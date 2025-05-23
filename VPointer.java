class VPointer implements IValue {
    IValue value;

    VPointer(IValue value0) {
        value = value0;
    }

    public void setValue(IValue value0) {
        value = value0;
    }

    IValue getValue() {
        return value;
    }

    public String toStr() {
        return "Ponteiro para: " + value.toString();
    }
}