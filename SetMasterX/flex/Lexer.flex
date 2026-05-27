%%

%class Lexer
%public
%unicode
%line
%column
%cup

%{

private java.util.HashMap<String, String> tablaSimbolos = new java.util.HashMap<>();

private java_cup.runtime.Symbol symbol(int type) {
    return new java_cup.runtime.Symbol(type, yyline + 1, yycolumn + 1);
}

private java_cup.runtime.Symbol symbol(int type, Object value) {
    return new java_cup.runtime.Symbol(type, yyline + 1, yycolumn + 1, value);
}
public void mostrarTablaSimbolos() {
    System.out.println("\n TABLA DE SÍMBOLOS:");
    for (String key : tablaSimbolos.keySet()) {
        System.out.println("ID: " + key + " → Tipo: " + tablaSimbolos.get(key));
    }
}

%}

DIGITO = [0-9]
LETRA = [a-zA-Z]
ID = {LETRA}({LETRA}|{DIGITO})*
ESPACIO = [ \t\r\n]+

%%

// Palabras clave
"SET_START"   { return symbol(sym.SET_START); }
"SET_END"     { return symbol(sym.SET_END); }
"INICIO"      { return symbol(sym.INICIO); }
"FIN"         { return symbol(sym.FIN); }
"SI"          { return symbol(sym.SI); }
"ENTONCES"    { return symbol(sym.ENTONCES); }
"PARA_CADA"   { return symbol(sym.PARA_CADA); }
"EN"          { return symbol(sym.EN); }
"VENN"        { return symbol(sym.VENN); }

// Operadores
"∪"           { return symbol(sym.UNION); }
"∩"           { return symbol(sym.INTERSECCION); }
"-"           { return symbol(sym.DIFERENCIA); }
"Δ"           { return symbol(sym.DIF_SIMETRICA); }

// Relacionales
"⊂"           { return symbol(sym.SUBCONJUNTO); }
"∈"           { return symbol(sym.PERTENECE); }
"=="          { return symbol(sym.IGUALDAD); }

// Símbolos
"{"           { return symbol(sym.LLAVE_ABRE); }
"}"           { return symbol(sym.LLAVE_CIERRA); }
"("           { return symbol(sym.PAREN_ABRE); }
")"           { return symbol(sym.PAREN_CIERRA); }
","           { return symbol(sym.COMA); }
";"           { return symbol(sym.PUNTO_COMA); }
"="           { return symbol(sym.ASIGNACION); }

// Literales
{DIGITO}+     { return symbol(sym.ENTERO, Integer.parseInt(yytext())); }
\'[a-zA-Z]\'  { return symbol(sym.CARACTER, yytext()); }

// Identificadores
{ID} {
    tablaSimbolos.put(yytext(), "ID");
    return symbol(sym.ID, yytext());
}

// Espacios
{ESPACIO} { }

// Error léxico
. {
    System.out.println(
        " ERROR LÉXICO → Símbolo no válido: '" + yytext() + "'" +
        " | Línea: " + (yyline+1) +
        " | Columna: " + (yycolumn+1)
    );
}