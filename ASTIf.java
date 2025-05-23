public class ASTIf implements ASTNode {
        ASTNode condition;
        ASTNode thenBranch;
        ASTNode elseBranch;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue cond = condition.eval(e);
                if (cond instanceof VBool == false) {
                        throw new InterpreterError("Not a valid condition: " + cond);
                }
                VBool b = (VBool) cond;
                if (b.getval()) {
                        return thenBranch.eval(e);
                } else {
                        if (elseBranch == null) { // isto n acontece
                                return new VInt(0);
                        } else {
                                return elseBranch.eval(e);
                        }
                }
        }

        public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
                this.condition = condition;
                this.thenBranch = thenBranch;
                this.elseBranch = elseBranch;
        }

}
