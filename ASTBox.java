class ASTBox implements ASTNode  {
    ASTNode node;

    ASTBox(ASTNode node0) {
        node = node0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VPointer(node.eval(e));               
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType t = node.typecheck(e);
        if (t instanceof ASTTId) {
            t = ((ASTTId)t).get(e);
        }
        return new ASTTRef(t);
    }

}
