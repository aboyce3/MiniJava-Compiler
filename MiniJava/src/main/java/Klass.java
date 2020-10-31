import java.util.ArrayList;
import java.util.HashMap;

public class Klass {
    private HashMap<String, Variable> globals;
    private ArrayList<Method> methods;
    private Klass inherited;

    public Klass(Klass k){
        globals = new HashMap<>();
        methods = new ArrayList<>();
        inherited = k;
    }

    public Klass(){
        globals = new HashMap<>();
        methods = new ArrayList<>();
    }

    public void addVar(String id, Variable v){
        globals.put(id,v);
    }

    public void addMethod(Method m){
        methods.add(m);
    }

    public void inherit(Klass k){
        inherited = k;
    }

    public Klass getInherited() {
        return inherited;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public HashMap<String, Variable> getGlobals() {
        return globals;
    }
}
