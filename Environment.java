import java.util.*;

public class Environment <E>{
    Environment<E> anc;
    Map<String, E> bindings;

    Environment(){
        anc = null;
        bindings = new HashMap<String,E>();
    }
    
    Environment(Environment<E> ancestor){
        anc = ancestor;
        bindings = new HashMap<String,E>();
    }

    Environment<E> beginScope(){
        return new Environment<E>(this);
    }
    
    Environment<E> endScope(){
        return anc;
    }

    void assoc(String id, E bind) throws InterpreterError {
        bindings.put(id, bind);
    }


    E find(String id) throws InterpreterError {
        E bind = bindings.get(id);
        if (bind != null) {
            return bind;
        }
        if (anc != null) {
            return anc.find(id);
        }
        throw new InterpreterError("Unbound variable: " + id);
    }

}
