class ASTTBool implements ASTType {
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTBool) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        return other instanceof ASTTBool;
    }
}