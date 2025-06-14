public class ASTAssign implements ASTNode {
    ASTNode l;
    ASTNode r;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType leftType = l.typecheck(e);
        if (leftType instanceof ASTTId) {
            leftType = ((ASTTId) leftType).get(e);
        }
        if (!(leftType instanceof ASTTRef)) {
            throw new TypeCheckerError("Esquerda da atribuição não é uma referência: " + leftType.toStr());
        }
        ASTTRef leftRefType = (ASTTRef) leftType;
        ASTType rightType = r.typecheck(e);
        if (!leftRefType.getType().specialEquals(rightType, e)) {
            throw new TypeCheckerError("Tipo da direita da atribuição não é compatível com a referência: " + 
                                       leftRefType.getType().toStr() + " != " + rightType.toStr());
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
