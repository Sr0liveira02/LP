public class ASTTString implements ASTType {

    public ASTTString() {}

    public String toStr() {
        return "string";
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        return o instanceof ASTTString;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        return other instanceof ASTTString;
    }

}
