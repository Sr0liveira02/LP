import java.util.HashMap;
import java.util.List;

class ASTStruct implements ASTNode  {

    List<Bind> struct;

    ASTStruct(List<Bind> struct) {
        this.struct = struct;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        HashMap<String, IValue> lbl = new HashMap<String, IValue>();
        for (Bind b : struct) {
            IValue v = b.getExp().eval(e);
            if (v == null) {
                throw new InterpreterError("Evaluation error in struct: " + b.toString());
            }
            lbl.put(b.getId(), v);
        }
	    return new VStruct(lbl);          
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        HashMap<String, ASTType> lbl = new HashMap<String, ASTType>();
        for (Bind b : struct) {
            ASTType t = b.getExp().typecheck(e);
            if (t == null) {
                throw new TypeCheckerError("Type error in struct: " + b.toString());
            }
            lbl.put(b.getId(), t);
        }
        return new ASTTStruct(new TypeBindList(lbl));
    }

}
