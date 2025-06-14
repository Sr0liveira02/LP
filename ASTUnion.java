import java.util.HashMap;
import java.util.List;

class ASTUnion implements ASTNode  {

    List<Bind> struct;

    ASTUnion(List<Bind> struct) {
        this.struct = struct;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        Bind b = struct.get(0);
        return new VUnion(b.getId(), b.getExp().eval(e)); 
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
        
        return new ASTTUnion(new TypeBindList(lbl));
    }

}
