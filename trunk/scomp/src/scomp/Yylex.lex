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
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	private final DecafToken newToken(final int symbolId) {
		this.updateLocation();
		
		return new DecafToken(symbolId, this.currentLine, this.currentColumn);
	}
	
	/**
	 * @param symbolId
	 * <br>Range: any integer
	 * @param object
	 * <br>Can be null
	 * <br>Shared parameter
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	private final DecafToken newToken(final int symbolId, final Object object) {
		this.updateLocation();
		
		return new DecafToken(symbolId, object, this.currentLine, this.currentColumn);
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

[a-zA-Z_\.][a-zA-Z_\.0-9]* { return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }

0x[a-fA-F0-9]+ { return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext().substring(2), 16)); }

[0-9]+ { return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext())); }

//.* { this.updateLocation(); }

[ \t]+ { this.updateLocation(); }

[\r\n\f]+ { /* Ignore */ }

. { this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
