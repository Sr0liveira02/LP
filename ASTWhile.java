public class ASTWhile implements ASTNode {
        ASTNode condition;
        ASTNode branch;
        
        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue cond = condition.eval(e);
                if (cond instanceof VBool == false) {
                        throw new InterpreterError("Not a valid condition: " + cond);
                }
                VBool b = (VBool) cond;
                IValue v = new VInt(0);
                while (b.getval()) {
                        v = branch.eval(e);
                        b = (VBool) condition.eval(e);
                }
                return v;
        }

        public ASTWhile(ASTNode condition, ASTNode branch) {
                this.condition = condition;
                this.branch = branch;
        }

}
