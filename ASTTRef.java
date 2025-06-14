public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType type) {
        this.type = type;
    }
    
    public ASTType getType() {
        return type;
    }

    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

    public void unfold(Environment<ASTType> env) throws TypeCheckerError {
        if (type instanceof ASTTRef) {
            ASTTRef refType = (ASTTRef) type;
            refType.unfold(env);
        } 
        if (type instanceof ASTTId){
            ASTTId idType = (ASTTId) type;
            type = idType.get(env);
        }
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTRef) {
            ASTTRef other = (ASTTRef) o;
            return this.type.equals(other.type);
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        if (other instanceof ASTTRef) {
            ASTTRef otherRef = (ASTTRef) other;
            return this.type.equals(otherRef.getType());
        }
        return false;
    }

}