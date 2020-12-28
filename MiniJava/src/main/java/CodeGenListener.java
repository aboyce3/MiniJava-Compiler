// Generated from MiniJavaGrammar.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class CodeGenListener implements MiniJavaGrammarListener {
	HashMap<String, Klass> classes;
	ArrayList<MethodDeclaration> allMethods;
	HashMap<String, MethodDeclaration> allMethodsMap;
	Klass currentClass;
	MethodDeclaration currentMethod;
	CodeBlock currentBlock;
	int localVariableIndex;
	State currentScope;
	ArrayList<State> scopes;
	FileWriter writer;
	int counter = 0;
	ErrorHandling errorHandling;
	ArrayList<Variable> loacls = new ArrayList<>();


	public CodeGenListener(Linker visitor, ErrorHandling e){
		classes = visitor.classes;
		allMethods = visitor.allMethods;
		allMethodsMap = visitor.allMethodsMap;
		scopes = new ArrayList<>();
		errorHandling = e;
		localVariableIndex = 0;
		currentScope = null;
		currentClass = null;
		currentMethod = null;
		currentBlock = null;
	}

	@Override public void enterMainClass(MiniJavaGrammarParser.MainClassContext ctx) {
		currentClass = classes.get(ctx.Identifier(0).getText());
		try{
			File f = new File("src/main/resources/JasminFiles/"+ctx.Identifier(0).getText() + ".j");
			f.createNewFile();
			writer = new FileWriter("src/main/resources/JasminFiles/"+ctx.Identifier(0).getText() + ".j");
			writer.write(".class public "+ ctx.Identifier(0).getText()+
					"\n.super java/lang/Object\n" +
					"\n;=====================\n" +
					";Constructor\n"+
					";=====================\n" +
					".method public <init>()V\n" +
					"   aload_0\n" +
					"   invokespecial java/lang/Object/<init>()V \n" +
					"   return\n" +
					".end method\n\n" +
					";=====================\n" +
					";Main Method\n"+
					";=====================\n" +
					".method public static main([Ljava/lang/String;)V\n" +
					"\t.limit stack 50\n\n");
		}
		catch(Exception e){}
	}

	@Override public void exitMainClass(MiniJavaGrammarParser.MainClassContext ctx) {
		try{
			writer.write("\n\treturn\n");
			writer.write(".end method");
			writer.close();
		} catch(Exception ignored){}
	}

	@Override public void enterClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
		currentClass = classes.get(ctx.Identifier(0).getText());
		try{
			File f = new File("src/main/resources/JasminFiles/"+ctx.Identifier(0).getText() + ".j");
			f.createNewFile();
			writer = new FileWriter("src/main/resources/JasminFiles/"+ctx.Identifier(0).getText() + ".j");
			String fields = "";
			String superClass = "java/lang/Object";
			String output = ".class public "+ ctx.Identifier(0).getText()+
					"\n.super {super}\n" +
					"{fields}\n" +
					";=====================\n" +
					";Constructor\n"+
					";=====================\n" +
					".method public <init>()V\n" +
					"   aload_0\n" +
					"   invokespecial java/lang/Object/<init>()V \n" +
					"   return\n" +
					".end method\n\n";
			if(currentClass.hasInheritedClass())
				superClass = currentClass.getInherited().getClassName();
			output = output.replace("{super}", superClass);
			if(currentClass.getGlobals().size() > 0) {
				for (int i = 0; i < currentClass.getGlobals().size(); i++) {
					fields += "\n.field public " + currentClass.getGlobals().get(i).getIdentifier() + " ";
					switch (currentClass.getGlobals().get(i).getType().getText()) {
						case "int":
							fields += "I\n";
							break;
						case "boolean":
							fields += "I\n";
							break;
						case "double":
							fields += "D\n";
							break;
						case "double[]":
							fields += "[D\n";
							break;
						case "int[]":
							fields += "[I\n";
							break;
						default:
							fields += "L" + currentClass.getGlobals().get(i).getType().getText() + ";\n";
							break;
					}
				}
			}
			output = output.replace("{fields}", fields);
			writer.write(output);
		}
		catch(Exception ignore){}
	}
	@Override public void exitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx) {
			try{
				writer.close();
			}catch (Exception ignore){}
	}
	@Override public void enterVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) {
		boolean global = false;
		for(int i = 0; i < currentClass.getGlobals().size(); i++){
			if(currentClass.getGlobals().get(i).getIdentifier().equals(ctx.Identifier().getText())){
				global = true;
			}
		}
		if(!global) {
			try {
				switch (ctx.type().getText()) {
					case "int":
					case "boolean":
						writer.write("\tldc 0\n");
						writer.write("\tistore " + (counter) + "\n");
						for(int i = currentMethod.getParams().size() + currentClass.getGlobals().size(); i < currentMethod.getTotal().size(); i++){
							if(currentMethod.getTotal().get(i).getIdentifier().equals(ctx.Identifier().getText())){
								currentMethod.getTotal().get(i).localLocation = counter++;
							}
						}
						break;
					case "double":
						writer.write("\tdconst_0\n");
						writer.write("\tdstore " + (counter) + "\n");
						for(int i = currentMethod.getParams().size() + currentClass.getGlobals().size(); i < currentMethod.getTotal().size(); i++){
							if(currentMethod.getTotal().get(i).getIdentifier().equals(ctx.Identifier().getText())){
								currentMethod.getTotal().get(i).localLocation = counter++;
							}
						}
						break;
					default:
						String output = "";
						for(Variable v : currentMethod.getTotal()){
						if(v.getIdentifier().equals(ctx.Identifier().getText())){
							output = v.getType().getText();
						}
					}
						for(String k : classes.keySet()){
							Klass cl = classes.get(k);
							for(Variable v : cl.getGlobals()){
								if(v.getIdentifier().equals(ctx.Identifier().getText())){
									output = v.getType().getText();
								}
							}
						}
						writer.write("\tnew " + output + "\n");
						writer.write("\tdup\n");
						writer.write("\tinvokespecial " + output + "/<init>()V\n");
						writer.write("\tastore " + (counter) + "\n");
						for(int i = currentMethod.getParams().size() + currentClass.getGlobals().size(); i < currentMethod.getTotal().size(); i++){
							if(currentMethod.getTotal().get(i).getIdentifier().equals(ctx.Identifier().getText())){
								currentMethod.getTotal().get(i).localLocation = counter++;
							}
						}
				}
			} catch (Exception ignore) {
			}
		}
	}
	@Override public void exitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx) {

	}
	@Override public void enterMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
		int paramCounter = 1;
		currentMethod = allMethodsMap.get(ctx.Identifier(0).getText());
		for(int i = currentClass.getGlobals().size() ; i < currentMethod.getTotal().size() && paramCounter <= currentMethod.getParams().size(); i++){
			currentMethod.getTotal().get(i).localLocation = paramCounter++;
		}
		currentMethod.setK(currentClass);
		currentMethod.setParamsLocal();
		counter = ctx.type().size();
		currentMethod = allMethodsMap.get(ctx.Identifier(0).getText());
		currentBlock = currentMethod.getBlock();
		try{
			String header = ";=====================\n";
			header += "; {method}\n";
			header += ";=====================\n";
			header = header.replace("{method}", ctx.Identifier(0).getText());
			header += ".method public " + ctx.Identifier(0).getText() +"({params}){return}"
					+"\n\t.limit stack 50\n\t.limit locals 50\n\n";
			String params = "";
			String returnType = "";
			if(ctx.type().size() > 1){
				int size = ctx.type().size();
				for(int i = 1; i < size; i++){
					//Checks the types of each param and appends to the string entitled "params".
					switch(ctx.type(i).getText()){
						case "int":
							params += "I";
							break;
						case "boolean":
							params += "I";
							break;
						case "double":
							params += "D";
							break;
						case "double[]":
							params += "[D";
							break;
						case "int[]":
							params += "[I";
							break;
						default:
							params += "L" + ctx.type(i).getText() + ";";
							break;
					}
				}
			}
			//Checks the return type equal to its respected type.
			switch(ctx.type(0).getText()){
				case "int":
					returnType = "I";
					break;
				case "boolean":
					returnType = "I";
					break;
				case "double":
					returnType = "D";
					break;
				case "double[]":
					returnType = "[D";
					break;
				case "int[]":
					returnType = "[I";
					break;
				default:
					params += "L" + ctx.type(0).getText() + ";";
					break;
			}
			header = header.replace("{params}", params);
			header = header.replace("{return}", returnType);
			writer.write(header);
		}catch(Exception ignore){}
	}
	@Override public void exitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx) {
		try {
			switch(ctx.type(0).getText()){
				case "int":
				case "boolean":
					if(ctx.expression().getText().equals("true")){
						writer.write("\tldc 1\n");
					}else if(ctx.expression().getText().equals("false")){
						writer.write("\tldc 0\n");
					}else if(ctx.expression().INTEGER_LITERAL() != null){
						writer.write(evaluateExpressions(ctx.expression(),currentMethod) +"\n");
					} else if(ctx.expression().INTEGER_LITERAL() == null){
						writer.write(evaluateExpressions(ctx.expression(),currentMethod) +"\n");
					}
					writer.write("\tireturn\n");
					break;
				case "double":
					if(ctx.expression().DOUBLE_LITERAL() != null){
						writer.write(evaluateExpressions(ctx.expression(),currentMethod) +"\n");
					} else if(ctx.expression().DOUBLE_LITERAL() == null){
						writer.write(evaluateExpressions(ctx.expression(),currentMethod) +"\n");
					}
					writer.write("\tdreturn\n");
					break;
				default:
					writer.write(evaluateExpressions(ctx.expression(),currentMethod) +"\n");
					writer.write("\tareturn\n");
					break;
			}
			writer.write(".end method\n\n");
		} catch(Exception ignore){}
	}
	@Override public void enterType(MiniJavaGrammarParser.TypeContext ctx) { }
	@Override public void exitType(MiniJavaGrammarParser.TypeContext ctx) { }
	@Override public void enterStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx) { }
	@Override public void exitStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx) { }
	@Override public void enterIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) { }
	@Override public void exitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx) { }
	@Override public void enterWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) { }
	@Override public void exitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx) { }
	@Override public void enterPrintLine(MiniJavaGrammarParser.PrintLineContext ctx) {
		try{
			writer.write("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
			writer.write(evaluateExpressions(ctx.expression(), currentMethod));
			writer.write("\tinvokevirtual java/io/PrintStream/println(I)V\n");
		}catch (Exception e){e.printStackTrace();}
	}

	@Override public void enterAssignment(MiniJavaGrammarParser.AssignmentContext ctx) {
		try{
		writer.write(evaluateExpressions(ctx.expression(), currentMethod));
	}catch (Exception e){  }
	}
	@Override public void enterArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) {
		try{
			String output = "";
			writer.write(evaluateExpressions(ctx.expression(0), currentMethod));
			writer.write(evaluateExpressions(ctx.expression(1), currentMethod));
			Klass c = classes.get(currentMethod.className());
			for (Variable v : c.getGlobals()) {
				if (v.getIdentifier().equals(ctx.Identifier().getText())) {
					switch (v.getType().getText()) {
						case "int[]":
							output += "\tiastore\n" +
									"\taload 0\n" +
									"\tgetfield "+ c.getClassName() + "/" + v.getIdentifier() + " [I\n";
							break;
						case "double[]":
							output += "\tdastore\n" +
									"\taload 0\n" +
									"\tgetfield "+ c.getClassName() + "/" + v.getIdentifier() + " [D\n";

					}
					writer.write(output);
					return;
				}
			}
			while(c.hasInheritedClass()){
				c = c.getInherited();
				for (Variable v : c.getGlobals()) {
					if (v.getIdentifier().equals(ctx.Identifier().getText())) {
						switch (v.getType().getText()) {
							case "int":
							case "boolean":
								output += "\tistore " + v.localLocation + "\n";
								break;
							case "double":
								output += "\tdstore " + v.localLocation + "\n" + "\tdreturn\n";
								break;
							default:
								output += "\tputfield " + c.getClassName() + "/" + v.getIdentifier() + " {type}"  +"\n";
								if(v.getType().getText().equals("int")){
									output = output.replace("{type}", "I");
								} else if(v.getType().getText().equals("double")){
									output = output.replace("{type}", "D");
								} else if(v.getType().getText().equals("double[]")){
									output = output.replace("{type}", "[D");
								} else if(v.getType().getText().equals("int[]")){
									output = output.replace("{type}", "[I");
								} else{
									output = output.replace("{type}", "L" + v.getType().getText());
								}
								break;
						}
						writer.write(output);
						return;
					}
				}
			}
		} catch (Exception e){ }
	}
	@Override public void enterExpression(MiniJavaGrammarParser.ExpressionContext ctx) { }
	@Override public void exitExpression(MiniJavaGrammarParser.ExpressionContext ctx) { }
	@Override public void enterEnd(MiniJavaGrammarParser.EndContext ctx) { }
	@Override public void exitEnd(MiniJavaGrammarParser.EndContext ctx) { }
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
	@Override public void exitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx) {

	}
	@Override public void exitAssignment(MiniJavaGrammarParser.AssignmentContext ctx) {
		try {
			String output = "";
			for (Variable v : currentMethod.getK().getGlobals())
				if (v.getIdentifier().equals(ctx.Identifier().getText())) {
					output += "\tputfield " + currentMethod.className() + "/" + v.getIdentifier() + " {type}"  +"\n";
					if(v.getType().getText().equals("int")){
						output = output.replace("{type}", "I");
					} else if(v.getType().getText().equals("double")){
						output = output.replace("{type}", "D");
					} else if(v.getType().getText().equals("double[]")){
						output = output.replace("{type}", "[D");
					} else if(v.getType().getText().equals("int[]")){
						output = output.replace("{type}", "[I");
					}else{
						output = output.replace("{type}", "L" + v.getType().getText());
					}
					writer.write(output);
					return;
				}
				for (Variable v : currentMethod.getTotal()) {
					if (v.getIdentifier().equals(ctx.Identifier().getText())) {
						switch (v.getType().getText()) {
							case "int":
							case "boolean":
								output += "\tistore " + v.localLocation + "\n";
								break;
							case "double":
								output += "\tdstore " + v.localLocation + "\n";
								break;
							case "double[]":
								output += "\tputfield " + currentMethod.className() + "/" + v.getIdentifier() + " [D"  +"\n";
								break;
							case "int[]":
								output += "\tputfield " + currentMethod.className() + "/" + v.getIdentifier() + " [I"  +"\n";
								break;
							default:
								output += "\tastore " + v.localLocation + "\n";
								break;
						}
						writer.write(output);
						return;
					}
				}
				Klass c = currentMethod.getK();
				while(c.hasInheritedClass()){
					c = c.getInherited();
					for (Variable v : c.getGlobals()) {
						if (v.getIdentifier().equals(ctx.Identifier().getText())) {
							if (v.getIdentifier().equals(ctx.Identifier().getText())) {
								output += "\tputfield " + currentMethod.className() + "/" + v.getIdentifier() + " {type}"  +"\n";
								if(v.getType().getText().equals("int")){
									output = output.replace("{type}", "I");
								} else if(v.getType().getText().equals("double")){
									output = output.replace("{type}", "D");
								} else if(v.getType().getText().equals("double[]")){
									output = output.replace("{type}", "[D");
								} else if(v.getType().getText().equals("int[]")){
									output = output.replace("{type}", "[I");
								}else{
									output = output.replace("{type}", "L" + v.getType().getText());
								}
								writer.write(output);
								return;
							}
						}
					}
				}
			} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	@Override public void exitPrintLine(MiniJavaGrammarParser.PrintLineContext ctx) { }
	@Override public void enterGoal(MiniJavaGrammarParser.GoalContext ctx) { }
	@Override public void exitGoal(MiniJavaGrammarParser.GoalContext ctx) { }


	public String evaluateExpressions(MiniJavaGrammarParser.ExpressionContext expression, MethodDeclaration m){
		if(expression.getText().equals("true"))
			return "\tldc 1\n";
		else if(expression.getText().equals("false"))
			return "\tldc 0\n";
		else if(expression.INTEGER_LITERAL() != null && expression.getText().equals(expression.INTEGER_LITERAL().getText()))
			return "\tldc " + expression.INTEGER_LITERAL().getText() + "\n";
		else if(expression.DOUBLE_LITERAL() != null && expression.getText().equals(expression.DOUBLE_LITERAL().getText()))
			return "\tdstore " + expression.DOUBLE_LITERAL().getText() +"\n";
		else if(expression.getText().equals("this"))
			return "\taload 0\n";
		else if(expression.Identifier() != null && expression.getText().contains("new")
				&& expression.expression().size() == 0){
				String output = "\tnew " + expression.Identifier().getText() + "\n";
				      output += "\tdup\n";
				      output += "\tinvokespecial " + expression.Identifier().getText() + "/<init>()V\n";
				return output;
		}
		else if(expression.getText().contains("new") && expression.getText().contains("double") && expression.getText().contains("[")){
			String output = evaluateExpressions(expression.expression(0),currentMethod);
			      output += "\tnewarray double\n";
			      return output;
		}
		else if(expression.getText().contains("new") && expression.getText().contains("int") && expression.getText().contains("[")){
			String output = evaluateExpressions(expression.expression(0),currentMethod);
			output += "\tnewarray int\n";
			return output;
		}
		else if(expression.getText().contains("!") ){
			String output = evaluateExpressions(expression.expression(0),m);
			Character last = output.charAt(output.length() -1);
			if(last.equals('0')) {
				output = output.substring(0, output.length() - 1);
				output += "1";
			}else {
				output = output.substring(0, output.length() - 1);
				output += "0";
			}
		}
		else if(expression.getText().contains(".") && expression.getText().contains("length")){
			String result = evaluateExpressions(expression.expression(0),m);
			      result += "\tarraylength\n";
			      return result;
		}
		else if(expression.expression().size() == 2 && expression.getText().contains("[")){
			String resultRight = evaluateExpressions(expression.expression(1),m);
			String result = evaluateExpressions(expression.expression(0),m);
			return result + resultRight;
		}
		else if(expression.getText().contains(".") && expression.Identifier() != null && expression.getText().contains("(")) {
			Klass k = classes.get(errorHandling.evaluateExpressionsNoPrint(expression.expression(0),m));
			String left = evaluateExpressions(expression.expression(0),currentMethod);
			if (k != null) {
				ArrayList<MethodDeclaration> methods = k.getMethods();
				ArrayList<String> paramTypes = new ArrayList<>();
				for(MethodDeclaration me : k.getMethods()){
					if(me.getIdentifier().equals(expression.Identifier().getText())){
						if(me.getParams().size() == expression.expression().size()-1){
							int x = 1;
							for(Variable v : me.getParams()){
								left += evaluateExpressions(expression.expression(x++),me);
								paramTypes.add(v.getType().getText());
							}
						}
					}
				}
				if (expression.expression().size() == 1) {
					for (MethodDeclaration method : methods) {
						if (method.getIdentifier().equals(expression.Identifier().getText()) && method.getParams().size() == 0) {
								String output = left + "\tinvokevirtual " + k.getClassName() + "/" + expression.Identifier().getText() + "()";
								MethodDeclaration md = allMethodsMap.get(expression.Identifier().getText());
								switch (md.getReturnType()) {
									case "int":
										return output += "I\n";
									case "boolean":
										return output += "I\n";
									case "double":
										return output += "D\n";
									case "double[]":
										return output += "[D\n";
									case "int[]":
										return output += "[I\n";
									default:
										return output += "L" + md.getReturnType() + ";\n";
							}
						}
					}
				} else if (expression.expression().size() > 1) {
					Klass c = null;
					Klass d = null;
					String s = "";
					for (int i = 1; i < expression.expression().size(); i++) {
						String temp = errorHandling.evaluateExpressionsNoPrint(expression.expression(i), m);
						paramTypes.add(temp);
					}
					boolean found = true;
					for(MethodDeclaration method : k.getMethods()){
							if(method.getIdentifier().equals(expression.Identifier().getText())){
								String addition = "";
								for(int i = 0; i < method.getParams().size(); i++){
									if(method.getParams().get(i).getType().getText().equals(paramTypes.get(i))){
										String output = left + addition;
										output += "\tinvokevirtual " + method.className() + "/" + method.getIdentifier() + "({params})";
										String params = "";
										for(Variable v : method.getParams()){
											switch(v.getType().getText()){
												case "int":
												case "boolean":
													params += "I";
													break;
												case "double":
													params += "D";
													break;
												case "int[]":
													params += "[I";
													break;
												case "double[]":
													params += "[D";
													break;
												default:
													params += "L" + v.getType().getText() + ";";
											}
										}
										output = output.replace("{params}", params);
										switch (method.getReturnType()) {
											case "int":
												return output += "I\n";
											case "boolean":
												return output += "I\n";
											case "double":
												return output += "D\n";
											case "double[]":
												return output += "[D\n";
											case "int[]":
												return output += "[I\n";
											default:
												return output += "L" + method.getReturnType() + ";";
										}
									}
								}
							}
					}
				}
			}
		}
		else if(expression.Identifier() != null && expression.getText().equals(expression.Identifier().getText())) {
			Klass k = classes.get(m.className());
			if (k.hasInheritedClass()) {
				for (Variable v : classes.get(m.className()).getGlobals()) {
					if (v.getIdentifier().equals(expression.Identifier().getText())) {
						String output = "\tgetfield {class}/{var} {type}";
						output = output.replace("{class}", m.className());
						output = output.replace("{var}", v.getIdentifier());
						switch(v.getType().getText()){
							case "int":
							case "boolean":
								output = output.replace("{type}", "I\n");
								break;
							case "double":
								output = output.replace("{type}", "D\n");
								break;
							case "double[]":
								output = output.replace("{type}", "[D\n");
								break;
							case "int[]":
								output = output.replace("{type}", "[I\n");
								break;
							default:
								output = output.replace("{type}", "L" + v.getType().getText() + "\n");
								break;
						}
						return output;
					}
				}
				for (int i = k.getGlobals().size(); i < currentMethod.getTotal().size(); i++) {
					if (currentMethod.getTotal().get(i).getIdentifier().equals(expression.Identifier().getText())) {
						String output = "\t{type}load {index}";
						output = output.replace("{class}", m.className());
						output = output.replace("{var}", currentMethod.getTotal().get(i).getIdentifier());
						switch(currentMethod.getTotal().get(i).getType().getText()){
							case "int":
							case "boolean":
								output = output.replace("{type}",  "i\n");
								output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
								break;
							case "double":
								output = output.replace("{type}", "d\n");
								output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
								break;
							case "double[]":
								output = output.replace("{type}", "da\n");
								output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
								break;
							case "int[]":
								output = output.replace("{type}", "ia\n");
								output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
								break;
							default:
								output = output.replace("{type}", "a") ;
								output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
								break;
						}
						return output;
					}
				}
				while (k.hasInheritedClass()) {
					k = k.getInherited();
					for (Variable v : k.getGlobals()) {
						if (v.getIdentifier().equals(expression.Identifier().getText())) {
							String output = "\tnew {class}\n" +
											"\tdup\n" +
											"\t\ninvokespecial {class}/<init>()V\n";
							output = output.replace("{class}", k.getClassName());
							return output;
						}
					}
				}
			} else {
				for (Variable v : classes.get(m.className()).getGlobals()) {
					if (v.getIdentifier().equals(expression.Identifier().getText())) {
						String output = "\taload 0\n\tgetfield {class}/{var} {type}";
						output = output.replace("{class}", m.className());
						output = output.replace("{var}", v.getIdentifier());
						switch(v.getType().getText()){
							case "int":
							case "boolean":
								output = output.replace("{type}", "I\n");
								break;
							case "double":
								output = output.replace("{type}", "D\n");
								break;
							case "double[]":
								output = output.replace("{type}", "[D\n");
								break;
							case "int[]":
								output = output.replace("{type}", "[I\n");
								break;
							default:
								output = output.replace("{type}", "L" + v.getType().getText() + "\n");
								break;
						}
						return output;
					}
				}
				for (int i = k.getGlobals().size(); i < currentMethod.getTotal().size(); i++) {
					if (currentMethod.getTotal().get(i).getIdentifier().equals(expression.Identifier().getText())) {
							if (currentMethod.getTotal().get(i).getIdentifier().equals(expression.Identifier().getText())) {
								String output = "\t{type}load {index}";
								output = output.replace("{class}", m.className());
								output = output.replace("{var}", currentMethod.getTotal().get(i).getIdentifier());
								switch(currentMethod.getTotal().get(i).getType().getText()){
									case "int":
									case "boolean":
										output = output.replace("{type}", "i");
										output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
										output = "\taload 0\n" + output;
										break;
									case "double":
										output = output.replace("{type}", "d");
										output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
										output = "\taload 0\n" + output;
										break;
									case "double[]":
										output = output.replace("{type}", "id");
										output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
										output = "\taload 0\n" + output;
										break;
									case "int[]":
										output = output.replace("{type}", "ia");
										output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
										output = "\taload 0\n" + output;
										break;
									default:
										output = output.replace("{type}", "a");
										output = output.replace("{index}", currentMethod.getTotal().get(i).localLocation +"\n");
										output = "\taload 0\n" + output;
										break;
								}
								return output;
					}
				}
			}
			}
		}
		else if(expression.binary_operator != null && (expression.binary_operator.equals("<") || expression.binary_operator.equals("&&"))){
			String left = evaluateExpressions(expression.expression(0),m);
			String right = evaluateExpressions(expression.expression(1),m);
			if(expression.binary_operator.getText().equals("<")){
				return left + right + "\tif_icmple {SOMESTATE}\n";
			}
			else if(expression.binary_operator.getText().equals("&&")){
				return left + right;
			}
		}
		else if(expression.binary_operator != null && (expression.binary_operator.getText().contains("+")
				|| expression.getText().contains("-") || expression.getText().contains("*"))){
			String op = expression.binary_operator.getText();
			String left = evaluateExpressions(expression.expression(0),m);
			String right = evaluateExpressions(expression.expression(1),m);
			if(expression.binary_operator.getText().equals("*")){
				return left + right + "\timul\n";
			}
			else if(expression.binary_operator.getText().equals("+")){
				return left + right + "\tiadd\n";
			}
			else if(expression.binary_operator.getText().equals("-")){
				return  left + right + "\tisub\n";
			}
		}
		else if( ((Character)expression.getText().charAt(0)).equals('(')) return evaluateExpressions(expression.expression(0),m);
		return "";
	}
}