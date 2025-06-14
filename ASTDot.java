public class ASTDot implements ASTNode {
    ASTNode struct;
    String field;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType isStruct = struct.typecheck(e);
        // System.out.println("Tipo do struct: " + isStruct.getClass());
        // System.out.println("Verificando tipo do struct: " + isStruct.toStr());
        if (isStruct instanceof ASTTId) {
            isStruct = ((ASTTId) isStruct).get(e);
        }
        if (!(isStruct instanceof ASTTStruct)) {
            throw new TypeCheckerError("Tipo incompatível: Esperado um struct: " + isStruct.toStr());
        }
        ASTTStruct structType = (ASTTStruct) isStruct;
        if (!structType.hasField(field)) {
            throw new TypeCheckerError("Campo '" + field + "' não existe no struct.");
        }
        return structType.getFieldType(field);
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue something = struct.eval(e);
        if (!(something instanceof VStruct)) {
            throw new InterpreterError("Esperado um struct, mas encontrado: " + something.toStr());
        }
        VStruct structValue = (VStruct) something;
        return structValue.getField(field);
    }

    public ASTDot(ASTNode l0, String r0) {
        struct = l0;
        field = r0;
    }

}
