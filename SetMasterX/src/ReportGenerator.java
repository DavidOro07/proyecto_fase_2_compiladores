import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {

    public static void generarReporte() {

        try {

            FileWriter writer =
                    new FileWriter("reporte.txt");

            writer.write(
                    "===== REPORTE =====\n\n"
            );

            writer.write(
                    "TABLA DE SÍMBOLOS\n\n"
            );

            for(String key : SymbolTable.tabla.keySet()){

                writer.write(
                        key + " = " +
                                SymbolTable.tabla.get(key)
                                + "\n"
                );
            }

            writer.write("\n");

            writer.write(
                    "ERRORES\n\n"
            );

            writer.write(
                    ErrorManager.getErrors()
            );

            writer.close();

            System.out.println(
                    "Reporte generado"
            );

        } catch(IOException e){

            e.printStackTrace();
        }
    }
}