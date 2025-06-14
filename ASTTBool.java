class ASTTBool implements ASTType {
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        if (o instanceof ASTTBool) {
            return true;
        }
        return false;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        return other instanceof ASTTBool;
    }
}