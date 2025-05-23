class VIcons implements IValue {
    ASTNode head;
    ASTNode tail;
    Environment<IValue> env;

    VIcons(ASTNode h, ASTNode t, Environment<IValue> e) {
        head = h;
        tail = t;
        env = e;
    }

    public ASTNode getHead() {
        return head;
    }

    public ASTNode getTail() {
        return tail;
    }

    public Environment<IValue> getEnv() {
        return env;
    }

    public void setHead(ASTNode h) {
        head = h;
    }

    public void setTail(ASTNode t) {
        tail = t;
    }

    public void setEnv(Environment<IValue> e) {
        env = e;
    }

    public String toStr() {
        return "LazyLista";
    }

}