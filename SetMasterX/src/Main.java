public class Main {

    public static void main(String[] args) {

        new IDE();
        System.out.println(
                "\n========================="
        );

        System.out.println(
                "ANÁLISIS COMPLETADO"
        );

        System.out.println(
                "========================="
        );

        System.out.println(
                "Errores encontrados: "
                        + ErrorManager.errores.size()
        );

        System.out.println(
                "Reporte generado correctamente"
        );
    }

}