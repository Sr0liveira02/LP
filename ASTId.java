public	class ASTId implements ASTNode	{	

    String id;	
    
    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        try {
        ASTType t = e.find(id);
        if (t == null) {
            throw new TypeCheckerError("Identifier '" + id + "' not found in environment.");
        }
        return t;
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public ASTId(String id)	{
        this.id = id;
    }

    public IValue eval(Environment<IValue> env)	throws InterpreterError {
        return env.find(id);	
    }

}	
