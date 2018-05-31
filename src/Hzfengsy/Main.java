package Hzfengsy;

import Hzfengsy.CodeGenerator.*;
import Hzfengsy.Exceptions.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;


public class Main
{
    private static ParseTree tree;
    private static String readTestFile(String filePath) {
        String ans = new String();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                ErrorReporter.getInstance().putLine(tempString);
                ans += tempString + '\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ans;
    }

    public static Boolean semantic(String prog) {
        Vector<String> errors = new Vector<>();
        CharStream input = CharStreams.fromString(prog);
        MLexer lexer = new MLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener()
        {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add("syntax error: " + line + ":" + charPositionInLine + ": " + msg);
            }
        });
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MParser parser = new MParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener()
        {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add("syntax error: " + line + ":" + charPositionInLine + ": " + msg);
            }
        });
        tree = parser.main_prog();
        if (!errors.isEmpty()) {
            for (String message : errors) System.err.println(message);
            System.exit(1);
        }
        ClassVisitor class_visitor = new ClassVisitor();
        FuncVisitor func_visitor = new FuncVisitor();
        MainVisitor main_visitor = new MainVisitor();
        class_visitor.visit(tree);
        func_visitor.visit(tree);
        main_visitor.visit(tree);
        return ErrorReporter.getInstance().check();
    }

    private static IRProgNode IRGenerate() {
        IRGenerator generator = new IRGenerator();
        return (IRProgNode) generator.visit(tree);
    }

    private static String codeGenrate(IRProgNode progNode) {
        CodeGenerator generator = new CodeGenerator(progNode);
        return generator.generate();
    }

    private static void writeFile(String text, String fileName) {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String program;
        if (args.length > 0) program = readTestFile(args[0]);
        else program = readTestFile("program.txt");
        semantic(program);
        IRProgNode IRProg = IRGenerate();

        InlineOptim optim = new InlineOptim(IRProg);
        optim.optim();

        System.err.println(IRProg);
        String code = codeGenrate(IRProg);
        if (args.length > 0) writeFile(code, "code.asm");
        else System.out.println(code);
        System.err.println(RegisterAllocator.print());
    }
}