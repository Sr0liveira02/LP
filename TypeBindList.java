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

        public HashMap<String, ASTType> getMap() {
                return lbl;
        }

        public boolean equals(Object obj, Environment<ASTType> env) throws TypeCheckerError {
                if (!(obj instanceof TypeBindList)) return false;
                TypeBindList other = (TypeBindList) obj;

                // Check if both maps have the same keys
                if (!lbl.keySet().equals(other.getMap().keySet())) return false;

                // Compare each corresponding ASTType using specialEquals
                for (String key : lbl.keySet()) {
                        ASTType thisType = lbl.get(key);
                        ASTType otherType = other.getMap().get(key);
                        if (thisType == null || otherType == null) return false;
                        if (!thisType.specialEquals(otherType, env)) return false;
                }
                return true;
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