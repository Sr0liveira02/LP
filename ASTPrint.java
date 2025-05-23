public class ASTPrint implements ASTNode {

  ASTNode exp;

  public IValue eval(Environment <IValue>e) throws InterpreterError { 
    IValue v0 = exp.eval(e); 
    System.out.print(v0.toStr());
    return new VInt(0);
  }
      
  public ASTPrint(ASTNode e) {
    exp = e;
  }

}

