import java.util.*;

public class SymbolTable {

    public static HashMap<String, Set<Integer>> tabla =
            new HashMap<>();

    public static void guardar(String nombre, Set<Integer> conjunto) {

        tabla.put(nombre, conjunto);

        System.out.println("✔ Conjunto guardado: "
                + nombre + " = " + conjunto);
    }

    public static Set<Integer> obtener(String nombre) {

        if (!tabla.containsKey(nombre)) {
            throw new RuntimeException(
                    "❌ El conjunto '" + nombre + "' no existe."
            );
        }

        return tabla.get(nombre);
    }

    public static void mostrarTabla() {

        System.out.println("\n===== TABLA DE SÍMBOLOS =====");

        for (String key : tabla.keySet()) {
            System.out.println(key + " = " + tabla.get(key));
        }
    }
}
