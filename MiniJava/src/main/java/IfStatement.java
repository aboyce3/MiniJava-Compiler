public class IfStatement {
    boolean expression;
    CodeBlock a,b;

    public IfStatement(String s, CodeBlock t, CodeBlock f) {
        try {
            if (expression = Boolean.parseBoolean(s)) a = t;
            else a = f;
        } catch (Exception e) {
            System.out.println("Exception Caught for if statement, invalid boolean expression.");
            a = t;
            b = f;
        }
    }

    public boolean isExpression() {
        return expression;
    }

    public CodeBlock getA() {
        return a;
    }

    public CodeBlock getB() {
        return b;
    }
}

