import java.util.ArrayList;

public class MethodDeclaration {
    private String returnType, identifier, className, methodName;
    private ArrayList<Variable> params;
    private CodeBlock block;


    public MethodDeclaration(String r, String i, String c){
        returnType = r;
        identifier = i;
        className = c;
        params = new ArrayList<>();
    }

    public void setBlock(CodeBlock b){ block = b; }

    public void setMethodName(String s){ methodName = s;}

    public void addParam(Variable params) {
        this.params.add(params);
    }

    public void addParams(ArrayList<Variable> p) {
        for (Variable v : p) params.add((Variable) v);
    }

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

    public String toString(){
        String output = getIdentifier() +"(";
        for(int i = 0; i < params.size()-1; i++)
            output += params.get(i).getType() + " " + getIdentifier() + ", ";
        return output += params.get(params.size()-1).getType() + " " + params.get(params.size()-1).getIdentifier() + ")";
    }

}
