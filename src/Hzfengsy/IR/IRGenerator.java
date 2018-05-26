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
    private IRLabelList labels = new IRLabelList();
    private Stack<IRBaseBlock> loopContinue = new Stack<>();
    private Stack<IRBaseBlock> loopBreak = new Stack<>();
    private TypeRecorder typeRecorder = TypeRecorder.getInstance();
    private IRBaseBlock globeVariable = new IRBaseBlock();

    private Set<String> buildin = new HashSet<>();

    public IRGenerator() {
        buildin.add("malloc");
        buildin.add("getInt");
        buildin.add("print");
        buildin.add("println");
        buildin.add("toString");
        buildin.add("getString");
        IRFuncNode malloc = new IRFuncNode("malloc", true, new IRVar("size", false));
        funcNodeMap.put("malloc", malloc);
        for (Map.Entry<String, FuncType> entry : functions.values()) {
            String funcName = entry.getKey();
            if (buildin.contains(funcName)) continue;
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
                funcNodeMap.put(funcName, function);
            }
        }
        globeVariable.setLabel(new IRLable("main"));
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
        //        funcStack.peek().allocVar(ans[0]);
        for (int i = 1; i <= parameterList.size(); i++)
            ans[i] = new IRVar(parameterList.elementAt(i), false);
        return ans;
    }

    @Override
    public IRBase visitMain_prog(MParser.Main_progContext ctx) {
        IRBase ans = visit(ctx.prog());
        System.err.println(ans.toString());
        return ans;
    }

    @Override
    public IRBase visitProg(MParser.ProgContext ctx) {
        IRProgNode ans = new IRProgNode();
        for (ParseTree x : ctx.define()) {
            IRBase define = visit(x);
            globeVariable.join((IRBaseBlock) define);
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
        IRBaseBlock funcEnd = new IRBaseBlock();
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
        IRBaseBlock node;
        if (funcName.equals("main")) {
            node = globeVariable;
        }
        else {
            node = new IRBaseBlock();
            node.setLabel(labels.insert(funcName));
        }
        func.appendNode(node);
        funcStack.push(func);
        IRBaseBlock nowBlock = node;
        for (Pair<String, BaseType> var : funcType.getVars()) {
            IRVar irVar = variables.insertVar(var.getKey(), false);
            func.allocVar(irVar);
        }
        for (MParser.StatContext x : ctx.stat()) {
            IRBase result = visit(x);
            if (result instanceof IRBaseBlock)
                nowBlock.join((IRBaseBlock) result);
            else if (result instanceof IRNode) {
                nowBlock.join(((IRNode) result).getHead());
                nowBlock = ((IRNode) result).getTail();
            }
        }
        func.appendNode(funcEnd);
        funcStack.pop();
        return func;
    }

    @Override
    public IRBase visitExpr_Stat(MParser.Expr_StatContext ctx) {
        if (ctx.expr() != null) {
            IRBase ans = visit(ctx.expr());
            if (ans instanceof IRBaseBlock || ans instanceof IRNode) return ans;
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
            return new IRBaseBlock(inst);
        }
        else if (expr instanceof IRBaseBlock) {
            IRBaseBlock ans = new IRBaseBlock((IRBaseBlock) expr);
            ans.setLastInstResult(result);
            return ans;
        }
        else {
            IRBaseBlock ans = ((IRNode) expr).getHead();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(ans, ((IRNode) expr).getTail());
        }
    }

    @Override
    public IRBase visitUnary(MParser.UnaryContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        if (ctx.op.getText().equals("+")) return null;
        IROperations.unaryOp op = IROperations.unaryOp.NEG;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBaseBlock(inst);
        }
        else if (expr instanceof IRBaseBlock) {
            IRBaseBlock ans = new IRBaseBlock((IRBaseBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBaseBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitNot(MParser.NotContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.unaryOp op = IROperations.unaryOp.NOT;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBaseBlock(inst);
        }
        else if (expr instanceof IRBaseBlock) {
            IRBaseBlock ans = new IRBaseBlock((IRBaseBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBaseBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitLNot(MParser.LNotContext ctx) {
        IRBase expr = visit(ctx.expr());
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.unaryOp op = IROperations.unaryOp.LNOT;
        if (expr instanceof IRExpr) {
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, (IRExpr) expr);
            return new IRBaseBlock(inst);
        }
        else if (expr instanceof IRBaseBlock) {
            IRBaseBlock ans = new IRBaseBlock((IRBaseBlock) expr);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return ans;
        }
        else {
            IRBaseBlock ans = ((IRNode) expr).getTail();
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, op, ans.getResult());
            ans.join(inst);
            return new IRNode(((IRNode) expr).getHead(), ans);
        }
    }

    @Override
    public IRBase visitAddSub(MParser.AddSubContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = ctx.op.getText().equals("+") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitAnd(MParser.AndContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.AND;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitLAnd(MParser.LAndContext ctx) {
        IRBaseBlock head, nowBlock;
        IRBaseBlock setResult = new IRBaseBlock();
        setResult.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(setResult);
        IRBaseBlock end = new IRBaseBlock();
        end.setLabel(labels.insertTemp());
        funcStack.peek().appendNode(end);
        IRExpr left_expr, right_expr;

        IRBase left = visit(ctx.expr(0));
        if (left instanceof IRBaseBlock) {
            nowBlock = head = new IRBaseBlock((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else if (left instanceof IRNode) {
            head = ((IRNode) left).getHead();
            left_expr = ((IRNode) left).getResult();
            nowBlock = ((IRNode) left).getTail();
        }
        else {
            nowBlock = head = new IRBaseBlock();
            left_expr = (IRExpr) left;
        }
        nowBlock.join(new IRjumpInstruction(left_expr, IROperations.jmpOp.JZ, setResult));

        IRBase right = visit(ctx.expr(1));
        if (right instanceof IRBaseBlock) {
            nowBlock.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else if (right instanceof IRNode) {
            head = ((IRNode) right).getHead();
            right_expr = ((IRNode) right).getResult();
            nowBlock = ((IRNode) right).getTail();
        }
        else {
            right_expr = (IRExpr) right;
        }
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        nowBlock.join(new IRjumpInstruction(right_expr, IROperations.jmpOp.JNZ, end));
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
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.OR;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitLOr(MParser.LOrContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.OR;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitXor(MParser.XorContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = IROperations.binaryOp.XOR;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitBitwise(MParser.BitwiseContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = ctx.op.getText().equals("<<") ? IROperations.binaryOp.LSHIFT : IROperations.binaryOp.RSHIFT;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitEqual(MParser.EqualContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op = ctx.op.getText().equals("==") ? IROperations.binaryOp.EQ : IROperations.binaryOp.NE;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitCompare(MParser.CompareContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IROperations.binaryOp op;
        if (ctx.op.getText().equals("<"))
            op = IROperations.binaryOp.LT;
        else if (ctx.op.getText().equals("<="))
            op = IROperations.binaryOp.LE;
        else if (ctx.op.getText().equals(">"))
            op = IROperations.binaryOp.GT;
        else op = IROperations.binaryOp.GE;
        IRBaseInstruction inst = new IRBinaryExprInstruction(result, op, left_expr, right_expr);
        ans.join(inst);
        return ans;
    }

    @Override
    public IRBase visitMulDivMod(MParser.MulDivModContext ctx) {
        IRBase left = visit(ctx.expr(0));
        IRBase right = visit(ctx.expr(1));
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr, right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
        }
        else right_expr = (IRExpr) right;
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
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
        if (ctx.expr() == null) return null;
        IRBase expr = visit(ctx.expr());
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr _expr;
        if (expr instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) expr);
            _expr = ((IRBaseBlock) expr).getResult();
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
            funcStack.peek().allocVar(result);
            IRBaseInstruction inst = new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(func.getThis(), new IRConst(index)));
            return new IRBaseBlock(inst);
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
            return new IRBaseBlock(inst);
        }
        else return null;
    }

    @Override
    public IRBase visitPostfix(MParser.PostfixContext ctx) {
        IRBase expr = visit(ctx.expr());
        if (expr instanceof IRExpr) {
            IRVar var = (IRVar) expr;
            IRVar temp = variables.insertTempVar();
            funcStack.peek().allocVar(temp);
            IRBaseInstruction tempCopy = new IRUnaryExprInstruction(temp, IROperations.unaryOp.MOV, var);
            IRBaseBlock ans = new IRBaseBlock(tempCopy);
            IROperations.binaryOp op = ctx.op.getText().equals("++") ? IROperations.binaryOp.ADD : IROperations.binaryOp.SUB;
            IRBaseInstruction inst = new IRBinaryExprInstruction((IRVar) expr, op, (IRExpr) expr, new IRConst(1));
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
        IRBaseBlock ans = new IRBaseBlock();
        IRExpr left_expr;
        IRExpr right_expr;
        if (left instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) left);
            left_expr = ((IRBaseBlock) left).getResult();
        }
        else left_expr = (IRExpr) left;
        if (right instanceof IRBaseBlock) {
            ans.join((IRBaseBlock) right);
            right_expr = ((IRBaseBlock) right).getResult();
            if (((IRBaseBlock) right).AssignValid() && ((IRVar) right_expr).isTemp())
                ans.setLastInstResult(left_expr);
            else {
                IROperations.unaryOp op = IROperations.unaryOp.MOV;
                IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
                ans.join(inst);
            }
        }
        else if (right instanceof IRExpr) {
            right_expr = (IRExpr) right;
            IROperations.unaryOp op = IROperations.unaryOp.MOV;
            IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
            ans.join(inst);
        }
        else {
            right_expr = (IRExpr) right;
            IROperations.unaryOp op = IROperations.unaryOp.MOV;
            IRBaseInstruction inst = new IRUnaryExprInstruction(left_expr, op, right_expr);
            ans.join(inst);
        }
        return ans;
    }

    @Override
    public IRBase visitSegment_Stat(MParser.Segment_StatContext ctx) {
        IRBaseBlock node = new IRBaseBlock();
        IRFuncNode func = funcStack.peek();
        IRBaseBlock nowBlock = node;
        for (MParser.StatContext x : ctx.stat()) {
            IRBase result = visit(x);
            if (result instanceof IRBaseBlock)
                nowBlock.join((IRBaseBlock) result);
            else if (result instanceof IRNode) {
                nowBlock.join(((IRNode) result).getHead());
                nowBlock = ((IRNode) result).getTail();
            }
        }
        if (node == nowBlock) return node;
        return new IRNode(node, nowBlock);
    }

    private IRBase forLoop(IRBase first, IRBase second, IRBase step, IRBase body) {
        IRBaseBlock head = new IRBaseBlock();
        if (first != null) {
            if (first instanceof IRBaseBlock)
                head.join((IRBaseBlock) first);
        }
        IRFuncNode func = funcStack.peek();
        IRBaseBlock bodyBlock = new IRBaseBlock();
        bodyBlock.setLabel(labels.insertTemp());
        func.appendNode(bodyBlock);
        head.join(new IRjumpInstruction(bodyBlock));
        IRBaseBlock stepBlock = null;
        if (step != null) {
            stepBlock = new IRBaseBlock();
            stepBlock.setLabel(labels.insertTemp());
            func.appendNode(stepBlock);
            loopContinue.push(stepBlock);
            if (step instanceof IRBaseBlock)
                stepBlock.join((IRBaseBlock) step);
            stepBlock.join(new IRjumpInstruction(bodyBlock));
        }
        else loopContinue.push(bodyBlock);
        IRBaseBlock tail = new IRBaseBlock();
        loopBreak.push(tail);
        tail.setLabel(labels.insertTemp());
        if (second != null) {
            IRExpr result;
            if (second instanceof IRExpr)
                result = (IRExpr) second;
            else if (second instanceof IRNode) {
                bodyBlock.join((IRBaseBlock) second);
                result = ((IRBaseBlock) second).getResult();
            }
            else {
                bodyBlock.join(((IRNode) second).getHead());
                result = ((IRNode) second).getResult();
            }
            IRVar tempResult = variables.insertTempVar();
            funcStack.peek().allocVar(tempResult);
            IRBaseInstruction jump = new IRjumpInstruction(result, IROperations.jmpOp.JZ, tail);
            bodyBlock.join(jump);
        }
        if (body instanceof IRBaseBlock) {
            bodyBlock.join((IRBaseBlock) body);
        }
        else if (((IRNode) body).getHead() == ((IRNode) body).getTail()) {
            bodyBlock.join(((IRNode) body).getHead());
        }
        else {
            bodyBlock.join(((IRNode) body).getHead());
            bodyBlock = ((IRNode) body).getTail();
        }
        if (step != null) {
            bodyBlock.join(new IRjumpInstruction(stepBlock));
        }
        else bodyBlock.join(new IRjumpInstruction(bodyBlock));
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
        return forLoop(first, second, third, visit(ctx.stat()));
    }

    @Override
    public IRBase visitIf_Stat(MParser.If_StatContext ctx) {
        IRBaseBlock head = new IRBaseBlock();
        IRBaseBlock body = new IRBaseBlock();
        IRBaseBlock now = head;
        body.setLabel(labels.insertTemp());
        IRFuncNode func = funcStack.peek();
        func.appendNode(body);
        IRBaseBlock tail = new IRBaseBlock();
        tail.setLabel(labels.insertTemp());
        IRBase IRCondition = visit(ctx.expr());
        IRExpr result;
        if (IRCondition instanceof IRExpr)
            result = (IRExpr) IRCondition;
        else if (IRCondition instanceof IRBaseBlock) {
            head.join((IRBaseBlock) IRCondition);
            result = ((IRBaseBlock) IRCondition).getResult();
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
        if (body_stat instanceof IRBaseBlock)
            body.join((IRBaseBlock) body_stat);
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
        IRBaseBlock head = new IRBaseBlock();
        IRBaseBlock now = head;
        IRBaseBlock body_1 = new IRBaseBlock();
        IRBaseBlock body_2 = new IRBaseBlock();
        body_1.setLabel(labels.insertTemp());
        body_2.setLabel(labels.insertTemp());
        IRFuncNode func = funcStack.peek();
        func.appendNode(body_1);
        func.appendNode(body_2);
        IRBaseBlock tail = new IRBaseBlock();
        tail.setLabel(labels.insertTemp());
        IRBase IRCondition = visit(ctx.expr());
        IRExpr result;
        if (IRCondition instanceof IRExpr)
            result = (IRExpr) IRCondition;
        else if (IRCondition instanceof IRBaseBlock) {
            head.join((IRBaseBlock) IRCondition);
            result = ((IRBaseBlock) IRCondition).getResult();
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
        if (body_stat_1 instanceof IRBaseBlock)
            body_1.join((IRBaseBlock) body_stat_1);
        else {
            body_1.join(((IRNode) body_stat_1).getHead());
            body_1 = ((IRNode) body_stat_1).getTail();
        }
        body_1.join(new IRjumpInstruction(tail));

        IRBase body_stat_2 = visit(ctx.stat(1));
        if (body_stat_2 instanceof IRBaseBlock)
            body_2.join((IRBaseBlock) body_stat_2);
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
        return forLoop(null, visit(ctx.expr()), null, visit(ctx.stat()));
    }

    @Override
    public IRBase visitBreak_Stat(MParser.Break_StatContext ctx) {
        IRBaseBlock tail = loopBreak.peek();
        IRBaseInstruction inst = new IRjumpInstruction(tail);
        return new IRBaseBlock(inst);
    }

    @Override
    public IRBase visitContinue_Stat(MParser.Continue_StatContext ctx) {
        IRBaseBlock tail = loopContinue.peek();
        IRBaseInstruction inst = new IRjumpInstruction(tail);
        return new IRBaseBlock(inst);
    }

    @Override
    public IRBase visitExprList(MParser.ExprListContext ctx) {
        IRArgs arg = new IRArgs();
        if (ctx.expr() != null) {
            IRBase result = visit(ctx.expr());
            if (result instanceof IRExpr) arg.addArg((IRExpr) result);
            else if (result instanceof IRBaseBlock) arg.join((IRBaseBlock) result);
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
        IRFuncNode func = funcNodeMap.get(funcName);
        IRArgs args = (IRArgs) visit(ctx.expr_list());
        IRVar result = null;
        if (!(functions.safeQuery(funcName).getReturnType() instanceof VoidType)) {
            result = variables.insertTempVar();
            funcStack.peek().allocVar(result);
        }
        IRBaseBlock block = new IRBaseBlock(args);
        IRBaseInstruction inst = new IRCallInstruction(result, func, args.getArgs());
        block.join(inst);
        return block;
    }

    @Override
    public IRBase visitSubscript(MParser.SubscriptContext ctx) {
        IRBase id = visit(ctx.expr(0));
        IRBase index = visit(ctx.expr(1));
        IRExpr id_result, index_result;
        IRBaseBlock block = new IRBaseBlock();
        if (id instanceof IRExpr)
            id_result = (IRExpr) id;
        else {
            block.join((IRBaseBlock) id);
            id_result = ((IRBaseBlock) id).getResult();
        }
        if (index instanceof IRExpr)
            index_result = (IRExpr) index;
        else {
            block.join((IRBaseBlock) index);
            index_result = ((IRBaseBlock) id).getResult();
        }

        if (!ASTSet.getInstance().getLeftValue(ctx)) {
            IRVar result = variables.insertTempVar();
            funcStack.peek().allocVar(result);
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

    private IRBase NewStack(Vector<Pair<IRExpr, IRExpr>> exprs, Integer deep) {
        Pair<IRExpr, IRExpr> expr = exprs.elementAt(deep);
        IRVar result = variables.insertTempVar();
        funcStack.peek().allocVar(result);
        IRBaseInstruction inst = new IRCallInstruction(result, funcNodeMap.get("malloc"), expr.getValue());
        IRBaseBlock baseBlock = new IRBaseBlock(inst);
        IRVar temp = variables.insertTempVar();
        funcStack.peek().allocVar(temp);
        baseBlock.join(new IRUnaryExprInstruction(new IRMem(result, new IRConst(0)), IROperations.unaryOp.MOV, expr.getKey()));
        baseBlock.join(new IRBinaryExprInstruction(temp, IROperations.binaryOp.ADD, result, new IRConst(8)));
        if (deep.equals(exprs.size() - 1))
            return baseBlock;
        IRVar index = variables.insertTempVar();
        funcStack.peek().allocVar(index);
        IRBase next = NewStack(exprs, deep + 1);
        IRExpr addr = new IRMem(baseBlock.getResult(), index);
        if (next instanceof IRNode)
            ((IRNode) next).getTail().join(new IRUnaryExprInstruction(addr, IROperations.unaryOp.MOV, ((IRNode) next).getResult()));
        else {
            ((IRBaseBlock) next).join(new IRUnaryExprInstruction(addr, IROperations.unaryOp.MOV, ((IRBaseBlock) next).getResult()));
        }
        IRBaseInstruction first = new IRUnaryExprInstruction(index, IROperations.unaryOp.MOV, new IRConst(0));
        IRVar condition = variables.insertTempVar();
        funcStack.peek().allocVar(condition);
        IRBaseInstruction second = new IRBinaryExprInstruction(condition, IROperations.binaryOp.LT, index, expr.getValue());
        IRBaseInstruction step = new IRBinaryExprInstruction(index, IROperations.binaryOp.ADD, index, new IRConst(1));
        IRNode loop = (IRNode) forLoop(new IRBaseBlock(first), new IRBaseBlock(second), new IRBaseBlock(step), next);
        baseBlock.join(loop.getHead());
        baseBlock.setResult(result);
        return new IRNode(baseBlock, loop.getTail());
    }

    @Override
    public IRBase visitClass_new(MParser.Class_newContext ctx) {
        IRBaseBlock baseBlock = new IRBaseBlock();
        Integer length = ctx.dimension().size();
        Vector<Pair<IRExpr, IRExpr>> exprs = new Vector<>();
        String className = ctx.class_name().getText();
        if (length == 0) {
            UserType userClass = (UserType) classes.safeGetClass(className);
            if (className.equals("String")) return null;
            else {
                IRVar result = variables.insertTempVar();
                funcStack.peek().allocVar(result);
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
                    baseBlock.join((IRBaseBlock) dimension);
                    expr = ((IRBaseBlock) dimension).getResult();
                }
                IRVar plus = variables.insertTempVar();
                funcStack.peek().allocVar(plus);

                IRBaseInstruction plus_inst = new IRBinaryExprInstruction(plus, IROperations.binaryOp.ADD, expr, new IRConst(1));
                baseBlock.join(plus_inst);
                IRVar size = variables.insertTempVar();
                funcStack.peek().allocVar(size);

                IRBaseInstruction sizeChange = new IRBinaryExprInstruction(size, IROperations.binaryOp.LSHIFT, plus, new IRConst(3));
                baseBlock.join(sizeChange);
                exprs.add(new Pair<>(expr, size));
            }
            IRBase result = NewStack(exprs, 0);
            if (result instanceof IRBaseBlock) {
                baseBlock.join((IRBaseBlock) result);
                return baseBlock;
            }
            else {
                IRNode node = (IRNode) result;
                baseBlock.join(node.getHead());
                if (node.getHead() == node.getTail()) return baseBlock;
                else return new IRNode(baseBlock, node.getTail());
            }
        }
    }

    @Override
    public IRBase visitMembervar(MParser.MembervarContext ctx) {
        IRBaseBlock block = new IRBaseBlock();
        IRBase expr = visit(ctx.expr());
        IRExpr expr_result;
        if (expr instanceof IRExpr) expr_result = (IRExpr) expr;
        else {
            block.join((IRBaseBlock) expr);
            expr_result = ((IRBaseBlock) expr).getResult();
        }
        String varName = ctx.id().getText();
        BaseType Class = typeRecorder.get(ctx);
        Integer index = Class.varIndex(varName);


        if (!ASTSet.getInstance().getLeftValue(ctx)) {
            IRVar result = variables.insertTempVar();
            funcStack.peek().allocVar(result);
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
        IRBaseBlock ans = new IRBaseBlock();
        String funcName = ctx.id().getText();
        IRBase expr = visit(ctx.expr());
        IRExpr expr_result;
        if (expr instanceof IRExpr) {
            expr_result = (IRExpr) expr;
        }
        else {
            ans.join((IRBaseBlock) expr);
            expr_result = ((IRBaseBlock) expr).getResult();
        }
        BaseType Class = typeRecorder.get(ctx);
        if (Class instanceof ArrayType) {
            if (funcName.equals("size")) return new IRMem(expr_result, new IRConst(-1));
        }
        String className = ((UserType) Class).getName();
        IRFuncNode func = funcNodeMap.get(className + "." + funcName);
        IRArgs args = new IRArgs();
        args.addArg(expr_result);
        args.join((IRArgs) visit(ctx.expr_list()));
        IRVar result = null;
        if (!(Class.safeQueryFunc(funcName).getReturnType() instanceof VoidType)) {
            result = variables.insertTempVar();
            funcStack.peek().allocVar(result);
        }

        IRBaseInstruction inst = new IRCallInstruction(result, func, args.getArgs());
        return new IRBaseBlock(inst);
    }

}
