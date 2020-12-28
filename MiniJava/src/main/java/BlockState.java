public class BlockState extends State{

    public BlockState(String label){
        super(label);
    }

    public BlockState(String label, State parent){
        super(label, parent);
    }

    @Override
    public void addState(State state) {
        State adding = state;
        adding.makeParent(this);
        nodes.add(state);
    }

    @Override
    public String getType() {
        return "BlockState";
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
        for (State item: nodes){
            if (item.getLabel().equals(text))
                return true;
            if (item.exists(text))
                return true;
        }
        return false;

    }

    @Override
    public Variable get(String varName) {
        return null;
    }
}
