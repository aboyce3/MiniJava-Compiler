import java.util.ArrayList;
import java.util.List;

public class CodeBlock {
    private List instructions;
    private ArrayList<Variable> variables;
    private MiniJavaGrammarParser.StatementContext mainMethod;
    private MiniJavaGrammarParser.ExpressionContext returnStatement;

    public CodeBlock(ArrayList<Variable> vars, List i){
        instructions = i;
        variables = vars;
    }

    public CodeBlock(ArrayList<Variable> vars, MiniJavaGrammarParser.StatementContext i){
        mainMethod = i;
        variables = vars;
    }

    public void setReturnStatement(MiniJavaGrammarParser.ExpressionContext s){ returnStatement = s; }

    public MiniJavaGrammarParser.ExpressionContext getReturnStatement() { return returnStatement; }


    public void add(MiniJavaGrammarParser.StatementContext e){
        instructions.add(e);
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void addVar(Variable v){
        variables.add(v);
    }

}
