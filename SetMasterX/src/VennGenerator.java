import java.util.Set;

public class VennGenerator {

    public static String operacionActual = "";

    public static void generar(
            String operacion,
            Set<Integer> resultado,
            Set<Integer> A,
            Set<Integer> B
    ){

        new VennFrame(
                operacion,
                resultado,
                A,
                B
        );
    }
}