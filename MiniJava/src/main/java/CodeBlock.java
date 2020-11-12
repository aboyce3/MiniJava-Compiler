import java.util.ArrayList;
import java.util.List;

public class CodeBlock {
    private List instructions;
    private ArrayList<Variable> variables;
    private CodeBlock innerBlock;
    private MiniJavaGrammarParser.ExpressionContext returnStatement;

    public CodeBlock(ArrayList<Variable> vars, List i){
        instructions = i;
        variables = vars;
    }

    public void setReturnStatement(MiniJavaGrammarParser.ExpressionContext s){ returnStatement = s; }

    public MiniJavaGrammarParser.ExpressionContext getReturnStatement() { return returnStatement; }

    public void setInnerBlock(CodeBlock b){ innerBlock = b; }

    public Variable findVar(String s){
        for(Variable v : variables){
            if(v.getIdentifier().equals(s))
                return v;
        }
        return null;
    }
    public void addVariables(ArrayList<Variable> vars) {
        for (Variable v : vars) variables.add(v);
    }

    public CodeBlock getInnerBlock(){ return innerBlock; }

    public void addInstruction(MiniJavaGrammarParser.StatementContext e){
        instructions.add(e);
    }

    public void add(MiniJavaGrammarParser.StatementContext e){
        instructions.add(e);
    }

    public List getInstructions() {
        return instructions;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void addVar(Variable v){
        variables.add(v);
    }

}
