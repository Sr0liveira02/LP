public class ASTTInt implements ASTType {
    
    public ASTTInt() {}

    public String toStr() {
        return "int";
    }

    public boolean equals(Object o) {
        return o instanceof ASTTInt;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        return other instanceof ASTTInt;
    }
}


