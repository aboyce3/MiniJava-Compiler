// Generated from MiniJavaGrammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 // Generated from MiniJavaGrammar.g4 by ANTLR 4.8
 import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;

 /**
 * This class provides an empty implementation of {@link MiniJavaGrammarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */

public class MiniJavaGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MiniJavaGrammarVisitor<T> {
	HashMap<String, Klass> classes = new HashMap<>();
	ArrayList<MethodDeclaration> allMethods = new ArrayList<>();
	HashMap<String, MethodDeclaration> allMethodsMap = new HashMap<>();
	Klass currentClass = null;
	MethodDeclaration currentMethod = null;
	CodeBlock currentBlock = null;

	@Override public T visitMainClass(MiniJavaGrammarParser.MainClassContext ctx) {
		Klass klass = new Klass(ctx.Identifier(0).toString());
		klass.setMainMethod(new CodeBlock(null, ctx.statement()));
		classes.put(klass.getClassName(),klass);
		return visitChildren(ctx);
	}

	@Override public T visitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
		Klass klass;
		if(ctx.Identifier().size() > 1) klass = new Klass(ctx.Identifier(0).toString(), ctx.Identifier(1).toString());
		else klass = new Klass(ctx.Identifier(0).toString());
		for(MiniJavaGrammarParser.VarDeclarationContext v : ctx.varDeclaration()) klass.addGlobal(new Variable(v.type(),v.Identifier().getText(),null));
		if(!classes.containsKey(klass.getClassName()))classes.put(klass.getClassName(),klass);
		else{
			String output = "\u001B[31m" + "Duplicate class names for " + klass.getClassName();
			System.out.println(output + "\u001B[0m");
			System.exit(0);
		}
		if(currentClass != null) classes.replace(currentClass.getClassName(),currentClass);
		currentClass = klass;
		return visitChildren(ctx);
	}

	@Override public T visitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
		currentMethod = new MethodDeclaration(ctx.type(0).toString(),ctx.Identifier(0).toString(),currentClass.getClassName());
		currentMethod.addTotal(currentClass.getGlobals());
		currentMethod.setReturnType(ctx.type(0).getText());
		if(ctx.Identifier().size() > 1)
			for (int i = 1; i < ctx.Identifier().size(); i++)
				currentMethod.addParam(new Variable(ctx.type(i), ctx.Identifier(i).getText(), null));
		currentBlock = new CodeBlock(currentMethod.getTotal(), ctx.statement());
		for(MiniJavaGrammarParser.VarDeclarationContext v : ctx.varDeclaration())
			currentBlock.addVar(new Variable(v.type(), v.Identifier().getText(), null));
		currentBlock.setReturnStatement(ctx.expression());
		currentMethod.setBlock(currentBlock);
		classes.get(currentClass.getClassName()).addMethod(currentMethod);
		allMethodsMap.put(ctx.Identifier().get(0).getText(), currentMethod);
		allMethods.add(currentMethod);
		return visitChildren(ctx);
	}

	//Links the objects with their corresponding references.
	public boolean link() {
		for(String s : classes.keySet())
			if(classes.get(s).getInheritedClassName() != null)
				classes.get(s).setInherited(classes.get(classes.get(s).getInheritedClassName()));
		for(String s : classes.keySet())
			if (classes.get(s).hasInheritedClass())
				if(classes.get(s).getInherited().getInheritedClassName() != null && !classes.get(s).getInherited().hasInheritedClass())
					classes.get(s).getInherited().setInherited(classes.get(classes.get(s).getInherited().getInheritedClassName()));
		return checkCyclic();
	}

	//Cyclic inheritance check.
	public boolean checkCyclic() {
		Klass temp;
		for (String s : classes.keySet()) {
			ArrayList<Klass> seen = new ArrayList<>();
			if (classes.get(s).hasInheritedClass()) {
				temp = classes.get(s);
				seen.add(temp);
				while (temp.hasInheritedClass()) {
					temp = temp.getInherited();
					for (Klass k : seen)
						if (k == temp) {
							System.out.println("\u001B[31m" + "Cyclic inheritance involving " + seen.toString() + "\u001B[0m");
							return false;
						}
					seen.add(temp);
				}
			}
		}
		return overloadAndOverrideCheck();
	}

	//Checks for Overload and Override errors.
	public boolean overloadAndOverrideCheck(){
		Klass temp;
		for(String s : classes.keySet()){
			ArrayList<MethodDeclaration> seen = new ArrayList<>();
			if(classes.get(s).hasInheritedClass()) {
				temp = classes.get(s);
				seen.addAll(temp.getMethods());
				while(temp.hasInheritedClass()){
					temp = temp.getInherited();
					seen.addAll(temp.getMethods());
				}
			}
			for(int i = 0; i < seen.size(); i++)
				for(int j = i+1; j <seen.size(); j++)
					for(int k = 0; k < seen.get(i).getParams().size(); k++)
						for(int m = 0; m < seen.get(j).getParams().size(); m++)
							if(seen.get(i).getIdentifier().equals(seen.get(j).getIdentifier()) && seen.get(i).className().equals(seen.get(j).className()))
								if(seen.get(i).getParams().get(k).getType().equals(seen.get(i).getParams().get(m).getType())){
									String output = "\u001B[31m" + "Overload/Override error for " + seen.get(i);
									System.out.println(output + "\u001B[0m");
									return false;
								}
		}
		return declarationCheck();
	}

	//Checked for duplicate declarations
	public boolean declarationCheck() {
		for(MethodDeclaration m : allMethods){
			for(int i = 0; i < m.getTotal().size(); i++) {
				for (int j = i + 1; j < m.getTotal().size(); j++) {
					String s = m.getTotal().get(i).getIdentifier();
					String t = m.getTotal().get(j).getIdentifier();
					if (s.equals(t)) {
						String output = "\u001B[31m" + "Identical declarations for the variable name " + m.getTotal().get(j).getIdentifier();
						System.out.println(output + "\u001B[0m");
						return false;
					}
				}
			}
		}
		return typeCheckAndSymbolsResolution();
	}

	//Type checking and symbol resolution
	public boolean typeCheckAndSymbolsResolution() {
		for(MethodDeclaration method : allMethods){
			if(method.getBlock().getInstructions().size() > 0) {
				for (Object instruction : method.getBlock().getInstructions()) {
					MiniJavaGrammarParser.StatementContext inst = (MiniJavaGrammarParser.StatementContext) instruction;
					/*if(inst instanceof MiniJavaGrammarParser.WhileStatementContext){
						String s = evaluateExpressions(inst.expression(0),method);
						if(!s.equals("boolean")){
							String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type boolean at line number " + inst.start.getLine();
							System.out.println(output + "\u001B[0m");
							System.exit(0);
						}else {
							Boolean b = helper(method, inst.statement(0).statement());
						}
					}
					else if(inst instanceof MiniJavaGrammarParser.IfStatementContext){
						String s = evaluateExpressions(inst.expression(0),method);
						if(!s.equals("boolean")){
							String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type boolean at line number " + inst.start.getLine();
							System.out.println(output + "\u001B[0m");
							System.exit(0);
						}else {
							Boolean b = helper(method, inst.statement(0).statement());
							Boolean c = helper(method, inst.statement(1).statement());
						}
					}*/
					if (inst instanceof MiniJavaGrammarParser.AssignmentContext) {
						boolean found = false;
						String s = evaluateExpressions(((MiniJavaGrammarParser.AssignmentContext) inst).expression(),method);
						System.out.println(((MiniJavaGrammarParser.AssignmentContext) inst).expression().getText());
						for(Variable v : method.getTotal()){
							if(v.getIdentifier().equals(((MiniJavaGrammarParser.AssignmentContext) inst).Identifier().getText())){
								if(v.getType().getText().equals(s)){
								found = true;
								}
							}
						}
						if(!found){
						String output = "\u001B[31m" + ((MiniJavaGrammarParser.AssignmentContext) inst).expression().getText() + " is not of type at line number " + inst.start.getLine();
						System.out.println(output + "\u001B[0m");
						return false;
						}
					}
					else if(inst instanceof MiniJavaGrammarParser.PrintStatementContext){
						String s = evaluateExpressions(((MiniJavaGrammarParser.PrintStatementContext) inst).expression(),method);
						if(!s.equals("int")){
							String output = "\u001B[31m" + ((MiniJavaGrammarParser.PrintStatementContext) inst).expression().getText() + " is not of type at line number " + inst.start.getLine();
							System.out.println(output + "\u001B[0m");
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	//Evaluates an expression to its given type
	public String evaluateExpressions(MiniJavaGrammarParser.ExpressionContext expression, MethodDeclaration method){
		 if(expression instanceof MiniJavaGrammarParser.MethodCallContext){
			String left = evaluateExpressions(((MiniJavaGrammarParser.MethodCallContext) expression).expression(0),method);
			ArrayList<String> params = new ArrayList<>();
			for(int i = 1; i < ((MiniJavaGrammarParser.MethodCallContext) expression).expression().size(); i++){
				String param = evaluateExpressions(((MiniJavaGrammarParser.MethodCallContext) expression).expression(i), method);
				params.add(param);
			}
			if(classes.get(left) != null)
				for(MethodDeclaration m : classes.get(left).getMethods())
					if(m.match(((MiniJavaGrammarParser.MethodCallContext) expression).Identifier().getText(),params))
						return m.getReturnType();
			String output = "\u001B[31m" + expression.getText() + " is not a valid symbol.";
			System.out.println(output + "\u001B[0m");
			System.exit(0);
		}
		else if(expression instanceof MiniJavaGrammarParser.IdentifierContext) {
			for (Variable v : method.getTotal())
				if (v.getIdentifier().equals(((MiniJavaGrammarParser.IdentifierContext) expression).Identifier().getText()))
					return v.getType().getText();
			String output = "\u001B[31m" + expression.getText() + " is not a valid symbol.";
			System.out.println(output + "\u001B[0m");
			System.exit(0);
		}
		else if(expression instanceof MiniJavaGrammarParser.ParenthesesContext)
			evaluateExpressions(((MiniJavaGrammarParser.ParenthesesContext) expression).expression(),method);
		else if(expression instanceof MiniJavaGrammarParser.BooleanNegationContext) {
			if (evaluateExpressions(((MiniJavaGrammarParser.BooleanNegationContext) expression).expression(), method).equals("boolean"))
				return "boolean";
		}
		else if(expression instanceof MiniJavaGrammarParser.LengthContext){
			String s = evaluateExpressions(((MiniJavaGrammarParser.LengthContext) expression).expression(),method);
			if(s.equals("int[]") || s.equals("double[]"))
				return "int";
			else {
				String output = "\u001B[31m" + expression.getText() + " is not of type int[] or double[].";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression instanceof MiniJavaGrammarParser.ArrayIndexContext){
			String s = evaluateExpressions(((MiniJavaGrammarParser.ArrayIndexContext) expression).expression(0),method);
			String m = evaluateExpressions(((MiniJavaGrammarParser.ArrayIndexContext) expression).expression(1),method);
			if(s.equals("int[]") && m.equals("int"))
				return "int";
			else if(s.equals("double[]") && m.equals("int"))
				return "double";
			else{
				String output = "\u001B[31m" + expression.getText() + " is not of type int[] or double[].";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression instanceof MiniJavaGrammarParser.NewIntArrayContext){
			String s = evaluateExpressions(((MiniJavaGrammarParser.NewIntArrayContext) expression).expression(),method);
			if(s.equals("int")) return "int[]";
			else{
				String output = "\u001B[31m" + ((MiniJavaGrammarParser.NewIntArrayContext) expression).expression().getText() + " is not of type int.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression instanceof MiniJavaGrammarParser.NewDoubleArrayContext){
			String s = evaluateExpressions(((MiniJavaGrammarParser.NewDoubleArrayContext) expression).expression(),method);
			if(s.equals("int")) return "double[]";
			else{
				String output = "\u001B[31m" + ((MiniJavaGrammarParser.NewDoubleArrayContext) expression).expression().getText() + " is not of type int.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression instanceof MiniJavaGrammarParser.ConstructorContext){
			String s = ((MiniJavaGrammarParser.ConstructorContext) expression).Identifier().getText();
			Klass k = classes.get(s);
			if(k != null){
				return k.getClassName();
			}
			else{
				String output = "\u001B[31m" + expression.getText() + " is not a valid symbol.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		if(expression instanceof MiniJavaGrammarParser.TrueContext) return "boolean";
		else if(expression instanceof MiniJavaGrammarParser.FalseContext) return "boolean";
		else if(expression instanceof MiniJavaGrammarParser.ThisContext) return method.className();
		else if(expression instanceof MiniJavaGrammarParser.INTEGER_LITERALContext) return "int";
		else if(expression instanceof MiniJavaGrammarParser.DOUBLE_LITERALContext) return "double";
		return "";
	}

	@Override public T visitType(MiniJavaGrammarParser.TypeContext ctx) { return visitChildren(ctx); }
	@Override public T visitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) { return visitChildren(ctx); }
	@Override public T visitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) { return visitChildren(ctx); }
	@Override public T visitBlock(MiniJavaGrammarParser.BlockContext ctx) { return visitChildren(ctx); }
	@Override public T visitPrintStatement(MiniJavaGrammarParser.PrintStatementContext ctx) { return visitChildren(ctx); }
	@Override public T visitAssignment(MiniJavaGrammarParser.AssignmentContext ctx) { return visitChildren(ctx); }
	@Override public T visitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) { return visitChildren(ctx); }
	@Override public T visitDOUBLE_LITERAL(MiniJavaGrammarParser.DOUBLE_LITERALContext ctx) { return null; }
	@Override public T visitNewDoubleArray(MiniJavaGrammarParser.NewDoubleArrayContext ctx) { return null; }
	@Override public T visitConstructor(MiniJavaGrammarParser.ConstructorContext ctx) { return null; }
	@Override public T visitTrue(MiniJavaGrammarParser.TrueContext ctx) { return null; }
	@Override public T visitINTEGER_LITERAL(MiniJavaGrammarParser.INTEGER_LITERALContext ctx) { return null; }
	@Override public T visitFalse(MiniJavaGrammarParser.FalseContext ctx) { return null; }
	@Override public T visitOperation(MiniJavaGrammarParser.OperationContext ctx) { return null; }
	@Override public T visitArrayIndex(MiniJavaGrammarParser.ArrayIndexContext ctx) { return null; }
	@Override public T visitMethodCall(MiniJavaGrammarParser.MethodCallContext ctx) { return null; }
	@Override public T visitIdentifier(MiniJavaGrammarParser.IdentifierContext ctx) { return null; }
	@Override public T visitLength(MiniJavaGrammarParser.LengthContext ctx) { return null; }
	@Override public T visitThis(MiniJavaGrammarParser.ThisContext ctx) { return null; }
	@Override public T visitBooleanNegation(MiniJavaGrammarParser.BooleanNegationContext ctx) { return null; }
	@Override public T visitNewIntArray(MiniJavaGrammarParser.NewIntArrayContext ctx) { return null; }
	@Override public T visitParentheses(MiniJavaGrammarParser.ParenthesesContext ctx) {return visitChildren(ctx); }
	@Override public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) { return visitChildren(ctx);}
	@Override public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) { return visitChildren(ctx); }

}