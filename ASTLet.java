import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        try {
        Environment<ASTType> en = e.beginScope();
        // System.out.println(decls.size() + " declarations in let");
        for (Bind b : decls) {
            if (b.getType() != null) {
                // System.out.println("Associating " + b.getId() + " with type: " + b.getType().toStr());
                ASTType t = b.getType();
                if (t instanceof ASTTId) 
                    t = ((ASTTId)t).get(en);
                else
                    t.unfold(en);
                en.assoc(b.getId(), t);
                ASTType exp = b.getExp().typecheck(en);
                //System.out.println("Type of " + b.getId() + " is: " + t.toStr() + ", expression type is: " + exp.toStr());
                if (!t.isSubTypeOf(exp))
                    throw new TypeCheckerError("Illegal type for " + b.getId() + ": expected " + t.toStr() + ", found " + exp.toStr());
            } else {
                ASTType t = b.getExp().typecheck(en);
                // if (t instanceof ASTTId) t = ((ASTTId)t).get(en);
                // System.out.println("Associating " + b.getId() + " with type: " + t.toStr());
                en.assoc(b.getId(), t);
            }
        }
        ASTType bodyType = body.typecheck(en);
        en.endScope();
        return bodyType;
        } catch (InterpreterError err){
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        Environment<IValue> en = e.beginScope();
        for (Bind b : decls) {
            IValue v = b.getExp().eval(en);
            /*if (v instanceof VUnion) { Arranjar uma maneira de o union saber o seu cocntexto pq ele precisa exemplo 16
                VUnion vu = (VUnion)v;
                vu.setType(((VUnion)b.getType()));
            }*/
           
            en.assoc(b.getId(), v);
        }
        IValue v = body.eval(en);
        en.endScope();
        return v;
    }

    public ASTLet(List<Bind> decls, ASTNode b) {
        this.decls = decls;
        body = b;
    }

}
