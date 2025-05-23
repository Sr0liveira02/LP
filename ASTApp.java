public class ASTApp implements ASTNode {
        ASTNode arg;
        ASTNode leftv;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue n = leftv.eval(e);
                if (n instanceof VClosure == false) {
                        throw new InterpreterError("Not a valid funciton: " + n);
                }
                VClosure closure = (VClosure) n;
                IValue v = arg.eval(e);
                // Don't check as the input can be whatever
                Environment<IValue> newEnv = closure.getEnv().beginScope();
                newEnv.assoc(closure.getIdentifier(), v);
                return closure.getExp().eval(newEnv);
        }

        public ASTApp(ASTNode leftv, ASTNode arg) {
                this.leftv = leftv;
                this.arg = arg;
        }

}
