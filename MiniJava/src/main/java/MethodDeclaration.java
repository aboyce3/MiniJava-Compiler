import java.util.ArrayList;

public class MethodDeclaration {
    private String returnType, identifier, className;
    private ArrayList<Variable> params, total;
    private MiniJavaGrammarParser.ExpressionContext returnStatement;
    private CodeBlock block;
    private Klass k;

    public MethodDeclaration(String r, String i, String c, MiniJavaGrammarParser.ExpressionContext expression){
        returnType = r;
        identifier = i;
        className = c;
        params = new ArrayList<>();
        total = new ArrayList<>();
        returnStatement = expression;
    }

    public void setK(Klass k) { this.k = k; }

    public Klass getK() {return this.k; }

    public void setBlock(CodeBlock b){ block = b; }

    public void addParam(Variable params) {
        this.total.add(params);
        this.params.add(params);
    }

    public void setParamsLocal(){
            for(int i = k.getGlobals().size() + params.size(); i < total.size(); i++)
                total.get(i).localLocation = params.size() + i;
    }

    public MiniJavaGrammarParser.ExpressionContext getReturnStatement(){
        return returnStatement;
    }

    public void addTotal(ArrayList<Variable> p) {
        for (Variable v : p) total.add((Variable) v);
    }

    public ArrayList<Variable> getTotal(){ return total;}

    public String getIdentifier() {
        return identifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public CodeBlock getBlock() {
        return block;
    }

    public ArrayList<Variable> getParams() {
        return params;
    }

    public String className(){ return className; }

    public void setReturnType(String s){ returnType = s;}

    public String toString(){
        String output = getIdentifier() +"(";
        if(params.size() > 0) {
            for (int i = 0; i < params.size() - 1; i++)
                output += "Identifier: " + getIdentifier() +  " Type: " +params.get(i).getType().getText() + " " + getIdentifier() + ", ";
            return output += params.get(params.size() - 1).getType().getText() + " " + params.get(params.size() - 1).getIdentifier() + ")";
        }else return output += ")";
    }

}
