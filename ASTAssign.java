public class ASTAssign implements ASTNode {
    ASTNode l;
    ASTNode r;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType leftType = l.typecheck(e);
        if (!(leftType instanceof ASTTRef)) {
            throw new TypeCheckerError("Tipos incompatíveis na atribuição");
        }
        ASTTRef leftRefType = (ASTTRef) leftType;
        ASTType rightType = r.typecheck(e);
        if (!leftRefType.getType().equals(rightType)) {
            throw new TypeCheckerError("Tipos incompatíveis na atribuição");
        }
        return rightType;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue left = l.eval(e);
        if (left instanceof VPointer == false) {
            throw new InterpreterError("Atribuição não é possível");
        }
        IValue right = r.eval(e);
        VPointer p = (VPointer) left;
        p.setValue(right);
        return right;
    }

    public ASTAssign(ASTNode l0, ASTNode r0) {
        l = l0;
        r = r0;
    }

}
