import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SecondVisitor<T> extends AbstractParseTreeVisitor<T> implements MiniJavaGrammarVisitor<T> {

    HashMap<String, Klass> classes;
    ArrayList<MethodDeclaration> allMethods;
    HashMap<String, MethodDeclaration> allMethodsMap;
    Klass currentClass;
    MethodDeclaration currentMethod;
    CodeBlock currentBlock;

    public SecondVisitor(MiniJavaGrammarBaseVisitor visitor){
        classes = visitor.classes;
        allMethods = visitor.allMethods;
        allMethodsMap = visitor.allMethodsMap;
        currentClass = null;
        currentMethod = null;
        currentBlock = null;
    }

    @Override
    public T visitMainClass(MiniJavaGrammarParser.MainClassContext ctx) {
        currentClass = classes.get(ctx.Identifier(0).getText());
        return visitChildren(ctx);
    }

    @Override
    public T visitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
        currentClass = classes.get(ctx.Identifier(0).getText());
        return visitChildren(ctx);
    }

    @Override
    public T visitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
        for(MethodDeclaration method : currentClass.getMethods())
            if(method.getIdentifier().equals(ctx.Identifier(0).getText())) {
                currentMethod = method;
                break;
            }
        currentBlock = currentMethod.getBlock();
        return visitChildren(ctx);
    }

    @Override
    public T visitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) {
        if(!evaluateExpressions(ctx.expression()).equals("boolean")) {
            String output = "\u001B[31m" + ctx.expression().getText() + " is not of type boolean " +  ctx.start.getLine() +".";
            System.out.println(output + "\u001B[0m");
        }
        return visitChildren(ctx);
    }

    @Override
    public T visitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) {
        if(!evaluateExpressions(ctx.expression()).equals("boolean")) {
            String output = "\u001B[31m" + ctx.expression().getText() + " is not of type boolean " +  ctx.start.getLine() +".";
            System.out.println(output + "\u001B[0m");
        }
        return visitChildren(ctx);
    }

    @Override
    public T visitPrintLine(MiniJavaGrammarParser.PrintLineContext ctx) {
        if(!evaluateExpressions(ctx.expression()).equals("int")){
            String output = "\u001B[31m" + ctx.expression().getText() + " is not of type int at line "+  ctx.start.getLine() +".";
            System.out.println(output + "\u001B[0m");
            return visitChildren(ctx);
        }
        return visitChildren(ctx);
    }

    @Override
    public T visitAssignment(MiniJavaGrammarParser.AssignmentContext ctx) {
        String s = evaluateExpressions(ctx.expression());
        Klass k = classes.get(currentMethod.className());
        if(k.hasInheritedClass()) {
            for (Variable v : currentMethod.getTotal()) {
            if (v.getIdentifier().equals(ctx.Identifier().getText())) {
                if (v.getType().getText().equals(s))
                    return visitChildren(ctx);
            }
        }
            while(k.hasInheritedClass()) {
                k = k.getInherited();
                for (Variable v : k.getGlobals()) {
                    if (v.getIdentifier().equals(ctx.Identifier().getText())) {
                        if (v.getType().getText().equals(s))
                            return visitChildren(ctx);
                    }
                }
            }
        }else{
            for (Variable v : currentMethod.getTotal()) {
                if (v.getIdentifier().equals(ctx.Identifier().getText())) {
                    if (v.getType().getText().equals(s))
                        return visitChildren(ctx);
                }
            }
        }
        if(!s.equals("Developer Error")) {
            String output = "\u001B[31m" + ctx.Identifier().getText() + " is not of the same type as "+ ctx.expression().getText() +" at line " + ctx.start.getLine();
            System.out.println(output + "\u001B[0m");
        }
        return visitChildren(ctx);
    }

    @Override
    public T visitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) {
        String left = "",right = "";
        boolean found = true;
        String indexType = evaluateExpressions(ctx.expression(0));
        if(indexType.equals("int")) {
            for(Variable v : currentMethod.getTotal()){
                if(v.getIdentifier().equals(ctx.Identifier().getText())){
                    left = v.getType().getText();
                    right = evaluateExpressions(ctx.expression(1));
                    if(left.equals(right))
                        return visitChildren(ctx);
                    else if(left.equals("int[]") && right.equals("int"))
                        return visitChildren(ctx);
                    else if(left.equals("double[]") && right.equals("double"))
                        return visitChildren(ctx);
                    found = false;
                }
            }
        }else {
            String output = "\u001B[31m" + ctx.expression(0).getText() + " is not of type int at line " + ctx.start.getLine();
            System.out.println(output + "\u001B[0m");
        }
        if(!found){
            String output = "\u001B[31m" + ctx.expression(0).getText() + " is not of the same type as "+ right +" at line " + ctx.start.getLine();
            System.out.println(output + "\u001B[0m");
        }
        return visitChildren(ctx);
    }

    public String evaluateExpressions(MiniJavaGrammarParser.ExpressionContext expression){
        if(expression.getText().equals("true") || expression.getText().equals("false")) return "boolean";
        else if(expression.INTEGER_LITERAL() != null && expression.getText().equals(expression.INTEGER_LITERAL().getText())) return "int";
        else if(expression.DOUBLE_LITERAL() != null && expression.getText().equals(expression.DOUBLE_LITERAL().getText())) return "double";
        else if(expression.getText().equals("this")) return currentMethod.className();
        else if(expression.Identifier() != null && expression.getText().contains("new") && expression.expression().size() == 0 && expression.getText().contains("()")){
            if(classes.get(expression.Identifier().getText()) != null){
                return expression.Identifier().getText();
            }else {
                String output = "\u001B[31m" + expression.Identifier().getText() + " is not a valid symbol at line number " + expression.start.getLine() + ".";
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.getText().contains("new") && expression.getText().contains("double") && expression.getText().contains("[")){
            if(!evaluateExpressions(expression.expression(0)).equals("int")){
                String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
            return "double[]";
        }
        else if(expression.getText().contains("new") && expression.getText().contains("int") && expression.getText().contains("[")){
            if(!evaluateExpressions(expression.expression(0)).equals("int")){
                String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
            return "int[]";
        }
        else if(expression.getText().contains("!") ){
            if(!evaluateExpressions(expression.expression(0)).equals("boolean")) {
                String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type boolean at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }else return "boolean";
        }
        else if(expression.getText().contains(".") && expression.getText().contains("length")){
            String result = evaluateExpressions(expression.expression(0));
            if(result.equals("int[]") || result.equals("double[]")) {
                return "int";
            }else{
                String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int or double at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.expression().size() == 2 && expression.getText().contains("[")){
            String result = evaluateExpressions(expression.expression(0));
            result = evaluateExpressions(expression.expression(0)).substring(0,result.indexOf("["));
            String resultRight = evaluateExpressions(expression.expression(1));
            if(((result.equals("int") || result.equals("double"))) && resultRight.equals("int")) {
                return result;
            }else{
                String output = "\u001B[31m";
                output += resultRight.equals("int") ? expression.expression(0).getText() + " is not of type int or double at line number " + expression.start.getLine()
                        : expression.expression(1).getText() + " is not of type int at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.getText().contains(".") && expression.Identifier() != null && expression.getText().contains("(")){
            Klass k = classes.get(evaluateExpressions(expression.expression(0)));
            if(k != null){
                ArrayList<MethodDeclaration> methods = k.getMethods();
                ArrayList<String> paramTypes = new ArrayList<>();
                if(expression.expression().size() == 1){
                    for(MethodDeclaration method : methods){
                        if(method.getIdentifier().equals(expression.Identifier().getText()) && method.getParams().size() == 0){
                            return method.getReturnType();
                        }
                    }
                }
                else if(expression.expression().size() > 1){
                    for(int i = 1; i < expression.expression().size(); i++) {
                        String temp = evaluateExpressions(expression.expression(i));
                        paramTypes.add(temp);
                    }
                    for(MethodDeclaration method : methods)
                       if(method.getIdentifier().equals(expression.Identifier().getText())){
                           boolean found = true;
                           for(int i = 0; i < method.getParams().size() && found; i++){
                               if(!method.getParams().get(i).getType().getText().equals(paramTypes.get(i))){
                                   if(classes.get(paramTypes.get(i)) != null){
                                       Klass c = classes.get(paramTypes.get(i));
                                       while(c.hasInheritedClass()){
                                           c = c.getInherited();
                                           if(method.getParams().get(i).getType().getText().equals(c.getClassName())){
                                               found = true;
                                               c = null;
                                               break;
                                           }
                                       }
                                   }
                                   else {
                                       found = false;
                                   }
                               }
                             }
                           if(found) return method.getReturnType();
                       }
                }
                else{
                    String output = "\u001B[31m" + expression.expression(0).getText() + " is not a valid symbol at line number " + expression.start.getLine();
                    System.out.println(output + "\u001B[0m");
                }
            }else{
                String output = "\u001B[31m" + expression.expression(0).getText() + " is not a valid symbol at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.Identifier() == null && expression.expression().size() == 1){
            return evaluateExpressions(expression.expression(0));
        }
        else if(expression.Identifier() != null && expression.getText().equals(expression.Identifier().getText())) {
            Klass k = classes.get(currentMethod.className());
            if (k.hasInheritedClass()) {
                for (Variable v : currentMethod.getTotal()) {
                    if (v.getIdentifier().equals(expression.Identifier().getText())) {
                            return v.getType().getText();
                    }
                }
                while (k.hasInheritedClass()) {
                    k = k.getInherited();
                    for (Variable v : k.getGlobals()) {
                        if (v.getIdentifier().equals(expression.Identifier().getText())) {
                                return v.getType().getText();
                        }
                    }
                }
            } else {
                for (Variable v : currentMethod.getTotal()) {
                    if (v.getIdentifier().equals(expression.Identifier().getText()))
                        return v.getType().getText();
                }
                String output = "\u001B[31m" + expression.getText() + " is not a valid symbol at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.getText().contains("&&")){
            String left = evaluateExpressions(expression.expression(0));
            String right = evaluateExpressions(expression.expression(1));
            if(left.equals("boolean") && right.equals("boolean")){
                return "boolean";
            }else{
                String output = "\u001B[31m" + expression.getText() + " is not of type boolean at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.getText().contains("<")){
            String left = evaluateExpressions(expression.expression(0));
            String right = evaluateExpressions(expression.expression(1));
            if(left.equals("int") && right.equals("int")) return "boolean";
            else if (left.equals("int") && right.equals("double")) return "boolean";
            else if (left.equals("double") && right.equals("int")) return "boolean";
            else if (left.equals("double") && right.equals("double")) return "boolean";
            else{
                String output = "\u001B[31m" + expression.getText() + " is not of type int or double at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.getText().contains("+") || expression.getText().contains("-") || expression.getText().contains("*")){
            String left = evaluateExpressions(expression.expression(0));
            String right = evaluateExpressions(expression.expression(1));
            if(left.equals("int") && right.equals("int")) return "int";
            else if (left.equals("int") && right.equals("double")) return "double";
            else if (left.equals("double") && right.equals("int")) return "double";
            else if (left.equals("double") && right.equals("double")) return "double";
            else{
                String output = "\u001B[31m" + expression.getText() + " is not of type int or double at line number " + expression.start.getLine();
                System.out.println(output + "\u001B[0m");
            }
        }
        else if(expression.Identifier() != null && expression.getText().contains("new") && expression.expression().size() == 1){
            return evaluateExpressions(expression.expression(0));
        }
        else {
            return evaluateExpressions(expression.expression(0));
        }
        return "Developer Error";
    }

    @Override
    public T visitExpression(MiniJavaGrammarParser.ExpressionContext ctx) {
        return null;
    }

    @Override
    public T visitEnd(MiniJavaGrammarParser.EndContext ctx) {
        return null;
    }

    @Override
    public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitType(MiniJavaGrammarParser.TypeContext ctx) {
        return null;
    }

    @Override
    public T visitStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx) {
        return visitChildren(ctx);
    }

}

