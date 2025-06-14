class ASTBool implements ASTNode  {
    boolean v;

    ASTBool(boolean v0) {
        v = v0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VBool(v);                
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTBool();
    }

}
