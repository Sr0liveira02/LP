public class ASTList implements ASTNode {
    ASTNode head;
    ASTNode tail;

    public ASTList(ASTNode h, ASTNode t) {
        head = h;
        tail = t;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new Vcons(head.eval(e), tail.eval(e));
    }
   
}
