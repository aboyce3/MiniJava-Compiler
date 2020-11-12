import java.util.ArrayList;

public class Klass {
    private ArrayList<MethodDeclaration> methodDeclarations;
    private ArrayList<Variable> globals = new ArrayList<>();
    private Klass inherited;
    private String inheritedClassName,className;
    private CodeBlock mainMethod;

    public Klass(String c, String k){
        methodDeclarations = new ArrayList<>();
        className = c;
        inheritedClassName = k;
    }

    public Klass(String c){
        methodDeclarations = new ArrayList<>();
        className = c;
        inheritedClassName = null;
    }

    public void addGlobal(Variable v){ globals.add(v); }

    public ArrayList<Variable> getGlobals(){ return globals; }

    public void setMainMethod(CodeBlock b){ mainMethod = b; }

    public CodeBlock getMainMethod(){ return mainMethod; }

    public void setMethodDeclarations(ArrayList<MethodDeclaration> arr){ methodDeclarations = arr;}

    public String getClassName() { return className; }

    public Klass getInherited() {
        return inherited;
    }

    public boolean hasInheritedClass(){ return inherited != null; }

    public String getInheritedClassName() { return inheritedClassName; }

    public void addMethod(MethodDeclaration m){
        methodDeclarations.add(m);
    }

    public void setInherited(Klass inherited) {
        this.inheritedClassName = inherited.getClassName();
        this.inherited = inherited;
    }

    public ArrayList<MethodDeclaration> getMethods() { return methodDeclarations; }

    public String toString(){
        return getClassName();
    }

}
