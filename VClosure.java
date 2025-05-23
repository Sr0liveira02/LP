class VClosure implements IValue {
    String identifier;
    ASTNode exp;
    Environment<IValue> env;

    VClosure(String indeString, ASTNode exp0, Environment<IValue> env0) {
        identifier = indeString;
        exp = exp0;
        env = env0;
    }

    String getIdentifier() {
        return identifier;
    }

    ASTNode getExp() {
        return exp;
    }

    Environment<IValue> getEnv() {
        return env;
    }

    public String toStr() {
        return "Função: " + identifier.toString();
    }
}