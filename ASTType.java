public interface ASTType  {
    default void unfold(Environment<ASTType> env) throws TypeCheckerError {}
    String toStr();
    boolean isSubTypeOf(ASTType t);
}


