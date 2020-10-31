import java.util.Stack;

public class CodeBlock {
    Stack<String> instructions;

    public CodeBlock(){
        instructions = new Stack<>();
    }

    public void addInstruction(String e){
        instructions.add(e);
    }
    public String pop(){
        return instructions.pop();
    }
    public void push(String e){
        instructions.push(e);
    }

    public Stack<String> getInstructions() {
        return instructions;
    }

}
