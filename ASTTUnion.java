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

    public boolean equals(Object obj, Environment<ASTType> env) throws TypeCheckerError { // maybe so tens q ter 1 em comun idk
        if (obj instanceof ASTTUnion) return ll.equals(((ASTTUnion)obj).getTypeBindList());
        else return false;
    }

    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
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
                if (thisFieldType == null || !otherFieldType.specialIsSubTypeOf(thisFieldType, env)) {
                    return false; // Field type mismatch
                }
            }
            return true;
        }
        return false;
    }

}