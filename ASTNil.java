class ASTNil implements ASTNode  {

    ASTNil() {
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTList(new ASTTUnit());         
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VNil();                
    }

}
