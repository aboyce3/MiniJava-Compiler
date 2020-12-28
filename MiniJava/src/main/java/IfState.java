public class IfState extends State{

    public IfState(String label) {
        super(label);
    }

    public IfState(String label, State parent){
        super(label,parent);
    }

    @Override
    public void addState(State state) {
        State adding = state;
        adding.makeParent(this);
        nodes.add(state);
    }

    @Override
    public String getType() {
        return "IfState";
    }

    @Override
    public boolean isDeclared(String name) {
        return true;
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean exists(String text) {
        for (State item: nodes) {
            if (item.getLabel().equals(text))
                return true;
            if (item.exists(text))
                return true;
        }
        return false;

    }

    @Override
    public Variable get(String varName) {
        for (Variable v: vars){
            if (v.getIdentifier().equals(varName))
                return v;
        }
        if (parentState != null)
            return parentState.get(varName);
        return null;

    }

}
