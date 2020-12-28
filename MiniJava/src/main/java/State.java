import java.util.ArrayList;

public abstract class State {

    ArrayList<State> nodes = new ArrayList<>();
    ArrayList<Variable> vars = new ArrayList<>();
    State parentState;
    String instructions, label;

    public State(String label){
        this.label = label;
    }

    public State(String label,State parent){
        this.label = label;
        parentState = parent;
    }

    public void append(String instructions){
        this.instructions += instructions;
    }

    public String getLabel(){
        return label;
    }

    public void makeParent(State parent){
        this.parentState = parent;
    }

    public State getParentState(){
        return parentState;
    }

    public void addVar(Variable v){
        vars.add(v);
    }

    public void addState(State state){
        nodes.add(state);
    }

    public abstract String getType();

    public abstract boolean isDeclared(String name);

    public abstract boolean isInitialized();

    public abstract boolean exists(String text);

    public abstract Variable get(String varName);

}
