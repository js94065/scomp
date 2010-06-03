package java_cup.demos.listparser;

import java_cup.runtime.Symbol;

%%

%cup

%%

"(" { return new Symbol(ListParserSymbols.LEFT_PARENTHESIS); }

")" { return new Symbol(ListParserSymbols.RIGHT_PARENTHESIS); }

[a-zA-Z_]+ { return new Symbol(ListParserSymbols.IDENTIFIER, yytext()); }

[ \t\r\n\f] { /* Ignore */ }

. { System.err.println("Illegal character: "+yytext()); }
 