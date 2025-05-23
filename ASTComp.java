public class ASTComp implements ASTNode {

    ASTNode lhs, rhs;
    String _op;

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
