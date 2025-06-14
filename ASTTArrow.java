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

    public void unfold(Environment<ASTType> env) throws TypeCheckerError {
        if (dom instanceof ASTTId)
            dom = ((ASTTId) dom).get(env);
        else
            dom.unfold(env);

        if (codom instanceof ASTTId)
            codom = ((ASTTId) codom).get(env);
        else
            codom.unfold(env);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ASTTArrow)) return false;
        ASTTArrow that = (ASTTArrow) o;
        if (!dom.equals(that.dom)) return false;
        return codom.equals(that.codom);
    }

    @Override
    public boolean isSubTypeOf(ASTType t) {
        if (t instanceof ASTTArrow) {
            ASTTArrow other = (ASTTArrow) t;
            return other.dom.isSubTypeOf(this.dom) && this.codom.isSubTypeOf(other.codom);
        }
        return false;
    }
}

