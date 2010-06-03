package scomp;

import java_cup.runtime.Symbol;

%%

%cup

%%

boolean { return new Symbol(DecafParserSymbols.BOOLEAN); }
break { return new Symbol(DecafParserSymbols.BREAK); }
callout { return new Symbol(DecafParserSymbols.CALLOUT); }
class { return new Symbol(DecafParserSymbols.CLASS); }
continue { return new Symbol(DecafParserSymbols.CONTINUE); }
else { return new Symbol(DecafParserSymbols.ELSE); }
false { return new Symbol(DecafParserSymbols.FALSE); }
if { return new Symbol(DecafParserSymbols.IF); }
int { return new Symbol(DecafParserSymbols.INT); }
return { return new Symbol(DecafParserSymbols.RETURN); }
true { return new Symbol(DecafParserSymbols.TRUE); }
void { return new Symbol(DecafParserSymbols.VOID); }
while { return new Symbol(DecafParserSymbols.WHILE); }

[ \t\r\n\f] { /* Ignore */ }

. { System.err.println("Illegal character: "+yytext()); }
