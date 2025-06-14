import java.util.List;

public class ASTMatchUnit implements ASTNode {
        ASTNode variable;
        List<MatchStatement> statements;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType var = variable.typecheck(e);
                if (!(var instanceof ASTTUnion)) {
                        throw new TypeCheckerError("Match unit expects a union type, found: " + var.toStr());
                }
                ASTTUnion unionType = (ASTTUnion) var;
                boolean hasField = false;
                for (MatchStatement b : statements) {
                        if (unionType.isInUnion(b.getLabel())) {
                                hasField = true;
                                break;
                        }
                }

                if (!hasField) {
                        throw new TypeCheckerError("Match unit does not match any field in union: " + unionType.toStr());
                }

                try {
                        ASTType outT = null;
                        for (MatchStatement b : statements) {
                                if (!unionType.isInUnion(b.getLabel())) {
                                        continue;
                                }
                                Environment<ASTType> newEnv = e.beginScope();
                                newEnv.assoc(b.getId(), unionType.getFieldType(b.getLabel()));
                                ASTType newoutT = b.getExp().typecheck(newEnv);
                                newEnv.endScope();
                                if (outT != null && !outT.equals(newoutT)) {
                                        throw new TypeCheckerError("Type mismatch in match unit: " + outT.toStr() + " and " + newoutT.toStr());
                                }
                                outT = newoutT;
                        }
                        return outT;
                } catch (InterpreterError err) {
                        throw new TypeCheckerError(err.getMessage());
                }
        }

        

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue maybeUnion = variable.eval(e);
                if (!(maybeUnion instanceof VUnion)) {
                        throw new InterpreterError("Input of match is not a union: " + maybeUnion.toStr());
                }

                VUnion unionValue = (VUnion) maybeUnion;
                for (MatchStatement b : statements) {
                        if (unionValue.getNameOfValue().equals(b.getLabel())) {
                                Environment<IValue> newEnv = e.beginScope();
                                newEnv.assoc(b.getId(), unionValue.get());
                                IValue result = b.getExp().eval(newEnv);
                                newEnv.endScope();
                                return result;
                        }
                }
        
                throw new InterpreterError("Match unit does not match any field in union: " + maybeUnion.toStr());
        }

        public ASTMatchUnit(ASTNode variable, List<MatchStatement> statements) {
                this.variable = variable;
                this.statements = statements;
        }

}
