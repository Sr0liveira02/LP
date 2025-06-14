public class ASTAnd implements ASTNode {

        ASTNode lhs, rhs;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType t1 = lhs.typecheck(e);
                if (t1 instanceof ASTTBool) {
                        ASTType t2 = rhs.typecheck(e);
                        if (t2 instanceof ASTTBool) {
                                return new ASTTBool();
                        }
                }
                throw new TypeCheckerError("illegal types to AND operator");
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = lhs.eval(e);
                if (v1 instanceof VBool) {
                        IValue v2 = rhs.eval(e);
                        if(v2 instanceof VBool) {
                                boolean b1 = ((VBool) v1).getval();
                                boolean b2 = ((VBool) v2).getval();
                                return new VBool(b1 && b2);
                        }
                } 
                throw new InterpreterError("illegal types to AND operator");
        }

        public ASTAnd(ASTNode l, ASTNode r) {
                lhs = l;
                rhs = r;
        }

}
