public class ASTExp implements ASTNode {
    ASTNode body;
    ASTNode next;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        body.eval(e);
        return next.eval(e);
    }

    public ASTExp(ASTNode body, ASTNode next) {
        this.body = body;
        this.next = next;
    }

}
