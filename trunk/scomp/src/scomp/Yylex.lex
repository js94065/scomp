package scomp;

import java_cup.runtime.Symbol;

%%

%cup

%%

boolean|break|callout|class|continue|else|false|if|int|return|true|void|while { return new Symbol(-1, yytext()); }

[ \t\r\n\f] { /* Ignore */ }

. { System.err.println("Illegal character: "+yytext()); }
