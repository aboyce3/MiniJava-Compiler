// Generated from MiniJavaGrammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniJavaGrammarParser}.
 */
public interface MiniJavaGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(MiniJavaGrammarParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(MiniJavaGrammarParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(MiniJavaGrammarParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(MiniJavaGrammarParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MiniJavaGrammarParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MiniJavaGrammarParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(MiniJavaGrammarParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MiniJavaGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MiniJavaGrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementBlock}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementBlock}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock(MiniJavaGrammarParser.StatementBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MiniJavaGrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MiniJavaGrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MiniJavaGrammarParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintLine}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintLine(MiniJavaGrammarParser.PrintLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintLine}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintLine(MiniJavaGrammarParser.PrintLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MiniJavaGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MiniJavaGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAssignment}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAssignment}
	 * labeled alternative in {@link MiniJavaGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignment(MiniJavaGrammarParser.ArrayAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MiniJavaGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MiniJavaGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaGrammarParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(MiniJavaGrammarParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaGrammarParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(MiniJavaGrammarParser.EndContext ctx);
}