import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        Environment<IValue> en = e.beginScope();
        for (Bind b : decls) {
            IValue v = b.getExp().eval(en);
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
