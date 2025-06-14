public class ASTTString implements ASTType {

    public ASTTString() {}

    public String toStr() {
        return "string";
    }

    public boolean equals(Object o) {
        return o instanceof ASTTString;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        return other instanceof ASTTString;
    }

}
