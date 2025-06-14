import java.util.*;

public class ASTTypeDef implements ASTNode {
    HashMap<String,ASTType> ltd;
    ASTNode body;

    public ASTTypeDef(HashMap<String,ASTType>  ltdp, ASTNode b) {
        ltd = ltdp;
        body = b;
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeCheckerError {
        try {
        Environment<ASTType> en = env.beginScope();
        for (Map.Entry<String, ASTType> entry : ltd.entrySet()) {
            en.assoc(entry.getKey(), entry.getValue());
        }
        ASTType bodyType = body.typecheck(en);
        en.endScope();
        return bodyType;
        } catch (InterpreterError err){
            throw new TypeCheckerError(err.getMessage());
        }
    }
    
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return body.eval(env);
    }
}
