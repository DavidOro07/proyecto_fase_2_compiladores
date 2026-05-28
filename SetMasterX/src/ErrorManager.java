import java.util.ArrayList;

public class ErrorManager {

    public static ArrayList<CompileError> errores =
            new ArrayList<>();

    // ======================================
    // AGREGAR ERROR
    // ======================================

    public static void addError(
            String tipo,
            String mensaje,
            int linea,
            int columna
    ) {

        errores.add(
                new CompileError(
                        tipo,
                        mensaje,
                        linea,
                        columna
                )
        );
    }

    // ======================================
    // LIMPIAR
    // ======================================

    public static void clear() {

        errores.clear();
    }

    // ======================================
    // HAY ERRORES
    // ======================================

    public static boolean hasErrors() {

        return !errores.isEmpty();
    }

    // ======================================
    // MOSTRAR
    // ======================================

    public static String getErrors() {

        StringBuilder sb =
                new StringBuilder();

        for(CompileError e : errores){

            sb.append(e.toString())
                    .append("\n");
        }

        return sb.toString();
    }
}