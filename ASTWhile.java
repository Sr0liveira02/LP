public class ASTWhile implements ASTNode {
        ASTNode condition;
        ASTNode branch;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType condType = condition.typecheck(e);
                if (!(condType instanceof ASTTBool)) {
                        throw new TypeCheckerError("Condition must be of type Bool, found: " + condType);
                }
                branch.typecheck(e);
                return new ASTTBool();
        }
        
        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue cond = condition.eval(e);
                if (cond instanceof VBool == false) {
                        throw new InterpreterError("Not a valid condition: " + cond);
                }
                VBool b = (VBool) cond;
                while (b.getval()) {
                        branch.eval(e);
                        b = (VBool) condition.eval(e);
                }
                return b;
        }

        public ASTWhile(ASTNode condition, ASTNode branch) {
                this.condition = condition;
                this.branch = branch;
        }

}
