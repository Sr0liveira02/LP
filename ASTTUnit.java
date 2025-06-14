class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    
    public String toStr() {
        return "()";
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        return o instanceof ASTTUnit;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        return other instanceof ASTTUnit;
    }
}