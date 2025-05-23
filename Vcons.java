class Vcons implements IValue {
    IValue head;
    IValue tail;

    Vcons(IValue h, IValue t) {
        head = h;
        tail = t;
    }

    public IValue getHead() {
        return head;
    }

    public IValue getTail() {
        return tail;
    }
    
    public String toStr() {
        return "Lista: " + head.toStr();
        // return "(Lista: " + head.toStr() + " | " + tail.toStr() + ")";
    }

}