class ASTBox implements ASTNode  {
    ASTNode node;

    ASTBox(ASTNode node0) {
        node = node0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VPointer(node.eval(e));               
    }

}
