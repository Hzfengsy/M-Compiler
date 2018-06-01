package Hzfengsy.IR;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;
import javafx.util.*;
import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class IRGenerator extends MBaseVisitor<IRBase>
{
    private Classes classes = Classes.getInstance();
    private Functions functions = Functions.getInstance();
    private Map<String, IRFuncNode> funcNodeMap = new HashMap<>();
    private Stack<IRFuncNode> funcStack = new Stack<>();
    private Stack<UserType> classStack = new Stack<>();
    private RenameMap renameMap = RenameMap.getInstance();
    private IRVariables variables = IRVariables.getInstance();
    private IRLabelList labels = IRLabelList.getInstance();
    private Stack<IRBasicBlock> loopContinue = new Stack<>();
    private Stack<IRBasicBlock> loopBreak = new Stack<>();
    private TypeRecorder typeRecorder = TypeRecorder.getInstance();
    private IRBasicBlock globeVariable = new IRBasicBlock();

    private Set<String> builtin = new HashSet<>();
    private StringData stringData = StringData.getInstance();

    private void initBuiltIn() {
        builtin.add("malloc");
        builtin.add("getInt");
        builtin.add("print");
        builtin.add("println");
        builtin.add("toString");
        builtin.add("getString");
        builtin.add("parseInt");
        builtin.add("ord");
        builtin.add("substring");
        builtin.add("strcmp");
        IRFuncNode malloc = new IRFuncNode("malloc", true, new IRVar("size", false));
        funcNodeMap.put("malloc", malloc);
        IRFuncNode print = new IRFuncNode("print", true, new IRVar("str", false));
        funcNodeMap.put("print", print);
        IRFuncNode println = new IRFuncNode("println", true, new IRVar("str", false));
        funcNodeMap.put("println", println);
        IRFuncNode toString = new IRFuncNode("toString", true, new IRVar("i", false));
        funcNodeMap.put("toString", toString);
        IRFuncNode getString = new IRFuncNode("getString", true);
        funcNodeMap.put("getString", getString);
        IRFuncNode getInt = new IRFuncNode("getInt", true);
        funcNodeMap.put("getInt", getInt);
        IRFuncNode strCombine = new IRFuncNode("strCombine", true, new IRVar("str1", false), new IRVar("str2", false));
        funcNodeMap.put("strCombine", strCombine);
        IRFuncNode strlen = new IRFuncNode("strlen", true, new IRVar("str", false));
        funcNodeMap.put("strlen", strlen);
        IRFuncNode parseInt = new IRFuncNode("parseInt", true, new IRVar("str", false));
        funcNodeMap.put("parseInt", parseInt);
        IRFuncNode ord = new IRFuncNode("ord", true, new IRVar("str", false), new IRVar("i", false));
        funcNodeMap.put("ord", ord);
        IRFuncNode substring = new IRFuncNode("substring", true, new IRVar("str", false), new IRVar("left", false), new IRVar("right", false));
        funcNodeMap.put("substring", substring);
        IRFuncNode strcmp = new IRFuncNode("strcmp", true, new IRVar("str1", false), new IRVar("str2", false));
        funcNodeMap.put("strcmp", strcmp);
    }

    public IRGenerator() {
        initBuiltIn();
        for (Map.Entry<String, FuncType> entry : functions.values()) {
            String funcName = entry.getKey();
            if (builtin.contains(funcName)) continue;
            FuncType func = entry.getValue();
            IRVar[] parameter = getIRParameter(func);
            IRFuncNode function = new IRFuncNode(funcName, parameter);
            funcNodeMap.put(funcName, function);
        }
        for (BaseType userType : classes.values()) {
            if (!(userType instanceof UserType)) continue;
            for (Map.Entry<String, FuncType> entry : userType.funcstions()) {
                String funcName = ((UserType) userType).getName() + "." + entry.getKey();
                FuncType func = entry.getValue();
                IRVar[] parameter = getIRMemberParameter(func);
                IRFuncNode function = new IRFuncNode(funcName, parameter);
                function.addVar(parameter[0]);
                funcNodeMap.put(funcName, function);

            }
        }
        globeVariable.setLabel(new IRLable("main"));
        funcNodeMap.get("main").appendNode(globeVariable);
    }

    private IRVar[] getIRParameter(FuncType func) {
        Vector<String> parameterList = func.getParameterName();
        IRVar[] ans = new IRVar[parameterList.size()];
        for (int i = 0; i < parameterList.size(); i++)
            ans[i] = new IRVar(parameterList.elementAt(i), false);
        return ans;
    }

    private IRVar[] getIRMemberParameter(FuncType func) {
        Vector<String> parameterList = func.getParameterName();
        IRVar[] ans = new IRVar[parameterList.size() + 1];
        ans[0] = variables.insertTempVar();
        for (int i = 1; i <= parameterList.size(); i++)
            ans[i] = new IRVar(parameterList.elementAt(i - 1), false);
        return ans;
    }

    @Override
    public IRBase visitMain_prog(MParser.Main_progContext ctx) {
        IRBase ans = visit(ctx.prog());
        return ans;
    }

    @Override
    public IRBase visitProg(MParser.ProgContext ctx) {
        IRProgNode ans = new IRProgNode();
        for (ParseTree x : ctx.define()) {
            IRBase define = visit(x);
            if (define instanceof IRBasicBlock)
                globeVariable.join((IRBasicBlock) define);
            else if (define != null){
                globeVariable.join(((IRNode) define).getHead());
                globeVariable = ((IRNode) define).getTail();
            }
        }
        for (ParseTree x : ctx.func()) {
            visit(x);
        }
        for (ParseTree x : ctx.clas()) {
            visit(x);
        }
        for (IRFuncNode func : funcNodeMap.values())
            ans.insertFunc(func);
        return ans;
    }

    @Override
    public IRBase visitClas(MParser.ClasContext ctx) {
        String className = ctx.id().getText();
        UserType type = (UserType) classes.safeGetClass(className);
        classStack.push(type);
        visit(ctx.prog());
        classStack.pop();
        return null;
    }

    @Override
    public IRBase visitFunc(MParser.FuncContext ctx) {
        String funcName = ctx.id().getText();
        FuncType funcType;
        IRFuncNode func;
        IRBasicBlock funcEnd = new IRBasicBlock();
        funcEnd.setLabel(new IRLable("end_" + funcName));
        if (classStack.empty()) {
            funcType = functions.safeQuery(funcName);
            func = funcNodeMap.get(funcName);
        }
        else {
            UserType type = classStack.peek();
            funcType = type.safeQueryFunc(funcName);
            func = funcNodeMap.get(type.getName() + "." + funcName);
        }
        IRBasicBlock node;
        if (funcName.equals("main")) {
            node = globeVariable;
        }
        else {
            node = new IRBasicBlock();
            node.setLabel(labels.insert(classStack.empty() ? funcName : classStack.peek().getName() + "." + funcName));
            func.appendNode(node);
            for (IRVar var : func.getArgs()) {
                node.join(new IRUnaryExprInstruction(var, IROperations.unaryOp.MOV, var));
            }
        }

        funcStack.push(func);
        IRBasicBlock nowBlock = node;
        for (Pair<String, BaseType> var : funcType.getVars()) {
            IRVar irVar = variables.insertVar(var.getKey(), false);
            func.addVar(irVar);
        }
        for (MParser.StatContext x : ctx.stat()) {
            IRBase result = visit(x);
            if (result instanceof IRBasicBlock)
                nowBlock.join((IRBasicBlock) result);
            else if (result instanceof IRNode) {
                nowBlock.join(((IRNode) result).getHead());
                nowBlock = ((IRNode) result).getTail();
            }
        }
        if (!classStack.empty() && classStack.peek().getName().equals(funcName))
            nowBlock.join(new IRRetInstruction(func.getArgs()[0]));
        func.appendNode(funcEnd);
        funcStack.pop();
        return func;
    }

    @Override
    public IRBase visitExpr_Stat(MParser.Expr_StatContext ctx) {
        if (ctx.expr() != null) {
            IRBase ans = visit(ctx.expr());
            if (ans instanceof IRBasicBlock || ans instanceof IRNode) return ans;
        }
        return null;
    }

    @Override
    public IRBase visitAssign_Define(MParser.Assign_DefineContext ctx) {
        IRBase expr = visit(ctx.expr());
        if (funcStack.empty() && classStack.empty()) {
            variables.insertVar(renameMap.get(ctx), true);
        }
        IRVar result = variables.query(renameMap.get(ctx));
        IROperations.unaryOp op = IROperations.unaryOp.MOV;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBasicBlock(inst);
        }
        else if (expr instanceof IRBasicBlock) {
            IRBasicBlock ans = new IRBasicBlock((IRBasicBlock) expr);
            ans.setLastInstResult(result);
            return ans;
        }
        else {
            IRBasicBlock tail = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ((IRNode) expr).getResult());
            tail.join(inst);
            return expr;
        }
    }

    @Override
    public IRBase visitId_Define(MParser.Id_DefineContext ctx) {
        if (funcStack.empty() && classStack.empty()) {
            variables.insertVar(renameMap.get(ctx), true);
        }
        return null;
    }

    @Override
    public IRBase visitUnary(MParser.UnaryContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        if (ctx.op.getText().equals("+")) return null;
        IROperations.unaryOp op = IROperations.unaryOp.NEG;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBasicBlock(inst);
        }
        else if (expr instanceof IRBasicBlock) {
            IRBasicBlock ans = new IRBasicBlock((IRBasicBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBasicBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitNot(MParser.NotContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.unaryOp op = IROperations.unaryOp.NOT;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBasicBlock(inst);
        }
        else if (expr instanceof IRBasicBlock) {
            IRBasicBlock ans = new IRBasicBlock((IRBasicBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBasicBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitLNot(MParser.LNotContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.unaryOp op = IROperations.unaryOp.LNOT;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBasicBlock(inst);
        }
        else if (expr instanceof IRBasicBlock) {
            IRBasicBlock ans = new IRBasicBlock((IRBasicBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBasicBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitAddSub(MParser.AddSubContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(result);
        if (typeRecorder.get(ctx.expr(0)) instanceof StringType || typeRecorder.get(ctx.expr(1)) instanceof StringType) {
            IRBaseInstruction inst = new IRCallInstruction(result, funcNodeMap.get("strCombine"), left_expr, right_expr);
            ans.join(inst);
        }
        else {
            IROperations.binaryOp op = ctx.op.getText().equals("+") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
            ans.join(inst);
        }
        return ans;
    }

    @Override
    public IRBase visitAnd(MParser.AndContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.AND;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitLAnd(MParser.LAndContext ctx) {
        IRBasicBlock head, nowBlock;
        IRBasicBlock setResult = new IRBasicBlock();
        setResult.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(setResult);
        IRBasicBlock end = new IRBasicBlock();
        end.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(end);
        IRExpr left_expr, right_expr;

        IRBase left = visit(ctx.expr(0));
        if (left instanceof IRBasicBlock) {
            nowBlock = head = new IRBasicBlock((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else if (left instanceof IRNode) {
            head = ((IRNode) left).getHead();
            left_expr = ((IRNode) left).getResult();
            nowBlock = ((IRNode) left).getTail();
        }
        else {
            nowBlock = head = new IRBasicBlock();
            left_expr = (IRExpr) left;
        }
        nowBlock.join(new IRjumpInstruction(left_expr, IROperations.jmpOp.JZ, setResult));

        IRBase right = visit(ctx.expr(1));
        if (right instanceof IRBasicBlock) {
            nowBlock.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else if (right instanceof IRNode) {
            nowBlock.join(((IRNode) right).getHead());
            right_expr = ((IRNode) right).getResult();
            nowBlock = ((IRNode) right).getTail();
        }
        else {
            right_expr = (IRExpr) right;
        }
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        nowBlock.join(new IRjumpInstruction(right_expr, IROperations.jmpOp.JZ, setResult));
        nowBlock.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRConst(1)));
        nowBlock.join(new IRjumpInstruction(end));

        setResult.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRConst(0)));
        setResult.join(new IRjumpInstruction(end));
        end.setResult(result);
        return new IRNode(head, end);
    }

    @Override
    public IRBase visitOr(MParser.OrContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.OR;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitLOr(MParser.LOrContext ctx) {
        IRBasicBlock head, nowBlock;
        IRBasicBlock setResult = new IRBasicBlock();
        setResult.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(setResult);
        IRBasicBlock end = new IRBasicBlock();
        end.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(end);
        IRExpr left_expr, right_expr;

        IRBase left = visit(ctx.expr(0));
        if (left instanceof IRBasicBlock) {
            nowBlock = head = new IRBasicBlock((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else if (left instanceof IRNode) {
            head = ((IRNode) left).getHead();
            left_expr = ((IRNode) left).getResult();
            nowBlock = ((IRNode) left).getTail();
        }
        else {
            nowBlock = head = new IRBasicBlock();
            left_expr = (IRExpr) left;
        }
        nowBlock.join(new IRjumpInstruction(left_expr, IROperations.jmpOp.JNZ, setResult));

        IRBase right = visit(ctx.expr(1));
        if (right instanceof IRBasicBlock) {
            nowBlock.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else if (right instanceof IRNode) {
            nowBlock.join(((IRNode) right).getHead());
            right_expr = ((IRNode) right).getResult();
            nowBlock = ((IRNode) right).getTail();
        }
        else {
            right_expr = (IRExpr) right;
        }
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        nowBlock.join(new IRjumpInstruction(right_expr, IROperations.jmpOp.JNZ, setResult));
        nowBlock.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRConst(0)));
        nowBlock.join(new IRjumpInstruction(end));

        setResult.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRConst(1)));
        setResult.join(new IRjumpInstruction(end));
        end.setResult(result);
        return new IRNode(head, end);
    }

    @Override
    public IRBase visitXor(MParser.XorContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.XOR;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitBitwise(MParser.BitwiseContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(result);
        IROperations.binaryOp op = ctx.op.getText().equals("<<") ? IROperations.binaryOp.LSHIFT : IROperations.binaryOp.RSHIFT;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitEqual(MParser.EqualContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.binaryOp op = ctx.op.getText().equals("==") ? IROperations.binaryOp.EQ : IROperations.binaryOp.NE;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitCompare(MParser.CompareContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().addVar(result);
        IROperations.binaryOp op;
        if (ctx.op.getText().equals("<"))
            op = IROperations.binaryOp.LT;
        else if (ctx.op.getText().equals("<="))
            op = IROperations.binaryOp.LE;
        else if (ctx.op.getText().equals(">"))
            op = IROperations.binaryOp.GT;
        else op = IROperations.binaryOp.GE;
        if (typeRecorder.get(ctx.expr(0)) instanceof StringType || typeRecorder.get(ctx.expr(1)) instanceof StringType) {
            IRBaseInstruction inst = new IRCallInstruction(result, funcNodeMap.get("strcmp"), left_expr, right_expr);
            ans.join(inst);
            IRVar result_new = variables.insertTempVar();
            funcStack.peek().addVar(result_new);
            ans.join(new IRBinaryExprInstruction(result_new, op, result, new IRConst(0)));
            return ans;
        }
        else {
            IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
            ans.join(inst);
            return ans;
        }
    }

    @Override
    public IRBase visitMulDivMod(MParser.MulDivModContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(result);
        IROperations.binaryOp op;
        if (ctx.op.getText().equals("*"))
            op = IROperations.binaryOp.MUL;
        else if (ctx.op.getText().equals("/"))
            op = IROperations.binaryOp.DIV;
        else op = IROperations.binaryOp.MOD;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitReturn_Stat(MParser.Return_StatContext ctx) {
        if (ctx.expr() == null)
            return new IRBasicBlock(new IRRetInstruction(null));
        IRBase expr = visit(ctx.expr());
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr _expr;
        if (expr instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) expr);
            _expr = ((IRBasicBlock) expr).getResult();
        }
        else if (expr instanceof IRExpr) _expr = (IRExpr) expr;
        else {
            _expr = ((IRNode) expr).getResult();
            IRBaseInstruction inst = new IRRetInstruction(_expr);
            ((IRNode) expr).getTail().join(inst);
            return expr;
        }
        IRBaseInstruction inst = new IRRetInstruction(_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitNumber(MParser.NumberContext ctx) {
        return new IRConst(Integer.parseInt(ctx.getText()));
    }

    @Override
    public IRBase visitFalse(MParser.FalseContext ctx) {
        return new IRConst(0);
    }

    @Override
    public IRBase visitTrue(MParser.TrueContext ctx) {
        return new IRConst(1);
    }

    @Override
    public IRBase visitIdentity(MParser.IdentityContext ctx) {
        if (ASTSet.getInstance().getMembervar(ctx)) {
            IRFuncNode func = funcStack.peek();
            BaseType Class = classStack.peek();
            Integer index = Class.varIndex(ctx.getText());
            IRVar result = variables.insertTempVar();
            if (ASTSet.getInstance().getLeftValue(ctx)) {
                return new IRMem(func.getThis(), new IRConst(index));
            }
            funcStack.peek().addVar(result);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(func.getThis(), new IRConst(index)));
            return new IRBasicBlock(inst);
        }
        else
            return variables.query(renameMap.get(ctx));
    }

    @Override
    public IRBase visitParens(MParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public IRBase visitPrefix(MParser.PrefixContext ctx) {
        IRBase expr = visit(ctx.expr());
        if (expr instanceof IRVar) {
            IROperations.binaryOp op = ctx.op.getText().equals("++") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction((IRVar) expr, op, (IRExpr) expr, new IRConst(1));
            return new IRBasicBlock(inst);
        }
        else if (expr instanceof IRBasicBlock) {
            IRBasicBlock ans = new IRBasicBlock((IRBasicBlock) expr);
            IRExpr var = ((IRBasicBlock) expr).getResult();
            IROperations.binaryOp op = ctx.op.getText().equals("++") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction(var, op, var, new IRConst(1));
            ans.join(inst);
            return ans;
        }
        else return null;
    }

    @Override
    public IRBase visitPostfix(MParser.PostfixContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar temp = variables.insertTempVar();
        IRBasicBlock ans = new IRBasicBlock();
        funcStack.peek().addVar(temp);
        if (expr instanceof IRExpr) {
            IRVar var = (IRVar) expr;
            IRBaseInstruction tempCopy = new IRUnaryExprInstruction(temp, IROperations.unaryOp.MOV, var);
            ans.join(tempCopy);
            IROperations.binaryOp op = ctx.op.getText().equals("++") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction(var, op, var, new IRConst(1));
            ans.join(inst);
            ans.setResult(temp);
            return ans;
        }
        else if (expr instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) expr);
            IRExpr var = ((IRBasicBlock) expr).getResult();
            IRBaseInstruction tempCopy = new IRUnaryExprInstruction(temp, IROperations.unaryOp.MOV, var);
            ans.join(tempCopy);
            IROperations.binaryOp op = ctx.op.getText().equals("++") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction(var, op, var, new IRConst(1));
            ans.join(inst);
            ans.setResult(temp);
            return ans;
        }
        else return null;
    }

    @Override
    public IRBase visitAssignment(MParser.AssignmentContext ctx) {
        IRBase right = visit(ctx.expr(1));
        IRBase left = visit(ctx.expr(0));
        IRBasicBlock ans = new IRBasicBlock();
        IRExpr left_expr;
        IRExpr right_expr;
        if (left instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) left);
            left_expr = ((IRBasicBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBasicBlock) {
            ans.join((IRBasicBlock) right);
            right_expr = ((IRBasicBlock) right).getResult();
            if (((IRBasicBlock) right).AssignValid() && ((IRVar) right_expr).isTemp() && !(left_expr instanceof IRMem))
                ans.setLastInstResult(left_expr);
            else {
                IROperations.unaryOp op = IROperations.unaryOp.MOV;
                IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
                ans.join(inst);
            }
            return ans;
        }
        else if (right instanceof IRExpr) {
            right_expr = (IRExpr) right;
            IROperations.unaryOp op = IROperations.unaryOp.MOV;
            IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
            ans.join(inst);
            return ans;
        }
        else {
            IRBasicBlock tail = ((IRNode) right).getTail();
            right_expr = tail.getResult();
            IROperations.unaryOp op = IROperations.unaryOp.MOV;
            IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
            tail.join(inst);
            return right;
        }

    }

    @Override
    public IRBase visitSegment_Stat(MParser.Segment_StatContext ctx) {
        IRBasicBlock node = new IRBasicBlock();
        IRFuncNode func = funcStack.peek();
        IRBasicBlock nowBlock = node;
        for (MParser.StatContext x : ctx.stat()) {
            IRBase result = visit(x);
            if (result instanceof IRBasicBlock)
                nowBlock.join((IRBasicBlock) result);
            else if (result instanceof IRNode) {
                nowBlock.join(((IRNode) result).getHead());
                nowBlock = ((IRNode) result).getTail();
            }
        }
        if (node == nowBlock) return node;
        return new IRNode(node, nowBlock);
    }

    private IRBase forLoop(IRBase first, IRBase second, IRBase step, IRBase body) {
        IRBasicBlock head = new IRBasicBlock();
        if (first != null) {
            if (first instanceof IRBasicBlock)
                head.join((IRBasicBlock) first);
        }
        IRFuncNode func = funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek();
        IRBasicBlock bodyBlock = new IRBasicBlock();
        IRBasicBlock nowBody = bodyBlock;
        bodyBlock.setLabel(labels.insertTemp());
        func.appendNode(bodyBlock);
        head.join(new IRjumpInstruction(bodyBlock));
        IRBasicBlock stepBlock = null;
        if (step != null) {
            stepBlock = new IRBasicBlock();
            stepBlock.setLabel(labels.insertTemp());
            func.appendNode(stepBlock);
            loopContinue.push(stepBlock);
            if (step instanceof IRBasicBlock)
                stepBlock.join((IRBasicBlock) step);
            stepBlock.join(new IRjumpInstruction(bodyBlock));
        }
        else loopContinue.push(bodyBlock);
        IRBasicBlock tail = new IRBasicBlock();
        loopBreak.push(tail);
        tail.setLabel(labels.insertTemp());
        if (second != null) {
            IRExpr result;
            if (second instanceof IRExpr)
                result = (IRExpr) second;
            else if (second instanceof IRBasicBlock) {
                bodyBlock.join((IRBasicBlock) second);
                result = ((IRBasicBlock) second).getResult();
            }
            else {
                bodyBlock.join(((IRNode) second).getHead());
                result = ((IRNode) second).getResult();
                nowBody = ((IRNode) second).getTail();
            }
            IRVar tempResult = variables.insertTempVar();
            (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(tempResult);
            IRBaseInstruction jump = new IRjumpInstruction(result, IROperations.jmpOp.JZ, tail);
            nowBody.join(jump);
        }
        if (body instanceof IRBasicBlock) {
            nowBody.join((IRBasicBlock) body);
        }
        else if (((IRNode) body).getHead() == ((IRNode) body).getTail()) {
            nowBody.join(((IRNode) body).getHead());
        }
        else {
            nowBody.join(((IRNode) body).getHead());
            nowBody = ((IRNode) body).getTail();
        }
        if (step != null) {
            nowBody.join(new IRjumpInstruction(stepBlock));
        }
        else nowBody.join(new IRjumpInstruction(bodyBlock));
        func.appendNode(tail);
        loopBreak.pop();
        loopContinue.pop();
        return new IRNode(head, tail);
    }

    private IRBase forLoop(IRBase first, IRBase second, IRBase step, ParseTree ctx) {
        IRBasicBlock head = new IRBasicBlock();
        if (first != null) {
            if (first instanceof IRBasicBlock)
                head.join((IRBasicBlock) first);
        }
        IRFuncNode func = funcStack.peek();
        IRBasicBlock bodyBlock = new IRBasicBlock();
        IRBasicBlock nowBody = bodyBlock;
        bodyBlock.setLabel(labels.insertTemp());
        func.appendNode(bodyBlock);
        head.join(new IRjumpInstruction(bodyBlock));
        IRBasicBlock stepBlock = null;
        if (step != null) {
            stepBlock = new IRBasicBlock();
            stepBlock.setLabel(labels.insertTemp());
            func.appendNode(stepBlock);
            loopContinue.push(stepBlock);
            if (step instanceof IRBasicBlock)
                stepBlock.join((IRBasicBlock) step);
            stepBlock.join(new IRjumpInstruction(bodyBlock));
        }
        else loopContinue.push(bodyBlock);
        IRBasicBlock tail = new IRBasicBlock();
        loopBreak.push(tail);
        tail.setLabel(labels.insertTemp());
        if (second != null) {
            IRExpr result;
            if (second instanceof IRExpr)
                result = (IRExpr) second;
            else if (second instanceof IRBasicBlock) {
                bodyBlock.join((IRBasicBlock) second);
                result = ((IRBasicBlock) second).getResult();
            }
            else {
                bodyBlock.join(((IRNode) second).getHead());
                result = ((IRNode) second).getResult();
                nowBody = ((IRNode) second).getTail();
            }
            IRVar tempResult = variables.insertTempVar();
            (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(tempResult);
            IRBaseInstruction jump = new IRjumpInstruction(result, IROperations.jmpOp.JZ, tail);
            nowBody.join(jump);
        }
        IRBase body = visit(ctx);
        if (body == null) {

        }
        else if (body instanceof IRBasicBlock) {
            nowBody.join((IRBasicBlock) body);
        }
        else if (((IRNode) body).getHead() == ((IRNode) body).getTail()) {
            nowBody.join(((IRNode) body).getHead());
        }
        else {
            nowBody.join(((IRNode) body).getHead());
            nowBody = ((IRNode) body).getTail();
        }
        if (step != null) {
            nowBody.join(new IRjumpInstruction(stepBlock));
        }
        else nowBody.join(new IRjumpInstruction(bodyBlock));
        func.appendNode(tail);
        loopBreak.pop();
        loopContinue.pop();
        return new IRNode(head, tail);
    }

    @Override
    public IRBase visitFor_Stat(MParser.For_StatContext ctx) {
        IRBase first = ctx.first == null ? null : visit(ctx.first);
        IRBase second = ctx.second == null ? null : visit(ctx.second);
        IRBase third = ctx.third == null ? null : visit(ctx.third);
        return forLoop(first, second, third, ctx.stat());
    }

    @Override
    public IRBase visitIf_Stat(MParser.If_StatContext ctx) {
        IRBasicBlock head = new IRBasicBlock();
        IRBasicBlock body = new IRBasicBlock();
        IRBasicBlock now = head;
        body.setLabel(labels.insertTemp());
        IRFuncNode func = funcStack.peek();
        func.appendNode(body);
        IRBasicBlock tail = new IRBasicBlock();
        tail.setLabel(labels.insertTemp());
        IRBase IRCondition = visit(ctx.expr());
        IRExpr result;
        if (IRCondition instanceof IRExpr)
            result = (IRExpr) IRCondition;
        else if (IRCondition instanceof IRBasicBlock) {
            head.join((IRBasicBlock) IRCondition);
            result = ((IRBasicBlock) IRCondition).getResult();
        }
        else {
            head.join(((IRNode) IRCondition).getHead());
            result = ((IRNode) IRCondition).getResult();
            now = ((IRNode) IRCondition).getTail();
        }
        IRBaseInstruction jump = new IRjumpInstruction(result, IROperations.jmpOp.JNZ, body);
        now.join(jump);
        now.join(new IRjumpInstruction(tail));
        IRBase body_stat = visit(ctx.stat());
        if (body_stat instanceof IRBasicBlock)
            body.join((IRBasicBlock) body_stat);
        else {
            body.join(((IRNode) body_stat).getHead());
            body = ((IRNode) body_stat).getTail();
        }
        body.join(new IRjumpInstruction(tail));
        func.appendNode(tail);
        return new IRNode(head, tail);
    }

    @Override
    public IRBase visitIfElse_Stat(MParser.IfElse_StatContext ctx) {
        IRBasicBlock head = new IRBasicBlock();
        IRBasicBlock now = head;
        IRBasicBlock body_1 = new IRBasicBlock();
        IRBasicBlock body_2 = new IRBasicBlock();
        body_1.setLabel(labels.insertTemp());
        body_2.setLabel(labels.insertTemp());
        IRFuncNode func = funcStack.peek();
        func.appendNode(body_1);
        func.appendNode(body_2);
        IRBasicBlock tail = new IRBasicBlock();
        tail.setLabel(labels.insertTemp());
        IRBase IRCondition = visit(ctx.expr());
        IRExpr result;
        if (IRCondition instanceof IRExpr)
            result = (IRExpr) IRCondition;
        else if (IRCondition instanceof IRBasicBlock) {
            head.join((IRBasicBlock) IRCondition);
            result = ((IRBasicBlock) IRCondition).getResult();
        }
        else {
            head.join(((IRNode) IRCondition).getHead());
            result = ((IRNode) IRCondition).getResult();
            now = ((IRNode) IRCondition).getTail();
        }
        IRBaseInstruction jump = new IRjumpInstruction(result, IROperations.jmpOp.JNZ, body_1);
        now.join(jump);
        now.join(new IRjumpInstruction(body_2));

        IRBase body_stat_1 = visit(ctx.stat(0));
        if (body_stat_1 instanceof IRBasicBlock)
            body_1.join((IRBasicBlock) body_stat_1);
        else {
            body_1.join(((IRNode) body_stat_1).getHead());
            body_1 = ((IRNode) body_stat_1).getTail();
        }
        body_1.join(new IRjumpInstruction(tail));

        IRBase body_stat_2 = visit(ctx.stat(1));
        if (body_stat_2 instanceof IRBasicBlock)
            body_2.join((IRBasicBlock) body_stat_2);
        else {
            body_2.join(((IRNode) body_stat_2).getHead());
            body_2 = ((IRNode) body_stat_2).getTail();
        }
        body_2.join(new IRjumpInstruction(tail));
        func.appendNode(tail);
        return new IRNode(head, tail);
    }

    @Override
    public IRBase visitWhile_Stat(MParser.While_StatContext ctx) {
        return forLoop(null, visit(ctx.expr()), null, ctx.stat());
    }

    @Override
    public IRBase visitBreak_Stat(MParser.Break_StatContext ctx) {
        IRBasicBlock tail = loopBreak.peek();
        IRBaseInstruction inst = new IRjumpInstruction(tail);
        return new IRBasicBlock(inst);
    }

    @Override
    public IRBase visitContinue_Stat(MParser.Continue_StatContext ctx) {
        IRBasicBlock tail = loopContinue.peek();
        IRBaseInstruction inst = new IRjumpInstruction(tail);
        return new IRBasicBlock(inst);
    }

    @Override
    public IRBase visitExprList(MParser.ExprListContext ctx) {
        IRArgs arg = new IRArgs();
        if (ctx.expr() != null) {
            IRBase result = visit(ctx.expr());
            if (result instanceof IRExpr) arg.addArg((IRExpr) result);
            else if (result instanceof IRBasicBlock) arg.join((IRBasicBlock) result);
        }
        return arg;
    }

    @Override
    public IRBase visitExprListCombine(MParser.ExprListCombineContext ctx) {
        IRArgs args0 = (IRArgs) visit(ctx.expr_list(0));
        IRArgs args1 = (IRArgs) visit(ctx.expr_list(1));
        IRArgs ans = new IRArgs(args0);
        ans.join(args1);
        return ans;
    }

    @Override
    public IRBase visitFunction(MParser.FunctionContext ctx) {
        String funcName = ctx.id().getText();
        if (ASTSet.getInstance().getMembervar(ctx)) {
            UserType type = classStack.peek();
            funcName = type.getName() + "." + funcName;
        }
        IRFuncNode func = funcNodeMap.get(funcName);
        IRArgs args = (IRArgs) visit(ctx.expr_list());
        IRVar result = null;
        if (ASTSet.getInstance().getMembervar(ctx)) {
            UserType type = classStack.peek();
            if (!(type.safeQueryFunc(ctx.id().getText()).getReturnType() instanceof VoidType)) {
                result = variables.insertTempVar();
                funcStack.peek().addVar(result);
            }
            args.addArg(funcStack.peek().getThis());
        }
        else {
            if (!(functions.safeQuery(funcName).getReturnType() instanceof VoidType)) {
                result = variables.insertTempVar();
                (funcStack.empty() ? funcNodeMap.get("main") : funcStack.peek()).addVar(result);
            }
        }
        IRBasicBlock block = new IRBasicBlock(args);
        IRBaseInstruction inst = new IRCallInstruction(result, func, args.getArgs());
        block.join(inst);
        return block;
    }

    @Override
    public IRBase visitSubscript(MParser.SubscriptContext ctx) {
        IRBase id = visit(ctx.expr(0));
        IRBase index = visit(ctx.expr(1));
        IRExpr id_result, index_result;
        IRBasicBlock block = new IRBasicBlock();
        if (id instanceof IRExpr)
            id_result = (IRExpr) id;
        else {
            block.join((IRBasicBlock) id);
            id_result = ((IRBasicBlock) id).getResult();
        }
        if (index instanceof IRExpr)
            index_result = (IRExpr) index;
        else {
            block.join((IRBasicBlock) index);
            index_result = ((IRBasicBlock) index).getResult();
        }

        if (!ASTSet.getInstance().getLeftValue(ctx)) {
            IRVar result = variables.insertTempVar();
            funcStack.peek().addVar(result);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(id_result, index_result));
            block.join(inst);
        }
        else {
            block.setResult(new IRMem(id_result, index_result));
        }
        return block;
    }

    @Override
    public IRBase visitNew(MParser.NewContext ctx) {
        return visit(ctx.class_new());
    }

    @Override
    public IRBase visitDimension(MParser.DimensionContext ctx) {
        if (ctx.expr() == null) return null;
        return visit(ctx.expr());
    }

    private IRFuncNode nowFunc() {
        if (funcStack.empty()) return funcNodeMap.get("main");
        else return funcStack.peek();
    }

    private IRBase NewStack(Vector<Pair<IRExpr, IRExpr>> exprs, Integer deep) {
        Pair<IRExpr, IRExpr> expr = exprs.elementAt(deep);
        IRVar result = variables.insertTempVar();
        nowFunc().addVar(result);
        IRBaseInstruction inst = new IRCallInstruction(result, funcNodeMap.get("malloc"), expr.getValue());
        IRBasicBlock baseBlock = new IRBasicBlock(inst);
        IRVar temp = variables.insertTempVar();
        nowFunc().addVar(temp);
        baseBlock.join(new IRUnaryExprInstruction(new IRMem(result, new IRConst(0)), IROperations.unaryOp.MOV, expr.getKey()));
        baseBlock.join(new IRBinaryExprInstruction(temp, IROperations.binaryOp.ADD, result, new IRConst(8)));
        if (deep.equals(exprs.size() - 1))
            return baseBlock;
        IRVar index = variables.insertTempVar();
        nowFunc().addVar(index);
        IRBase next = NewStack(exprs, deep + 1);
        IRExpr addr = new IRMem(baseBlock.getResult(), index);
        if (next instanceof IRNode)
            ((IRNode) next).getTail().join(new IRUnaryExprInstruction(addr, IROperations.unaryOp.MOV, ((IRNode) next).getResult()));
        else {
            ((IRBasicBlock) next).join(new IRUnaryExprInstruction(addr, IROperations.unaryOp.MOV, ((IRBasicBlock) next).getResult()));
        }
        IRBaseInstruction first = new IRUnaryExprInstruction(index, IROperations.unaryOp.MOV, new IRConst(0));
        IRVar condition = variables.insertTempVar();
        nowFunc().addVar(condition);
        IRBaseInstruction second = new IRBinaryExprInstruction(condition, IROperations.binaryOp.LT, index, expr.getKey());
        IRBaseInstruction step = new IRBinaryExprInstruction(index, IROperations.binaryOp.ADD, index, new IRConst(1));
        IRNode loop = (IRNode) forLoop(new IRBasicBlock(first), new IRBasicBlock(second), new IRBasicBlock(step), next);
        baseBlock.join(loop.getHead());
        baseBlock.setResult(temp);
        IRNode ans = new IRNode(baseBlock, loop.getTail());
        ans.setResult(temp);
        return ans;
    }

    @Override
    public IRBase visitClass_new(MParser.Class_newContext ctx) {
        IRBasicBlock baseBlock = new IRBasicBlock();
        Integer length = ctx.dimension().size();
        Vector<Pair<IRExpr, IRExpr>> exprs = new Vector<>();
        String className = ctx.class_name().getText();
        if (length == 0) {
            UserType userClass = (UserType) classes.safeGetClass(className);
            if (className.equals("String")) return null;
            else {
                IRVar result = variables.insertTempVar();
                nowFunc().addVar(result);
                IRBaseInstruction inst = new IRCallInstruction(result, funcNodeMap.get("malloc"), new IRConst(userClass.memSize()));
                baseBlock.join(inst);
                if (userClass.construction) {
                    IRBaseInstruction inst_con = new IRCallInstruction(null, funcNodeMap.get(className + "." + className), result);
                    baseBlock.join(inst_con);
                    baseBlock.setResult(result);
                }
                return baseBlock;
            }
        }
        else {
            for (int i = 0; i < length; i++) {
                if (ctx.dimension(i).expr() == null) break;
                IRBase dimension = visit(ctx.dimension(i));
                IRExpr expr;
                if (dimension instanceof IRExpr)
                    expr = (IRExpr) dimension;
                else {
                    baseBlock.join((IRBasicBlock) dimension);
                    expr = ((IRBasicBlock) dimension).getResult();
                }
                IRVar plus = variables.insertTempVar();
                nowFunc().addVar(plus);

                IRBaseInstruction plus_inst = new IRBinaryExprInstruction(plus, IROperations.binaryOp.ADD, expr, new IRConst(1));
                baseBlock.join(plus_inst);
                IRVar size = variables.insertTempVar();
                nowFunc().addVar(size);

                IRBaseInstruction sizeChange = new IRBinaryExprInstruction(size, IROperations.binaryOp.LSHIFT, plus, new IRConst(3));
                baseBlock.join(sizeChange);
                exprs.add(new Pair<>(expr, size));
            }
            IRBase result = NewStack(exprs, 0);
            if (result instanceof IRBasicBlock) {
                baseBlock.join((IRBasicBlock) result);
                return baseBlock;
            }
            else {
                IRNode node = (IRNode) result;
                baseBlock.join(node.getHead());
                if (node.getHead() == node.getTail()) return baseBlock;

                else {
                    IRNode ans = new IRNode(baseBlock, node.getTail());
                    ans.setResult(node.getResult());
                    return ans;
                }
            }
        }
    }

    @Override
    public IRBase visitMembervar(MParser.MembervarContext ctx) {
        IRBasicBlock block = new IRBasicBlock();
        IRBase expr = visit(ctx.expr());
        IRExpr expr_result;
        if (expr instanceof IRExpr) expr_result = (IRExpr) expr;
        else {
            block.join((IRBasicBlock) expr);
            expr_result = ((IRBasicBlock) expr).getResult();
        }
        String varName = ctx.id().getText();
        BaseType Class = typeRecorder.get(ctx.expr());
        Integer index = Class.varIndex(varName);


        if (!ASTSet.getInstance().getLeftValue(ctx)) {
            IRVar result = variables.insertTempVar();
            funcStack.peek().addVar(result);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(expr_result, new IRConst(index)));
            block.join(inst);
        }
        else {
            block.setResult(new IRMem(expr_result, new IRConst(index)));
        }
        return block;
    }

    @Override
    public IRBase visitThis(MParser.ThisContext ctx) {
        return funcStack.peek().getThis();
    }

    @Override
    public IRBase visitMemberfunc(MParser.MemberfuncContext ctx) {
        IRBasicBlock ans = new IRBasicBlock();
        String funcName = ctx.id().getText();
        IRBase expr = visit(ctx.expr());
        IRExpr expr_result;
        if (expr instanceof IRExpr) {
            expr_result = (IRExpr) expr;
        }
        else {
            ans.join((IRBasicBlock) expr);
            expr_result = ((IRBasicBlock) expr).getResult();
        }
        BaseType Class = typeRecorder.get(ctx.expr());
        if (Class instanceof ArrayType) {
            if (funcName.equals("size")) {
                IRVar result = variables.insertTempVar();
                funcStack.peek().addVar(result);
                ans.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(expr_result, new IRConst(-1))));
                return ans;
            }
        }
        if (Class instanceof StringType) {
            IRVar result = variables.insertTempVar();
            funcStack.peek().addVar(result);
            if (funcName.equals("length")) {
                ans.join(new IRCallInstruction(result, funcNodeMap.get("strlen"), expr_result));
            }
            if (funcName.equals("parseInt")) {
                ans.join(new IRCallInstruction(result, funcNodeMap.get("parseInt"), expr_result));
            }
            if (funcName.equals("ord")) {
                IRArgs args = new IRArgs();
                args.addArg(expr_result);
                args.join((IRArgs) visit(ctx.expr_list()));
                ans.join(args);
                ans.join(new IRCallInstruction(result, funcNodeMap.get("ord"), args.getArgs()));
            }
            if (funcName.equals("substring")) {
                IRArgs args = new IRArgs();
                args.addArg(expr_result);
                args.join((IRArgs) visit(ctx.expr_list()));
                ans.join(args);
                ans.join(new IRCallInstruction(result, funcNodeMap.get("substring"), args.getArgs()));
            }
            return ans;
        }
        String className = ((UserType) Class).getName();
        IRFuncNode func = funcNodeMap.get(className + "." + funcName);
        IRArgs args = new IRArgs();
        args.addArg(expr_result);
        args.join((IRArgs) visit(ctx.expr_list()));
        ans.join(args);
        IRVar result = null;
        if (!(Class.safeQueryFunc(funcName).getReturnType() instanceof VoidType)) {
            result = variables.insertTempVar();
            funcStack.peek().addVar(result);
        }
        IRBaseInstruction inst = new IRCallInstruction(result, func, args.getArgs());
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitStr(MParser.StrContext ctx) {
        String x = ctx.getText();
        x = x.substring(1, x.length() - 1);
        IRExpr get = stringData.getLabel(x);
        if (get == null) {
            IRVar var = variables.insertTempString();
            stringData.insert(var, x);
            return var;
        }
        else return get;
    }

    @Override
    public IRBase visitNull(MParser.NullContext ctx) {
        return new IRConst(0);
    }
}
