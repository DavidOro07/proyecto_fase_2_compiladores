public class VennGenerator {

    public static String operacionActual = "";

    public static void generar(String operacion) {

        operacionActual = operacion;

        new VennFrame(operacion);
    }
}