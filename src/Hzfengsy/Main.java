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

        //对每一个输入的字符串，构造一个 ANTLRStringStream 流 in
        ANTLRInputStream in = new ANTLRInputStream(expr);

        //用 in 构造词法分析器 lexer，词法分析的作用是产生记号
        MLexer lexer = new MLexer(in);

        //用词法分析器 lexer 构造一个记号流 tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //再使用 tokens 构造语法分析器 parser,至此已经完成词法分析和语法分析的准备工作
        MParser parser = new MParser(tokens);

        //最终调用语法分析器的规则 prog，完成对表达式的验证
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