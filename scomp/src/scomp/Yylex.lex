package scomp;

import java_cup.runtime.Symbol;

%%

%cup
%char
%line

%{
	private int firstCharacterIndexInCurrentLine = 0;
	
	private int currentLine = 1;
	
	private int currentColumn = 1;
	
	private final void updateLocation() {
		if (yyline + 1 > this.currentLine) {
			this.firstCharacterIndexInCurrentLine = yychar;
			this.currentLine = yyline + 1;
		}
		
		this.currentColumn = 1 + yychar - this.firstCharacterIndexInCurrentLine;
	}
	
	/**
	 * @param symbolId
	 * <br>Range: any integer
	 */
	private final DecafToken newToken(final int symbolId) {
		this.updateLocation();
		
		return new DecafToken(symbolId, this.currentLine, this.currentColumn);
	}
	
%}

%%

boolean { return this.newToken(DecafParserSymbols.BOOLEAN); }
break { return this.newToken(DecafParserSymbols.BREAK); }
callout { return this.newToken(DecafParserSymbols.CALLOUT); }
class { return this.newToken(DecafParserSymbols.CLASS); }
continue { return this.newToken(DecafParserSymbols.CONTINUE); }
else { return this.newToken(DecafParserSymbols.ELSE); }
false { return this.newToken(DecafParserSymbols.FALSE); }
if { return this.newToken(DecafParserSymbols.IF); }
int { return this.newToken(DecafParserSymbols.INT); }
return { return this.newToken(DecafParserSymbols.RETURN); }
true { return this.newToken(DecafParserSymbols.TRUE); }
void { return this.newToken(DecafParserSymbols.VOID); }
while { return this.newToken(DecafParserSymbols.WHILE); }

//.* { this.updateLocation(); }

[ \t]+ { this.updateLocation(); }

[\r\n\f]+ { /* Ignore */ }

. { this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, yytext()); }
