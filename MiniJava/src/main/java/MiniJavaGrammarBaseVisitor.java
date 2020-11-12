// Generated from MiniJavaGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.HashMap;

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
		klass.setMainMethod(new CodeBlock(null, ctx.statement().statement()));
		classes.put(klass.getClassName(),klass);
		return visitChildren(ctx);
	}

	@Override public T visitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
		Klass klass = null;
		if(ctx.Identifier().size() > 1) klass = new Klass(ctx.Identifier(0).toString(), ctx.Identifier(1).toString());
		else klass = new Klass(ctx.Identifier(0).toString());
		for(MiniJavaGrammarParser.VarDeclarationContext v : ctx.varDeclaration()) klass.addGlobal(new Variable(v.type(),v.Identifier().getText(),null));
		classes.put(klass.getClassName(),klass);
		if(currentClass != null) classes.replace(currentClass.getClassName(),currentClass);
		currentClass = klass;
		return visitChildren(ctx);
	}

	@Override public T visitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
		currentMethod = new MethodDeclaration(ctx.type(0).toString(),ctx.Identifier(0).toString(),currentClass.getClassName());
		currentMethod.addParams(currentClass.getGlobals());
		if(ctx.Identifier().size() > 1)
			for (int i = 1; i < ctx.Identifier().size(); i++)
				currentMethod.addParam(new Variable(ctx.type(i), ctx.Identifier(i).getText(), null));
		currentBlock = new CodeBlock(currentMethod.getParams(), ctx.statement());
		for(MiniJavaGrammarParser.VarDeclarationContext v : ctx.varDeclaration())
			currentBlock.addVar(new Variable(v.type(), v.Identifier().getText(), null));
		currentBlock.setReturnStatement(ctx.expression());
		currentMethod.setBlock(currentBlock);
		currentMethod.setMethodName(ctx.Identifier().get(0).getText());
		classes.get(currentClass.getClassName()).addMethod(currentMethod);
		allMethodsMap.put(ctx.Identifier().get(0).getText(), currentMethod);
		allMethods.add(currentMethod);
		return visitChildren(ctx);
	}

	//Getter for this classes hashmap.
	//public HashMap<String, Klass> getClasses(){ return classes; }

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
	public boolean checkCyclic(){
		Klass temp = null;
		for(String s : classes.keySet()){
			ArrayList<Klass> seen = new ArrayList<>();
			if(classes.get(s).hasInheritedClass()) {
				temp = classes.get(s);
				seen.add(temp);
				while(temp.hasInheritedClass()){
						temp = temp.getInherited();
						for(Klass k : seen)
							if(k == temp){
								System.out.println("\u001B[31m" + "Cyclic inheritance involving " + seen.toString() +"\u001B[0m");
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
		Klass temp = null;
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
			for(int i = 0; i < m.getParams().size(); i++)
				for(int j = i+1; j < m.getParams().size(); j++)
					if(m.getParams().get(i).getIdentifier().equals(m.getParams().get(j).getIdentifier())){
						String output = "\u001B[31m" + "Identical declarations for the variable name " + m.getParams().get(j).getIdentifier();
						System.out.println(output + "\u001B[0m");
						return false;
					}
			System.out.println(m.getParams());
		}
		return symbolsAndTypes();
	}

	//Symbol resolution and Type Checking
	public boolean symbolsAndTypes(){
			for(MethodDeclaration method : allMethods){
				if(method.getBlock().getInstructions().size() > 0) {
					for (Object instruction : method.getBlock().getInstructions()) {
						MiniJavaGrammarParser.StatementContext inst = (MiniJavaGrammarParser.StatementContext) instruction;
						if (inst.getText().contains("=")) {
							if(inst.expression().size() == 2){
								if(!evaluateAssignmentExpression(inst.expression(0), method).equals("int")){
									String output = "\u001B[31m" + inst.expression(0).getText() + "is not of type int.";
									System.out.println(output + "\u001B[0m");
									return false;
								} else if (!evaluateAssignmentExpression(inst.expression(1), method).equals(method.getBlock().findVar(inst.Identifier().getText()).getType())){
									String output = "\u001B[31m" + inst.expression(0).getText() + "is not of type int.";
									System.out.println(output + "\u001B[0m");
									return false;
								}
							}
						}
					}
				}
			}
		return true;
	}

	//Evaluates an expression to its given type
	public String evaluateAssignmentExpression(MiniJavaGrammarParser.ExpressionContext expression, MethodDeclaration method){
		if(expression.getText().equals("true") || expression.getText().equals("false")) return "boolean";
		else if(expression.INTEGER_LITERAL() != null) return "int";
		else if(expression.DOUBLE_LITERAL() != null) return "double";
		else if(expression.getText().equals("this")) return method.className();
		else if(!expression.getText().contains("(") && expression.Identifier() != null) {
			if (method.getBlock().findVar(expression.Identifier().getText()) != null) {
				method.getBlock().findVar(expression.Identifier().getText()).getType().getText();
			}
			else {
				String output = "\u001B[31m" + expression.Identifier().getText() + "is not a valid symbol.";
				System.out.println(output + "\u001B[0m");
				System.exit(1);
			}
		} else if(expression.Identifier() != null && expression.getText().contains("new") && expression.expression().size() == 0){
			if(classes.get(expression.Identifier().getText()) != null){
				return expression.Identifier().getText();
			}else {
				String output = "\u001B[31m" + expression.Identifier().getText() + "is not a valid symbol.";
				System.out.println(output + "\u001B[0m");
				System.exit(1);
			}
		} else if(expression.getText().contains("new") && expression.Identifier() == null && expression.getText().contains("[")){
			//if()
		}
		return "";
	}

	@Override public T visitStatement(MiniJavaGrammarParser.StatementContext ctx) { return visitChildren(ctx); }
	@Override public T visitType(MiniJavaGrammarParser.TypeContext ctx) { return visitChildren(ctx); }
	@Override public T visitExpression(MiniJavaGrammarParser.ExpressionContext ctx) { return visitChildren(ctx); }
	@Override public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) { return visitChildren(ctx);}
	@Override public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) { return visitChildren(ctx); }

}