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

public class Linker<T> extends AbstractParseTreeVisitor<T> implements MiniJavaGrammarVisitor<T> {
	HashMap<String, Klass> classes = new HashMap<>();
	ArrayList<MethodDeclaration> allMethods = new ArrayList<>();
	HashMap<String, MethodDeclaration> allMethodsMap = new HashMap<>();
	Klass currentClass = null;
	MethodDeclaration currentMethod = null;
	CodeBlock currentBlock = null;
	boolean errorNotFound = true;

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
		classes.put(klass.getClassName(),klass);
		if(currentClass != null) classes.replace(currentClass.getClassName(),currentClass);
		currentClass = klass;
		return visitChildren(ctx);
	}

	@Override public T visitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
		currentMethod = new MethodDeclaration(ctx.type(0).toString(),ctx.Identifier(0).toString(),currentClass.getClassName(),ctx.expression());
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
	public void link() {
		for(String s : classes.keySet())
			if(classes.get(s).getInheritedClassName() != null)
				classes.get(s).setInherited(classes.get(classes.get(s).getInheritedClassName()));
		for(String s : classes.keySet())
			if (classes.get(s).hasInheritedClass())
				if(classes.get(s).getInherited().getInheritedClassName() != null && !classes.get(s).getInherited().hasInheritedClass())
					classes.get(s).getInherited().setInherited(classes.get(classes.get(s).getInherited().getInheritedClassName()));
		checkCyclic();
	}

	//Cyclic inheritance check.
	public void checkCyclic(){
		Klass temp;
		boolean stop = false;
		for(String s : classes.keySet()){
			ArrayList<Klass> seen = new ArrayList<>();
			if(classes.get(s).hasInheritedClass()) {
				temp = classes.get(s);
				seen.add(temp);
				while(temp.hasInheritedClass()){
					temp = temp.getInherited();
					for(Klass k : seen)
						if (k == temp && !stop) {
							System.out.println("\u001B[31m" + "Cyclic inheritance involving " + seen.toString() + "\u001B[0m");
							stop = true;
							errorNotFound = false;
						}
						if (stop) break;

					seen.add(temp);
				}
			}
		}
		overloadAndOverrideCheck();
	}

	//Check for Overload and Override errors.
	public void overloadAndOverrideCheck() {
		for (String s : classes.keySet()) {
			ArrayList<MethodDeclaration> declarations = classes.get(s).getMethods();
			for (int i = 0; i < declarations.size(); i++)
				for (int j = i+1; j < declarations.size(); j++) {
					String a = declarations.get(i).getIdentifier();
					String b = declarations.get(j).getIdentifier();
					if (a.equals(b)) {
						if (declarations.get(i).getParams().size() == declarations.get(j).getParams().size() && declarations.get(i).getParams().size() == 0) {
							System.out.println("\u001B[31m" + "Overload/Override Error involving methods entitled " + declarations.get(i).getIdentifier() + "\u001B[0m");
						}else if(declarations.get(i).getParams().size() == declarations.get(j).getParams().size()) {
							for (int k = 0; k < declarations.get(i).getParams().size(); k++) {
								String t = declarations.get(i).getParams().get(k).getType().getText();
								String t2 = declarations.get(j).getParams().get(k).getType().getText();
								if (t.equals(t2)) {
									System.out.println("\u001B[31m" + "Overload/Override Error involving methods entitled " + declarations.get(i).getIdentifier() + "\u001B[0m");
									errorNotFound = false;
								}
							}
						}
						}
					}
				}
		declarationCheck();
	}

	public boolean isErrorNotFound() {
		return errorNotFound;
	}

	//Check for duplicate declarations.
	public void declarationCheck() {
		for(MethodDeclaration m : allMethods){
			for(int i = 0; i < m.getTotal().size(); i++) {
				for (int j = i + 1; j < m.getTotal().size(); j++) {
					String s = m.getTotal().get(i).getIdentifier();
					String t = m.getTotal().get(j).getIdentifier();
					if (s.equals(t)) {
						String output = "\u001B[31m" + "Identical declarations for the variable name " + m.getTotal().get(j).getIdentifier();
						System.out.println(output + "\u001B[0m");
						errorNotFound = false;
					}
				}
			}
		}
	}
	@Override
	public T visitEnd(MiniJavaGrammarParser.EndContext ctx) {
		link();
		return null;
	}
	@Override public T visitGoal(MiniJavaGrammarParser.GoalContext ctx) { return visitChildren(ctx); }
	@Override public T visitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) { return visitChildren(ctx); }
	@Override public T visitStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx) { return null; }
	@Override public T visitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) { return null; }
	@Override public T visitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) { return null; }
	@Override public T visitPrintLine(MiniJavaGrammarParser.PrintLineContext ctx) { return null; }
	@Override public T visitAssignment(MiniJavaGrammarParser.AssignmentContext ctx) { return null; }
	@Override public T visitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) { return null; }
	@Override public T visitType(MiniJavaGrammarParser.TypeContext ctx) {
		return null;
	}
	@Override public T visitExpression(MiniJavaGrammarParser.ExpressionContext ctx) {
		return null;
	}

}
