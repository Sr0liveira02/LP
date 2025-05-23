public class ASTDeRef implements ASTNode {

    ASTNode exp;

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue v0 = exp.eval(e); 
		if (v0 instanceof VPointer) { 
			return ((VPointer)v0).getValue();
		} else { 
			throw new InterpreterError("illegal types to dereference operator (*)"); 
		}
    }
        
    public ASTDeRef(ASTNode e) {
		exp = e;
	}
}

