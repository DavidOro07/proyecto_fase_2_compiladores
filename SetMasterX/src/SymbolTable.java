import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

    public static Map<String, Set<Integer>> tabla =
            new HashMap<>();

    // =====================================
    // GUARDAR VARIABLE
    // =====================================

    public static void put(
            String id,
            Set<Integer> valor
    ) {

        if(tabla.containsKey(id)){

            ErrorManager.addError(
                    "Semántico",
                    "Variable ya declarada: " + id,
                    0,
                    0
            );

            return;
        }

        tabla.put(id, valor);
    }

    // =====================================
    // OBTENER VARIABLE
    // =====================================

    public static Set<Integer> get(String id) {

        if(!tabla.containsKey(id)){

            ErrorManager.addError(
                    "Semántico",
                    "Variable no declarada: " + id,
                    0,
                    0
            );

            return null;
        }

        return tabla.get(id);
    }

    // =====================================
    // EXISTE
    // =====================================

    public static boolean exists(String id){

        return tabla.containsKey(id);
    }

    // =====================================
    // LIMPIAR
    // =====================================

    public static void clear(){

        tabla.clear();
    }
}