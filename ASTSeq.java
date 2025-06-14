public class ASTSeq implements ASTNode {
    ASTNode body;
    ASTNode next;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        body.typecheck(e);
        return next.typecheck(e);
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        body.eval(e);
        return next.eval(e);
    }

    public ASTSeq(ASTNode body, ASTNode next) {
        this.body = body;
        this.next = next;
    }

}
