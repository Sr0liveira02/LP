public class ASTMatch implements ASTNode {
        ASTNode lv;
        ASTNode nilExpression;
        String headIdentifier;
        String tailIdentifier;
        ASTNode consExpression;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                try {
                ASTType lvType = lv.typecheck(e);
                if (lvType instanceof ASTTList) { // Eager list
                        ASTType outputA = nilExpression.typecheck(e);
                        ASTType outputB;
                        if (((ASTTList) lvType).getElementType() != null) {
                                Environment<ASTType> newEnv = e.beginScope();
                                newEnv.assoc(headIdentifier, ((ASTTList) lvType).getElementType());
                                newEnv.assoc(tailIdentifier, new ASTTList(((ASTTList) lvType).getElementType()));
                                outputB = consExpression.typecheck(newEnv);
                                newEnv.endScope();
                        } else  {
                                return outputA;
                        }
                        if (outputA.specialEquals(outputB, e)) {
                                return outputA;
                        } else {
                                throw new TypeCheckerError("Type mismatch in cons expression: " + outputA.toStr() + " and " + outputB.toStr());
                        }
                } else {
                        throw new TypeCheckerError("Input of match is not a list: " + lvType.toStr());
                }
                } catch (InterpreterError err){
                        throw new TypeCheckerError(err.getMessage());
                }
        }

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
