public class ASTAnd implements ASTNode {

    ASTNode lhs, rhs;

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
