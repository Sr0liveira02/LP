public class ASTApp implements ASTNode {
        ASTNode arg;
        ASTNode leftv;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType functionType = leftv.typecheck(e);
                if (functionType instanceof ASTTId)
                        functionType = ((ASTTId) functionType).get(e);
                if (!(functionType instanceof ASTTArrow)) {
                        throw new TypeCheckerError("Left side of application is not a function type: " + functionType.toStr());
                }
                ASTTArrow t1a = (ASTTArrow) functionType;
                ASTType t2 = arg.typecheck(e);
                if (t2 instanceof ASTTId) {
                        t2 = ((ASTTId) t2).get(e);
                }
                
                if (!t1a.getArgType().specialIsSubTypeOf(t2, e)) {
                        throw new TypeCheckerError("Illegal types to application operator: " + t1a.toStr() + " and " + t2.toStr());
                }
                return t1a.getReturnType();
        }

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
                IValue ret = closure.getExp().eval(newEnv);
                newEnv.endScope();
                return ret;
        }

        public ASTApp(ASTNode leftv, ASTNode arg) {
                this.leftv = leftv;
                this.arg = arg;
        }
}
