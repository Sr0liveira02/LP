public class ASTTList implements ASTType {
    private ASTType elt;

    public ASTTList(ASTType eltt)
    {
        elt = eltt;
    }

    public ASTType getElementType() {
        return elt;
    }
    
    public String toStr() {
        return "list<"+elt.toStr()+">";
    }
    
    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        if (o instanceof ASTTList) {
            ASTTList other = (ASTTList) o;
            if (this.elt instanceof ASTTUnit && !(other.getElementType() instanceof ASTTUnit)) {
                return true;
            }
            return this.elt.specialEquals(other.getElementType(), env);
        }
        return false;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTList) {
            ASTTList otherList = (ASTTList) other;
            return otherList.getElementType() instanceof ASTTUnit || this.elt.specialEquals(otherList.getElementType(), env);
        }
        return false;
    }
}
