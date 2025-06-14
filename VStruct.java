import java.util.HashMap;

class VStruct implements IValue {
    HashMap<String, IValue> lbl;

    VStruct(HashMap<String, IValue> value0) {
        lbl = value0;
    }

    IValue getField(String property) {
        return lbl.get(property);
    }

    public String toStr() {
        return "Strutura assim: " + lbl.toString();
    }
}