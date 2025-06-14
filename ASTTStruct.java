import java.util.List;

public class ASTTStruct implements ASTType {

    private TypeBindList ll;

    public ASTTStruct(TypeBindList llp) {
        ll = llp;
    }

    public ASTType getFieldType(String property) {
        return ll.getFieldType(property);
    }

    public boolean hasField(String field) {
        return ll.hasField(field);
    }

    public TypeBindList getTypeBindList() {
        return ll;
    }
    
    public String toStr() {
        return "Struct {" + ll.toString() + "}";
    }

    public void unfold(Environment<ASTType> env) throws TypeCheckerError {
        ll.unfold(env);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ASTTStruct) return ll.equals(((ASTTStruct)obj).getTypeBindList());
        else return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other) {
        if (other instanceof ASTTStruct) {
            ASTTStruct otherStruct = (ASTTStruct) other;
            List<String> otherProperties = otherStruct.getTypeBindList().getProperties();
            List<String> thisProperties = this.ll.getProperties();
            for (String property : thisProperties) {
                if (!otherProperties.contains(property)) {
                    return false; // This struct has a property not in the other struct
                }
                ASTType thisFieldType = this.ll.getFieldType(property);
                ASTType otherFieldType = otherStruct.getFieldType(property);
                if (thisFieldType == null || !thisFieldType.isSubTypeOf(otherFieldType)) {
                    return false; // Field type mismatch
                }
            }
            return true;
        }
        return false;
    }

}