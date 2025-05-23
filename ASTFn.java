public class ASTFn implements ASTNode {
    String _identifier;
    ASTNode body;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VClosure(_identifier, body, e);
    }

    public ASTFn(String indentifier, ASTNode b) {
        this._identifier = indentifier;
        body = b;
    }

    public ASTNode getBody() {
        return body;
    }

}
