import org.antlr.v4.runtime.*;

public class Main extends BaseErrorListener{

    public static void main(String[] args) {
        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/BubbleSort.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
        parser.setErrorHandler(new DefaultErrorStrategy());
        MiniJavaGrammarBaseVisitor classVisitor = new MiniJavaGrammarBaseVisitor();
        Class traverseResult = (Class) classVisitor.visit(parser.goal());
        ErrorHandling handler = null;
        if(classVisitor.link()){}
    }
}