import java.util.ArrayList;

public class Method {
    private String returnType, identifier;
    private ArrayList<Variable> params;
    private CodeBlock block;

    public Method(String r, String i, CodeBlock b){
        returnType = r;
        identifier = i;
        block = b;
        params = new ArrayList<>();
    }

    public void addParams(Variable params) {
        this.params.add(params);
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

}
