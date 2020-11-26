import org.antlr.v4.runtime.*;
import CodeGeneration.*;
import ErrorHandling.*;
import Models.*;

import java.io.File;

public class Main extends BaseErrorListener{

    public static void main(String[] args) throws Exception {
        String currentDirectory = System.getProperty("user.dir");
        File path = new File(currentDirectory + "/src/main/resources/");
        File[] files = path.listFiles();
        /*for (int i = 0; i < files.length; i++) {
            System.out.println("File Name: " + files[i].getName());
            CharStream stream = null;
            stream = CharStreams.fromFileName(files[i].getAbsolutePath());
            MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
            parser.setErrorHandler(new DefaultErrorStrategy());
            Linker classVisitor = new Linker();
            Class traverseResult = (Class) classVisitor.visit(parser.goal());
            stream = CharStreams.fromFileName(files[i].getAbsolutePath());
            lexer = new MiniJavaGrammarLexer(stream);
            tokenStream = new CommonTokenStream(lexer);
            parser = new MiniJavaGrammarParser(tokenStream);
            ErrorHandling visitor = new ErrorHandling(classVisitor);
            traverseResult = (Class) visitor.visit(parser.goal());
            if(classVisitor.errorNotFound && visitor.errorNotFound){
                stream = CharStreams.fromFileName(files[i].getAbsolutePath());
                lexer = new MiniJavaGrammarLexer(stream);
                tokenStream = new CommonTokenStream(lexer);
                parser = new MiniJavaGrammarParser(tokenStream);
                CodeGeneration generator = new CodeGeneration(classVisitor);
                traverseResult = (Class) visitor.visit(parser.goal());
            }
        }*/
        CharStream stream = null;
        stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Print.java");
        MiniJavaGrammarLexer lexer = new MiniJavaGrammarLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MiniJavaGrammarParser parser = new MiniJavaGrammarParser(tokenStream);
        parser.setErrorHandler(new DefaultErrorStrategy());
        Linker classVisitor = new Linker();
        Class traverseResult = (Class) classVisitor.visit(parser.goal());
        stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Print.java");
        lexer = new MiniJavaGrammarLexer(stream);
        tokenStream = new CommonTokenStream(lexer);
        parser = new MiniJavaGrammarParser(tokenStream);
        ErrorHandling visitor = new ErrorHandling(classVisitor);
        traverseResult = (Class) visitor.visit(parser.goal());
        if(classVisitor.errorNotFound && visitor.errorNotFound){
            stream = CharStreams.fromFileName("/home/andrew/IdeaProjects/MiniJava/src/main/resources/Print.java");
            lexer = new MiniJavaGrammarLexer(stream);
            tokenStream = new CommonTokenStream(lexer);
            parser = new MiniJavaGrammarParser(tokenStream);
            CodeGeneration generator = new CodeGeneration(classVisitor);
            traverseResult = (Class) generator.visit(parser.goal());
        }
    }
}