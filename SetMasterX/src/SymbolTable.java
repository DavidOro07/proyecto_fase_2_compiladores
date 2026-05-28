import java.util.*;

public class SymbolTable {

    public static HashMap<String, Set<Integer>> tabla =
            new HashMap<>();

    // ==========================================
    // GUARDAR
    // ==========================================

    public static void guardar(
            String nombre,
            Set<Integer> conjunto
    ) {

        tabla.put(nombre, conjunto);
    }

    // ==========================================
    // OBTENER
    // ==========================================

    public static Set<Integer> obtener(String nombre) {

        if (!tabla.containsKey(nombre)) {

            throw new RuntimeException(
                    "❌ El conjunto '" + nombre + "' no existe."
            );
        }

        return tabla.get(nombre);
    }

    // ==========================================
    // EXISTE
    // ==========================================

    public static boolean existe(String id){

        return tabla.containsKey(id);
    }

    // ==========================================
    // MOSTRAR TABLA
    // ==========================================

    public static void mostrarTabla() {

        System.out.println(
                "\n===== TABLA DE SÍMBOLOS ====="
        );

        for(String key : tabla.keySet()) {

            System.out.println(
                    key + " = " + tabla.get(key)
            );
        }
    }
}