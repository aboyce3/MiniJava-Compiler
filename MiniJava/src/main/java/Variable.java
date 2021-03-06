public class Variable {
    private String identifier, value;
    private MiniJavaGrammarParser.TypeContext type;
    int arrayLength;
    int localLocation;

    public Variable(MiniJavaGrammarParser.TypeContext t, String i, String v){
        type = t;
        identifier = i;
        value = v;
    }

    public void setLocalLocation(int i) { localLocation = i; }

    public String getIdentifier() {
        return identifier;
    }

    public MiniJavaGrammarParser.TypeContext getType(){ return type; }

    public String getValue(){
        return value;
    }

    public int getArrayLength(){ return arrayLength; }

    public void setArrayLength(int i){ arrayLength = i;}

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        return "Identifier: " + identifier + " Type: " + this.type.getText();
    }

}
