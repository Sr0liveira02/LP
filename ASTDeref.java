public class ASTDeref implements ASTNode {

    ASTNode exp;

	public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
		ASTType t = exp.typecheck(e);
		if (t instanceof ASTTId) {
			t = ((ASTTId)t).get(e);
		}
		if (t instanceof ASTTRef) {
			return ((ASTTRef)t).getType();
		} else {
			throw new TypeCheckerError("illegal types to dereference operator (*)");
		}
	}

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue v0 = exp.eval(e); 
		if (v0 instanceof VPointer) { 
			return ((VPointer)v0).getValue();
		} else { 
			throw new InterpreterError("illegal types to dereference operator (*)"); 
		}
    }
        
    public ASTDeref(ASTNode e) {
		exp = e;
	}
}

