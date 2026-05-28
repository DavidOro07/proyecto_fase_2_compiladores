public class CompileError {

    public String tipo;
    public String mensaje;
    public int linea;
    public int columna;

    public CompileError(
            String tipo,
            String mensaje,
            int linea,
            int columna
    ) {

        this.tipo = tipo;
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {

        return "[" + tipo + "] " +
                mensaje +
                " (Línea: " + linea +
                ", Columna: " + columna + ")";
    }
}