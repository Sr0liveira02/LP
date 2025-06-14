public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    
    public String toStr() {
        return id;
    }

    public ASTType get(Environment<ASTType> e) throws TypeCheckerError {
        try {
        ASTType type = e.find(id);
        if (type == null) {
            throw new TypeCheckerError("Tipo não encontrado: " + id);
        }
        if (type instanceof ASTTId){
            type = ((ASTTId) type).get(e);
        }
        else
            type.unfold(e);

        return type;
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public void unfold(Environment<ASTType> env) throws TypeCheckerError {
        System.out.println("Shouldn't be Unfolding ASTTId: " + id);
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTId) {
            ASTTId other = (ASTTId) o;
            return this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        System.out.println("Se esta linha executou entao esta algo de errado aqui!");
        return other instanceof ASTTId && this.id.equals(((ASTTId) other).id);
    }

}	
