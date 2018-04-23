package Hzfengsy;

import Hzfengsy.Exceptions.ErrorReporter;
import Hzfengsy.Visitor.*;
import Hzfengsy.Parser.MLexer;
import Hzfengsy.Parser.MParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;


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
        CharStream input = CharStreams.fromString(prog);
        MLexer lexer = new MLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MParser parser = new MParser(tokens);
        ParseTree tree = parser.main_prog();
        ClassVisitor class_visitor = new ClassVisitor(classes, reporter);
        FuncVisitor func_visitor = new FuncVisitor(functions, classes, reporter);
        MainVisitor main_visitor = new MainVisitor(functions, classes, reporter);
        try {
            parser.setErrorHandler(new BailErrorStrategy());
            class_visitor.visit(tree);
            func_visitor.visit(tree);
            main_visitor.visit(tree);
            reporter.check();
        } catch (Exception e) {
//            System.err.println("Parser error");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        String program;
        if (args.length == 1) program = readTestFile(args[0]);
        else program = readTestFile("program.txt");
        run(program);
    }
}