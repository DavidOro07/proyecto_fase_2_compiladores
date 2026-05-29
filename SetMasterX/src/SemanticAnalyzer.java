public class SemanticAnalyzer {

    // =====================================
    // EXISTE VARIABLE
    // =====================================

    public static boolean existeVariable(String id){

        return SymbolTable.tabla.containsKey(id);
    }

    // =====================================
    // VERIFICAR VARIABLE
    // =====================================

    public static void verificarVariable(String id){

        if(!existeVariable(id)){

            ErrorManager.addError(
                    "Semántico",
                    "Variable no declarada: " + id,
                    0,
                    0
            );
        }
    }
}