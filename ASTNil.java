class ASTNil implements ASTNode  {

    ASTNil() {
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VNil();                
    }

}
