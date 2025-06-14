public class ASTFun implements ASTNode {
    String _identifier;
    ASTType argType;
    ASTNode body;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        try {
        ASTType something = argType;
        if (something instanceof ASTTId) {
            argType = ((ASTTId)something).get(e);
        }
        else
            something.unfold(e);
        
        
        Environment<ASTType> newEnv = e.beginScope();
        newEnv.assoc(_identifier, argType);
        // System.out.println("Associando " + _identifier + " com o tipo: " + argType.toStr());
        // Type check the body of the function
        ASTType bodyType = body.typecheck(newEnv);
        // System.out.println("Something just so i dont go crazy: " + _identifier);
        
        // Return a function type with the identifier and body type
        // System.out.println("Type of " + _identifier + " receives " + argType.toStr() + " e devolve: " + bodyType.toStr());
        // descer o argType a criar o ASTTArrow em base do body type
        
        ASTTArrow ret = new ASTTArrow(argType, bodyType);
        newEnv.endScope();
        return ret;
        // System.out.println("Function: " + _identifier + " receives: " + argType.toStr() + " e devolve " + bodyType.toStr());
        //return rightAssociate(argType, bodyType);
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
        // return ret;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VClosure(_identifier, body, e);
    }

    public ASTFun(String indentifier, ASTType type, ASTNode b) {
        this._identifier = indentifier;
        body = b;
        argType = type;
    }

    public ASTNode getBody() {
        return body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }

}
