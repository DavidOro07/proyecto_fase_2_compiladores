import java.io.FileWriter;
import java.io.PrintWriter;

public class ReportGenerator {

    public static void generarReporte(){

        try{

            FileWriter archivo =
                    new FileWriter("reporte.txt");

            PrintWriter writer =
                    new PrintWriter(archivo);

            writer.println(
                    "===== REPORTE SETMASTER X =====\n"
            );

            // =========================
            // TABLA DE SÍMBOLOS
            // =========================

            writer.println(
                    "TABLA DE SÍMBOLOS\n"
            );

            for(String key : SymbolTable.tabla.keySet()){

                writer.println(
                        key
                                + " = "
                                + SymbolTable.tabla.get(key)
                );
            }

            // =========================
            // ERRORES
            // =========================

            writer.println(
                    "\nERRORES\n"
            );

            if(ErrorManager.errores.isEmpty()){

                writer.println(
                        "Sin errores."
                );

            } else {

                for(CompileError e :
                        ErrorManager.errores){

                    writer.println(
                            e.toString()
                    );
                }
            }

            writer.close();

            System.out.println(
                    "✅ Reporte generado"
            );

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}