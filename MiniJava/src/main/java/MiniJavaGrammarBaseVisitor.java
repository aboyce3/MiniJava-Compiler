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
		}
		symbolsAndTypes();
		return true;
	}

	//Symbol resolution and Type Checking
	public void symbolsAndTypes(){
		for(MethodDeclaration method : allMethods){
			if(method.getBlock().getInstructions().size() > 0) {
				for (Object instruction : method.getBlock().getInstructions()) {
					MiniJavaGrammarParser.StatementContext inst = (MiniJavaGrammarParser.StatementContext) instruction;
					if (inst.getText().contains("=") && inst.Identifier() != null) {
						if (inst.expression().size() == 2) {
							String s = evaluateExpressions(inst.expression(1), method);
							String m = method.getBlock().findVar(inst.Identifier().getText()).getType().getText();
							if (!evaluateExpressions(inst.expression(0), method).equals("int")) {
								String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
								System.out.println(output + "\u001B[0m");
								System.exit(0);
							} else if (!s.equals(m)) {
								String output = "\u001B[31m" + inst.expression(0).getText() + " is not of the same type.";
								System.out.println(output + "\u001B[0m");
								System.exit(0);
							}
						} else if(inst.expression().size() == 1){
							String s = evaluateExpressions(inst.expression(0), method);
							String m = method.getBlock().findVar(inst.Identifier().getText()).getType().getText();
							if (!s.equals(m)) {
								String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
								System.out.println(output + "\u001B[0m");
								System.exit(0);
							}
						}
					} else if(inst.getText().contains("while")){
						if(evaluateExpressions(inst.expression(0),method).equals("boolean")){
							boolean b = ifWhileHelper(inst.statement(0).statement(),method);
						}
					}else if(inst.getText().contains("{")){
						boolean b = ifWhileHelper(inst.statement(),method);
					}
					else if(inst.getText().contains("if")){
						if(evaluateExpressions(inst.expression(0),method).equals("boolean")){
							if(ifWhileHelper(inst.statement(0).statement(),method)){
								Boolean s = ifWhileHelper(inst.statement(1).statement(),method);
							}
						}
					}
					else if(inst.getText().contains("System")){
						String s = evaluateExpressions(inst.expression(0),method);
						boolean b = s.equals("int");
					}
				}
			}
		}
	}

	public boolean ifWhileHelper(java.util.List<MiniJavaGrammarParser.StatementContext> list, MethodDeclaration method){
		if(method.getBlock().getInstructions().size() > 0) {
		for (Object instruction : list) {
			MiniJavaGrammarParser.StatementContext inst = (MiniJavaGrammarParser.StatementContext) instruction;
			if (inst.getText().contains("=") && inst.Identifier() != null) {
				if (inst.expression().size() == 2) {
					String s = evaluateExpressions(inst.expression(1), method);
					String m = method.getBlock().findVar(inst.Identifier().getText()).getType().getText();
					if (!evaluateExpressions(inst.expression(0), method).equals("int")) {
						String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
						System.out.println(output + "\u001B[0m");
						System.exit(0);
					} else if (!s.equals(m)) {
						String output = "\u001B[31m" + inst.expression(0).getText() + " is not of the same type.";
						System.out.println(output + "\u001B[0m");
						System.exit(0);
					}
				} else if(inst.expression().size() == 1){
					String s = evaluateExpressions(inst.expression(0), method);
					String m = method.getBlock().findVar(inst.Identifier().getText()).getType().getText();
					if (!s.equals(m)) {
						String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
						System.out.println(output + "\u001B[0m");
						System.exit(0);
					}
				}
			}else if(inst.expression().size() == 1 && inst.statement().size() == 0){
				System.out.println(inst.getText());
				Variable v = method.getBlock().findVar(inst.Identifier().getText());
				if(v == null){
					String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
					System.out.println(output + "\u001B[0m");
					System.exit(0);
				}
				else if (!evaluateExpressions(inst.expression(0), method).equals(v.getType().getText())){
					String output = "\u001B[31m" + inst.expression(0).getText() + " is not of type int.";
					System.out.println(output + "\u001B[0m");
					System.exit(0);
				}
			}
			else if(inst.getText().contains("while")){
				if(evaluateExpressions(inst.expression(0),method).equals("boolean")){
					return ifWhileHelper(inst.statement(0).statement(),method);
				}
			}
			else if(inst.getText().contains("if")){
				if(evaluateExpressions(inst.expression(0),method).equals("boolean")){
					if(ifWhileHelper(inst.statement(0).statement(),method)){
						return ifWhileHelper(inst.statement(1).statement(),method);
					}
				}
			}
			else if(inst.getText().contains("System")){
				String s = evaluateExpressions(inst.expression(0),method);
				return s.equals("int");
			}
		}
	}
		return true;
	}

	//Evaluates an expression to its given type
	public String evaluateExpressions(MiniJavaGrammarParser.ExpressionContext expression, MethodDeclaration method){
		if(expression.getText().equals("true") || expression.getText().equals("false")) return "boolean";
		else if(expression.INTEGER_LITERAL() != null && expression.getText().equals(expression.INTEGER_LITERAL().getText())) return "int";
		else if(expression.DOUBLE_LITERAL() != null && expression.getText().equals(expression.DOUBLE_LITERAL().getText())) return "double";
		else if(expression.getText().equals("this")) return method.className();
		else if(expression.Identifier() != null && expression.getText().contains("new") && expression.expression().size() == 0){
			if(classes.get(expression.Identifier().getText()) != null){
				return expression.Identifier().getText();
			}else {
				String output = "\u001B[31m" + expression.Identifier().getText() + " is not a valid symbol.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.getText().contains("new") && expression.getText().contains("double") && expression.getText().contains("[")){
			if(!evaluateExpressions(expression.expression(0),method).equals("int")){
				String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
			return "double[]";
		}
		else if(expression.getText().contains("new") && expression.getText().contains("int") && expression.getText().contains("[")){
			if(!evaluateExpressions(expression.expression(0),method).equals("int")){
				String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
			return "int[]";
		}
		else if(expression.getText().contains("!") ){
			if(!evaluateExpressions(expression.expression(0),method).equals("boolean")) {
				String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type boolean.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}else return "boolean";
		}
		else if(expression.getText().contains(".") && expression.getText().contains("length")){
			String result = evaluateExpressions(expression.expression(0),method);
			if(result.equals("int") || result.equals("double")) {
				return "int";
			}else{
				String output = "\u001B[31m" + expression.Identifier().getText() + " is not of type int or double.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.expression().size() == 2 && expression.getText().contains("[")){
			String result = evaluateExpressions(expression.expression(0),method);
			String resultRight = evaluateExpressions(expression.expression(1),method);
			if((result.equals("int") || result.equals("double")) && resultRight.equals("int")) {
				return result + "[]";
			}else{
				String output = "\u001B[31m";
				output += resultRight.equals("int") ? expression.expression(0).getText() + " is not of type int or double."
						: expression.expression(1).getText() + "is not of type int.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.getText().contains(".") && expression.Identifier() != null && expression.getText().contains("(")){
			Klass k = classes.get(evaluateExpressions(expression.expression(0),method));
			if(k != null){
				ArrayList<MethodDeclaration> methods = k.getMethods();
				while(true) {
					for (MethodDeclaration methodDeclaration : methods) {
						boolean correct = true;
						String m = methodDeclaration.getMethodName();
						String t = expression.Identifier().getText();
						if (m.equals(t)) {
							if(expression.expression().size()-1 == methodDeclaration.getParams().size()){
								for(int i = 0; i < methodDeclaration.getParams().size(); i++){
									if(correct)
									if(!methodDeclaration.getParams().get(i).getType().getText().equals(evaluateExpressions(expression.expression(i),method)))
										correct = true;
									else correct = false;
								}
								if(correct) return methodDeclaration.getReturnType();
							}
						}
					}
					if(k.hasInheritedClass()){
						k = k.getInherited();
						methods = k.getMethods();
					}else{
						String output = "\u001B[31m" + expression.Identifier().getText() + " is not a valid symbol.";
						System.out.println(output + "\u001B[0m");
						System.exit(0);
					}
				}
			}else{
				String output = "\u001B[31m" + expression.expression(0).getText() + " is not a valid symbol.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.Identifier() == null && expression.expression().size() == 1){
			return evaluateExpressions(expression.expression(0),method);
		}
		else if(expression.Identifier() != null && expression.getText().equals(expression.Identifier().getText())){
			for(Variable v : method.getTotal()){
				if(v.getIdentifier().equals(expression.Identifier().getText()))
					return v.getType().getText();
			}
		}
		else if(expression.getText().contains("+") || expression.getText().contains("-") || expression.getText().contains("*")){
			String left = evaluateExpressions(expression.expression(0),method);
			String right = evaluateExpressions(expression.expression(1),method);
					if(left.equals("int") && right.equals("int")) return "int";
					else if (left.equals("int") && right.equals("double")) return "double";
					else if (left.equals("double") && right.equals("int")) return "double";
					else if (left.equals("double") && right.equals("double")) return "double";
					else{
						String output = "\u001B[31m" + expression.getText() + " is not of type int or double.";
						System.out.println(output + "\u001B[0m");
						System.exit(0);
					}
		}
		else if(expression.getText().contains("&&")){
			String left = evaluateExpressions(expression.expression(0),method);
			String right = evaluateExpressions(expression.expression(1),method);
			if(left.equals("boolean") && right.equals("boolean")){
				return "boolean";
			}else{
				String output = "\u001B[31m" + expression.getText() + " is not of type boolean.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.getText().contains("<")){
			String left = evaluateExpressions(expression.expression(0),method);
			String right = evaluateExpressions(expression.expression(1),method);
			if(left.equals("int") && right.equals("int")) return "boolean";
			else if (left.equals("int") && right.equals("double")) return "boolean";
			else if (left.equals("double") && right.equals("int")) return "boolean";
			else if (left.equals("double") && right.equals("double")) return "boolean";
			else{
				String output = "\u001B[31m" + expression.getText() + " is not of type int or double.";
				System.out.println(output + "\u001B[0m");
				System.exit(0);
			}
		}
		else if(expression.Identifier() != null && expression.getText().contains("new") && expression.expression().size() == 1){
			return evaluateExpressions(expression.expression(0),method);
		}
		return "Developer Error";
	}

	@Override public T visitStatement(MiniJavaGrammarParser.StatementContext ctx) { return visitChildren(ctx); }
	@Override public T visitType(MiniJavaGrammarParser.TypeContext ctx) { return visitChildren(ctx); }
	@Override public T visitExpression(MiniJavaGrammarParser.ExpressionContext ctx) { return visitChildren(ctx); }
	@Override public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) { return visitChildren(ctx);}
	@Override public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) { return visitChildren(ctx); }

}