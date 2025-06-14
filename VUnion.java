import java.util.List;

class VUnion implements IValue {
    IValue value;
    String identifier;
    TypeBindList type;

    VUnion(String identifier, IValue value) {
        this.value = value;
        this.identifier = identifier;
        this.type = null;
    }

    VUnion(String identifier, IValue value, TypeBindList type) {
        this.value = value;
        this.identifier = identifier;
        this.type = type;
    }

    String getNameOfValue() {
        return identifier;
    }

    IValue get() {
        return value;
    }

    public void setType(TypeBindList type) {
        this.type = type;
    }

    public List<String> getType() {
        return type.getProperties();
    }

    public TypeBindList getTypeBindList() {
        return type;
    }

    public String toStr() {
        return "Union com: " + value.toString();
    }
}