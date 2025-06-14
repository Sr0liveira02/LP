public class ASTUnit implements ASTNode {

    public ASTUnit() {}

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTUnit();
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
		return new VUnit();
    }
}