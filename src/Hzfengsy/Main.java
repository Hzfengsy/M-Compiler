package Hzfengsy;
import Hzfengsy.Listener.*;
import Hzfengsy.Parser.MLexer;
import Hzfengsy.Parser.MParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Main {

    private static Function functions = new Function();
    private static Classes classes = new Classes();

    private static String readTestFile(String filePath) {
        String ans = new String();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
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

    public static void run(String prog) throws Exception
    {

        try
        {
            CharStream input = CharStreams.fromString(prog);
            MLexer lexer = new MLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MParser parser = new MParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parser.prog();
            preVisitor preeval = new preVisitor(functions, classes);
            preeval.visit(tree);
            Visitor eval = new Visitor(functions, classes);
            eval.visit(tree);
        }
        catch (Exception e)
        {
            System.err.println("Parser error");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception
    {
        String program;
        if (args.length == 1) program = readTestFile(args[0]);
        else program = readTestFile("program.txt");
        run(program);
    }
}