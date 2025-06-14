import java.util.*;

public class TypeBindList  {

        private HashMap<String, ASTType> lbl;

        public TypeBindList(HashMap<String, ASTType> ll) {
                lbl = ll;
        }

        public ASTType getFieldType(String name) {
                return lbl.get(name);
        }

        public boolean hasField(String field) {
                return lbl.containsKey(field);
        }

        public List<String> getProperties() {
                return new ArrayList<>(lbl.keySet());
        }

        public void unfold(Environment<ASTType> env) throws TypeCheckerError {
                for (Map.Entry<String, ASTType> entry : lbl.entrySet()) {
                        ASTType type = entry.getValue();
                        if (type instanceof ASTTStruct) {
                                ((ASTTStruct) type).unfold(env);
                        }
                        if (type instanceof ASTTId) {
                                ASTType unfolded = ((ASTTId) type).get(env);
                                entry.setValue(unfolded);
                        }
                }
        }

        @Override
        public boolean equals(Object obj) {
                if (!(obj instanceof TypeBindList)) return false;
                return getProperties().equals(((TypeBindList)obj).getProperties());
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("TypeBindList: ");
                for (Map.Entry<String, ASTType> entry : lbl.entrySet()) {
                        sb.append(entry.getKey()).append(": ").append(entry.getValue().toStr()).append(", ");
                }
                return sb.toString();
        }

}