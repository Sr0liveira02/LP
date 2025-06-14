public class ASTComp implements ASTNode {

        ASTNode lhs, rhs;
        String _op;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                        ASTType t1 = lhs.typecheck(e);
                        ASTType t2 = rhs.typecheck(e);
                        if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                                return new ASTTBool();
                        }
                        throw new TypeCheckerError("illegal types to " + _op + " operator");
                }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = lhs.eval(e);
                if (v1 instanceof VInt) {
                        IValue v2 = rhs.eval(e);
                        if(v2 instanceof VInt) {
                                int i1 = ((VInt) v1).getval();
                                int i2 = ((VInt) v2).getval();
                                switch (_op) {
                                        case "<":
                                                return new VBool(i1 < i2);
                                        case "<=":
                                                return new VBool(i1 <= i2);
                                        case ">":
                                                return new VBool(i1 > i2);
                                        case ">=":
                                                return new VBool(i1 >= i2);
                                }
                        }
                } 
                throw new InterpreterError("illegal types to " + _op + " operator");
        }

        public ASTComp(ASTNode l, String op, ASTNode r) {
                lhs = l;
                rhs = r;
                _op = op;
        }

}
