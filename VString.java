class VString implements IValue {
    String v;

    VString(String v0) {
        v = v0;
    }

    String getval() {
        return v;
    }

    public String toStr() {
        return v;
    }
}