public class Variable {
    private String type, identifier, value;

    public Variable(String t, String i, String v){
        type = t;
        identifier = i;
        value = v;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType(){
        return type;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
