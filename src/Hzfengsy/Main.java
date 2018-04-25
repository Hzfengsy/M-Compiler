package Hzfengsy;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Visitor.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;


public class Main
{

    private static Functions functions = new Functions();
    private static Classes classes = new Classes();
    private static ErrorReporter reporter = new ErrorReporter();

    private static String readTestFile(String filePath) {
        String ans = new String();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                reporter.putLine(tempString);
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

    public static void run(String prog) {
        Vector<String> errors = new Vector<>();
        CharStream input = CharStreams.fromString(prog);
        MLexer lexer = new MLexer(input);
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
        ParseTree tree = parser.main_prog();
        if (!errors.isEmpty()) {
            for (String message : errors) System.err.println(message);
            System.exit(1);
        }
        ClassVisitor class_visitor = new ClassVisitor(classes, reporter);
        FuncVisitor func_visitor = new FuncVisitor(functions, classes, reporter);
        MainVisitor main_visitor = new MainVisitor(functions, classes, reporter);
        class_visitor.visit(tree);
        func_visitor.visit(tree);
        main_visitor.visit(tree);
        reporter.check();
    }

    public static void main(String[] args) {
        String program;
        if (args.length == 1) program = readTestFile(args[0]);
        else program = readTestFile("program.txt");
        run(program);
    }
}