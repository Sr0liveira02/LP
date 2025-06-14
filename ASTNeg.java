public class ASTNeg implements ASTNode {

    ASTNode exp;

	public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
		ASTType t = exp.typecheck(e);
		if (t instanceof ASTTInt) {
			return new ASTTInt();
		} else {
			throw new TypeCheckerError("illegal types to neg operator");
		}
	}

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue v0 = exp.eval(e); 
		if (v0 instanceof VInt) { 
			return new VInt(-((VInt)v0).getval()); 
		} else { 
			throw new InterpreterError("illegal types to neg operator"); 
		}
    }
        
    public ASTNeg(ASTNode e) {
		exp = e;
    }

}

