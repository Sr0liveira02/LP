class Bind {
    private final String id;
    private final ASTNode exp;
    private final ASTType type;

    public Bind( String _id, ASTType type, ASTNode _exp) {
        this.id = _id;
        this.exp = _exp;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public ASTNode getExp() {
        return exp;
    }

    public ASTType getType() {
        return type;
    }
}
