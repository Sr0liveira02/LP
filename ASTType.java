public interface ASTType  {
    default boolean specialEquals(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (this instanceof ASTTId) {
            if (other instanceof ASTTId) {
                if (((ASTTId) this).getId().equals(((ASTTId) other).getId()))
                    return true;
                else
                    ((ASTTId) this).get(env).equals(((ASTTId) other).get(env), env);
            }
            return ((ASTTId) this).get(env).equals(other, env);
        }
        if (other instanceof ASTTId) {
            return this.equals(((ASTTId) other).get(env), env);
        }
        return this.equals(other, env);
    }

    default boolean specialIsSubTypeOf(ASTType t, Environment<ASTType> env) throws TypeCheckerError {
        if (this instanceof ASTTId) {
            if (t instanceof ASTTId) {
                if (((ASTTId) this).getId().equals(((ASTTId) t).getId()))
                    return true;
                else
                    ((ASTTId) this).get(env).isSubTypeOf(((ASTTId) t).get(env), env);
            }
            return ((ASTTId) this).get(env).isSubTypeOf(t, env);
        }
        if (t instanceof ASTTId) {
            return this.isSubTypeOf(((ASTTId) t).get(env), env);
        }
        return this.isSubTypeOf(t, env);
    }

    String toStr();
    boolean isSubTypeOf(ASTType t, Environment<ASTType> env) throws TypeCheckerError;
    boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError;
}


