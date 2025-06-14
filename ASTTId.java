public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    
    public String toStr() {
        return id;
    }

    public String getId() {
        return id;
    }

    public ASTType get(Environment<ASTType> e) throws TypeCheckerError {
        try {
        ASTType type = e.find(id);
        if (type == null) {
            throw new TypeCheckerError("Tipo não encontrado: " + id);
        }
        return type;
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        if (o instanceof ASTTId) {
            ASTTId other = (ASTTId) o;
            return this.id.equals(other.id);
        }
        return false;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        System.out.println("Se esta linha executou entao esta algo de errado aqui!");
        return other instanceof ASTTId && this.id.equals(((ASTTId) other).id);
    }

}	
