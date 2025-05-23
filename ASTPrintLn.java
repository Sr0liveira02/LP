public class ASTPrintLn implements ASTNode {

  ASTNode exp;

  public IValue eval(Environment <IValue>e) throws InterpreterError { 
    IValue v0 = exp.eval(e); 
    System.out.println(v0.toStr());
    return new VInt(0);
  }
      
  public ASTPrintLn(ASTNode e) {
    exp = e;
  }

}

