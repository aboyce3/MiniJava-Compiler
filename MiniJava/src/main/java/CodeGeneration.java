import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.HashMap;

public class CodeGeneration<T> extends AbstractParseTreeVisitor<T> implements MiniJavaGrammarVisitor<T> {

    HashMap<String, Klass> classes;
    ArrayList<MethodDeclaration> allMethods;
    HashMap<String, MethodDeclaration> allMethodsMap;
    Klass currentClass;
    MethodDeclaration currentMethod;
    CodeBlock currentBlock;
    ArrayList<Scope> scopes;
    Scope currentScope;
    ClassFile cf;
    ClassPool cp = ClassPool.getDefault();
    MethodInfo minfo;

    public CodeGeneration(Linker visitor){
        classes = visitor.classes;
        allMethods = visitor.allMethods;
        allMethodsMap = visitor.allMethodsMap;
        currentClass = null;
        currentMethod = null;
        currentBlock = null;
        currentScope = null;
        scopes = new ArrayList<>();
    }

    @Override
    public T visitMainClass(MiniJavaGrammarParser.MainClassContext ctx) {
        currentClass = classes.get(ctx.Identifier(0).getText());
        try{
        cf = new ClassFile( false, currentClass.getClassName(), null);
        cf = ClassPool.getDefault().get(currentClass.getClassName()).getClassFile();
        Bytecode code = new Bytecode(cf.getConstPool());
        code.addAload(0);
        code.addInvokespecial("java/lang/Object", MethodInfo.nameInit, "()V");
        code.addReturn(null);
        minfo = new MethodInfo(cf.getConstPool(), MethodInfo.nameInit, "()V");
        minfo.setCodeAttribute(code.toCodeAttribute());
        cf.addMethod(minfo);
        } catch(Exception e){}
        return visitChildren(ctx);
    }

    @Override
    public T visitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
        currentClass = classes.get(ctx.Identifier(0).getText());
        return visitChildren(ctx);
    }

    @Override
    public T visitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
        currentMethod = allMethodsMap.get(ctx.Identifier(0).getText());
        currentBlock = currentMethod.getBlock();
        return visitChildren(ctx);
    }

    @Override
    public T visitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public T visitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) {

        return visitChildren(ctx);
    }

    @Override public T visitPrintLine(MiniJavaGrammarParser.PrintLineContext ctx) {

        return visitChildren(ctx);
    }
    @Override public T visitAssignment(MiniJavaGrammarParser.AssignmentContext ctx) { return visitChildren(ctx); }
    @Override public T visitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) { return visitChildren(ctx); }
    @Override public T visitExpression(MiniJavaGrammarParser.ExpressionContext ctx) { return null; }
    @Override public T visitEnd(MiniJavaGrammarParser.EndContext ctx) { return null; }
    @Override public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) { return visitChildren(ctx); }
    @Override public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) { return visitChildren(ctx); }
    @Override public T visitType(MiniJavaGrammarParser.TypeContext ctx) { return null; }
    @Override public T visitStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx) { return visitChildren(ctx); }
}

