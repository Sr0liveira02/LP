class ASTString implements ASTNode  {

    String string;

    ASTString(String string) {
        this.string = string.substring(1, string.length() - 1);
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VString(string);              
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTString();
    }

}
