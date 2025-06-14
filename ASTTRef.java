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

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        if (o instanceof ASTTRef) {
            ASTTRef other = (ASTTRef) o;
            return this.type.specialEquals(other.type, env);
        }
        return false;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTRef) {
            ASTTRef otherRef = (ASTTRef) other;
            return this.type.specialEquals(otherRef.getType(), env);
        }
        return false;
    }

}