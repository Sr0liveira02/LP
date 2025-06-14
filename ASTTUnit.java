class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    
    public String toStr() {
        return "()";
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        return other instanceof ASTTUnit;
    }
}