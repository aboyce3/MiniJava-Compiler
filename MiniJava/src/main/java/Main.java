import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main extends BaseErrorListener{

    public static void main(String[] args){
       CharStream stream = null;
       try{
          stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Factorial.java");
       }catch(Exception e){
           e.printStackTrace();
       }
       MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
       CommonTokenStream tokenStream = new CommonTokenStream(lexer);
       MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
       ParseTree tree = parser.goal();
       parser.setErrorHandler(new DefaultErrorStrategy());

    }
}