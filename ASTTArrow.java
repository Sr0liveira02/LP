public class ASTTArrow implements ASTType {
    public ASTType dom;
    public ASTType codom;

    public ASTTArrow(ASTType d, ASTType co) {
        //System.out.println("ASTTArrow IN: d.type="+d.toStr()+",co.type="+co.toStr());
        dom = d;
        codom = co;

    }

    public ASTType getArgType() {
        return dom;
    }

    public ASTType getReturnType() {
        return codom;
    }

    public String toStr() {
        return "(" + dom.toStr()+")->("+codom.toStr()+")";
    }

    public boolean equals(Object o, Environment<ASTType> env) throws TypeCheckerError {
        if (this == o) return true;
        if (!(o instanceof ASTTArrow)) return false;
        ASTTArrow that = (ASTTArrow) o;
        if (!dom.specialEquals(that.dom, env)) return false;
        return codom.specialEquals(that.codom, env);
    }

    @Override
    public boolean isSubTypeOf(ASTType t, Environment<ASTType> env) throws TypeCheckerError {
        if (t instanceof ASTTArrow) {
            ASTTArrow other = (ASTTArrow) t;
            return other.dom.specialIsSubTypeOf(this.dom, env) && this.codom.specialIsSubTypeOf(other.codom, env);
        }
        return false;
    }
}

