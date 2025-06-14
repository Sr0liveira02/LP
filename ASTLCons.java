public class ASTLCons implements ASTNode {
    ASTNode head;
    ASTNode tail;


    public ASTLCons(ASTNode h, ASTNode t) {
        head = h;
        tail = t;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VIcons(head, tail, e);
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType headType = head.typecheck(e);
        ASTType tailType = tail.typecheck(e);
        
        if (tailType instanceof ASTTList) {
            ASTTList tailListType = (ASTTList) tailType;
            if (!(tailListType.getElementType() instanceof ASTTUnit) && !headType.equals(tailListType.getElementType())) {
                throw new TypeCheckerError("Head type does not match tail element type");
            }
            return new ASTTList(headType);
        } else {
            throw new TypeCheckerError("Tail must be a list type");
        }
    }
    
}
