import org.antlr.v4.runtime.*;

import java.io.File;

public class Main extends BaseErrorListener{

    public static void main(String[] args) throws Exception {
        File path = new File("/home/andrew/IdeaProjects/MiniJava/src/main/resources/");
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("File Name: " + files[i].getName());
            CharStream stream = null;
            stream = CharStreams.fromFileName(files[i].getAbsolutePath());
            MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
            parser.setErrorHandler(new DefaultErrorStrategy());
            MiniJavaGrammarBaseVisitor classVisitor = new MiniJavaGrammarBaseVisitor();
            Class traverseResult = (Class) classVisitor.visit(parser.goal());
            stream = CharStreams.fromFileName(files[i].getAbsolutePath());
            lexer = new MiniJavaGrammarLexer(stream);
            tokenStream = new CommonTokenStream(lexer);
            parser = new MiniJavaGrammarParser(tokenStream);
            SecondVisitor visitor = new SecondVisitor(classVisitor);
            traverseResult = (Class) visitor.visit(parser.goal());
            //if(new TypeChecking(classVisitor.classes).symbolsAndTypes()){}
        }
    }
}