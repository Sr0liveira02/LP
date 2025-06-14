public class ASTEqual implements ASTNode {

        ASTNode lhs, rhs;
        String _op;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType t1 = lhs.typecheck(e);
                ASTType t2 = rhs.typecheck(e);
                if (t1 instanceof ASTTBool && t2 instanceof ASTTBool) {
                        return new ASTTBool();
                } else if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                        return new ASTTBool();
                } else {
                        throw new TypeCheckerError("Illegal types to " + _op + " operator");
                }
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = lhs.eval(e);
                if (v1 instanceof VBool) {
                        IValue v2 = rhs.eval(e);
                        if (v2 instanceof VBool) {
                                boolean b1 = ((VBool) v1).getval();
                                boolean b2 = ((VBool) v2).getval();
                                if (_op.equals("==")) {
                                        return new VBool(b1 == b2);
                                } else {
                                        return new VBool(b1 != b2);
                                } 
                        }
                } else if (v1 instanceof VInt) {
                        IValue v2 = rhs.eval(e);
                        if (v2 instanceof VInt) {
                                int i1 = ((VInt) v1).getval();
                                int i2 = ((VInt) v2).getval();
                                if (_op.equals("==")) {
                                        return new VBool(i1 == i2);
                                } else {
                                        return new VBool(i1 != i2);
                                } 
                        }
                }
                throw new InterpreterError("Illegal types to " + _op + " operator");
        }

        public ASTEqual(ASTNode l, String op, ASTNode r) {
                lhs = l;
                rhs = r;
                _op = op;
        }

}
