public class ASTLazyList implements ASTNode {
    ASTNode head;
    ASTNode tail;


    public ASTLazyList(ASTNode h, ASTNode t) {
        head = h;
        tail = t;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VIcons(head, tail, e);
    }
   
}
