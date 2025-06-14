public class ASTNot implements ASTNode {

    ASTNode exp;

	public ASTType typecheck(Environment <ASTType>e) throws TypeCheckerError {
		ASTType t = exp.typecheck(e);
		if (t instanceof ASTTBool) {
			return new ASTTBool();
		} else {
			throw new TypeCheckerError("illegal types to not operator");
		}
	}

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue v0 = exp.eval(e); 
		if (v0 instanceof VBool) { 
			return new VBool(((VBool)v0).getval() == false); 
		} else { 
			throw new InterpreterError("illegal types to not operator"); 
		}
    }
        
    public ASTNot(ASTNode e) {
		exp = e;
    }

}

