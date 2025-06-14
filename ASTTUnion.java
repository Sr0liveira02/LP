import java.util.List;

public class ASTTUnion implements ASTType {

    private TypeBindList ll;

    public ASTTUnion(TypeBindList llp) {
        ll = llp;
    }

    public ASTType getFieldType(String property) {
        return ll.getFieldType(property);
    }

    public boolean isInUnion(String property) {
        return ll.getFieldType(property) != null;
    }

    public List<String> getProperties() {
        return ll.getProperties();
    }

    public TypeBindList getTypeBindList() {
        return ll;
    }
    
    public String toStr() {
        return "Union {" + ll.toString() + "}";
    }

    public void unfold(Environment<ASTType> env) throws TypeCheckerError {
        ll.unfold(env);
    }

    @Override
    public boolean equals(Object obj) { // maybe so tens q ter 1 em comun idk
        if (obj instanceof ASTTUnion) return ll.equals(((ASTTUnion)obj).getTypeBindList());
        else return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        if (other instanceof ASTTUnion) {
            ASTTUnion otherStruct = (ASTTUnion) other;
            List<String> otherProperties = otherStruct.getTypeBindList().getProperties();
            List<String> thisProperties = this.ll.getProperties();
            for (String property : otherProperties) {
                if (!thisProperties.contains(property)) {
                    return false; // This struct has a property not in the other struct
                }
                ASTType thisFieldType = this.ll.getFieldType(property);
                ASTType otherFieldType = otherStruct.getFieldType(property);
                if (thisFieldType == null || !otherFieldType.isSubTypeOf(thisFieldType)) {
                    return false; // Field type mismatch
                }
            }
            return true;
        }
        return false;
    }

}