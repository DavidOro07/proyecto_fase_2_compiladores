%%
%class Lexer
%unicode
%cup
%line
%column

%{

import java_cup.runtime.Symbol;

private Symbol symbol(int type){
    return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value){
    return new Symbol(type, yyline, yycolumn, value);
}

%}

DIGIT = [0-9]
ID = [a-zA-Z][a-zA-Z0-9]*

%%

"SET_START"       { return symbol(sym.SET_START); }
"SET_END"         { return symbol(sym.SET_END); }

"INICIO"          { return symbol(sym.INICIO); }
"FIN"             { return symbol(sym.FIN); }

"VENN"            { return symbol(sym.VENN); }

"∪"               { return symbol(sym.UNION); }
"∩"               { return symbol(sym.INTERSECCION); }
"-"               { return symbol(sym.DIFERENCIA); }
"Δ"               { return symbol(sym.DIF_SIM); }

"{"               { return symbol(sym.LLAVE_IZQ); }
"}"               { return symbol(sym.LLAVE_DER); }

"("               { return symbol(sym.PAR_IZQ); }
")"               { return symbol(sym.PAR_DER); }

","               { return symbol(sym.COMA); }
";"               { return symbol(sym.PYC); }

"="               { return symbol(sym.IGUAL); }

{DIGIT}+ {

    return symbol(
            sym.NUMERO,
            Integer.parseInt(yytext())
    );
}

{ID} {

    return symbol(
            sym.ID,
            yytext()
    );
}

"//".* { }
[ \t\r\n]+ { }

. {

    ErrorManager.addError(
            "Léxico",
            "Caracter ilegal: " + yytext(),
            yyline + 1,
            yycolumn + 1
    );
}