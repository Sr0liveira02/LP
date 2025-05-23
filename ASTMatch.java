public class ASTMatch implements ASTNode {
        ASTNode lv;
        ASTNode nilExpression;
        String headIdentifier;
        String tailIdentifier;
        ASTNode consExpression;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue lvValue = lv.eval(e);
                if (lvValue instanceof VNil) {
                        return nilExpression.eval(e);
                } else if (lvValue instanceof VIcons) { // Lazy list
                        VIcons iconsValue = (VIcons) lvValue;
                        Environment<IValue> newEnv = e.beginScope();
                        newEnv.assoc(headIdentifier, iconsValue.getHead().eval(iconsValue.getEnv())); // here crashes
                        newEnv.assoc(tailIdentifier, iconsValue.getTail().eval(iconsValue.getEnv()));
                        return consExpression.eval(newEnv);
                } else if (lvValue instanceof Vcons) { // Eager list
                        Vcons consValue = (Vcons) lvValue;
                        Environment<IValue> newEnv = e.beginScope();
                        newEnv.assoc(headIdentifier, consValue.getHead());
                        newEnv.assoc(tailIdentifier, consValue.getTail());
                        return consExpression.eval(newEnv);
                } else {
                        throw new InterpreterError("Pattern match failed");
                }
        }

        public ASTMatch(ASTNode lv, ASTNode nilExpression, String headIdentifier, String tailIdentifier, ASTNode consExpression) {
                this.lv = lv;
                this.headIdentifier = headIdentifier;
                this.tailIdentifier = tailIdentifier;
                this.nilExpression = nilExpression;
                this.consExpression = consExpression;
        }

}
