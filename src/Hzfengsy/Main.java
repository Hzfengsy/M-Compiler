package Hzfengsy;
import Hzfengsy.Listener.MyListener;
import Hzfengsy.Listener.Visitor;
import Hzfengsy.Parser.MLexer;
import Hzfengsy.Parser.MParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Main {

    public static String readTestFile(String filePath) {
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

    public static void run(String expr) throws Exception
    {

        ANTLRInputStream in = new ANTLRInputStream(expr);

        MLexer lexer = new MLexer(in);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MParser parser = new MParser(tokens);

        ParseTree tree = parser.prog();
//        Visitor eval = new Visitor();
//        eval.visit(tree);


        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new MyListener(), tree);
    }

    public static void main(String[] args) throws Exception{

        String testStr=readTestFile("testdata");
        run(testStr);
    }
}