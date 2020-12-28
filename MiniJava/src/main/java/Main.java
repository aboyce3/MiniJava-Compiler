import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main extends BaseErrorListener{

    public static void main(String[] args) throws Exception {
        CharStream stream = null;
        stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Factorial.java");
        MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
        parser.setErrorHandler(new DefaultErrorStrategy());
        Linker classVisitor = new Linker();
        Class traverseResult = (Class) classVisitor.visit(parser.goal());
        stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Factorial.java");
        lexer = new MiniJavaGrammarLexer(stream);
        tokenStream = new CommonTokenStream(lexer);
        parser = new MiniJavaGrammarParser(tokenStream);
        ErrorHandling visitor = new ErrorHandling(classVisitor);
        traverseResult = (Class) visitor.visit(parser.goal());
        if(classVisitor.errorNotFound && visitor.errorNotFound){
            stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Factorial.java");
            lexer = new MiniJavaGrammarLexer(stream);
            tokenStream = new CommonTokenStream(lexer);
            parser = new MiniJavaGrammarParser(tokenStream);
            MiniJavaGrammarParser.GoalContext ctx = parser.goal();
            ParseTreeWalker walker = new ParseTreeWalker();
            CodeGenListener listener = new CodeGenListener(classVisitor, visitor);
            walker.walk(listener, ctx);
        }
    }
}