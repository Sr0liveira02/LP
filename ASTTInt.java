public class ASTTInt implements ASTType {
    
    public ASTTInt() {}

    public String toStr() {
        return "int";
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        return o instanceof ASTTInt;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        return other instanceof ASTTInt;
    }
}


