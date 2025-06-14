class MatchStatement {
    private final String label;
    private final String id;
    private final ASTNode exp;

   public MatchStatement(String label, String id, ASTNode exp) {
        this.label = label;
        this.id = id;
        this.exp = exp;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

    public ASTNode getExp() {
        return exp;
    }

    
}
