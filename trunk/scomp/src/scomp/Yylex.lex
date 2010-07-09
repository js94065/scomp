package scomp;

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
		
		return new DecafToken(symbolId, this.currentLine, this.currentColumn, this.yytext());
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
		
		return new DecafToken(symbolId, object, this.currentLine, this.currentColumn, this.yytext());
	}
	
%}

%%

boolean { return this.newToken(DecafParserSymbols.BOOLEAN); }
break { return this.newToken(DecafParserSymbols.BREAK); }
callout { return this.newToken(DecafParserSymbols.CALLOUT); }
class { return this.newToken(DecafParserSymbols.CLASS); }
continue { return this.newToken(DecafParserSymbols.CONTINUE); }
else { return this.newToken(DecafParserSymbols.ELSE); }
false { return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, false); }
if { return this.newToken(DecafParserSymbols.IF); }
int { return this.newToken(DecafParserSymbols.INT); }
return { return this.newToken(DecafParserSymbols.RETURN); }
true { return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, true); }
void { return this.newToken(DecafParserSymbols.VOID); }
while { return this.newToken(DecafParserSymbols.WHILE); }

"(" { return this.newToken(DecafParserSymbols.LEFT_PARENTHESIS); }
")" { return this.newToken(DecafParserSymbols.RIGHT_PARENTHESIS); }
"{" { return this.newToken(DecafParserSymbols.LEFT_BRACE); }
"}" { return this.newToken(DecafParserSymbols.RIGHT_BRACE); }
"[" { return this.newToken(DecafParserSymbols.LEFT_BRACKET); }
"]" { return this.newToken(DecafParserSymbols.RIGHT_BRACKET); }

'([ -!#-&(-\[\]-~]|\\\'|\\\"|\\t|\\n)' { return this.newToken(DecafParserSymbols.CHAR_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }

\"([ -!#-&(-\[\]-~]|\\\'|\\\"|\\t|\\n)*\" { return this.newToken(DecafParserSymbols.STRING_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }

[a-zA-Z_\.][a-zA-Z_\.0-9]* { return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }

0x[a-fA-F0-9]+ { return this.newToken(DecafParserSymbols.INT_LITERAL, this.yytext()); }

[0-9]+ { return this.newToken(DecafParserSymbols.INT_LITERAL, this.yytext()); }

"," { return this.newToken(DecafParserSymbols.COMMA); }
";" { return this.newToken(DecafParserSymbols.SEMI_COLON); }
"!" { return this.newToken(DecafParserSymbols.NOT); }
"+" { return this.newToken(DecafParserSymbols.PLUS); }
"-" { return this.newToken(DecafParserSymbols.MINUS); }
"*" { return this.newToken(DecafParserSymbols.TIMES); }
"/" { return this.newToken(DecafParserSymbols.DIVIDE); }
"%" { return this.newToken(DecafParserSymbols.MODULO); }
"<<" { return this.newToken(DecafParserSymbols.ARITHMETIC_SHIFT_LEFT); }
">>" { return this.newToken(DecafParserSymbols.ARITHMETIC_SHIFT_RIGHT); }
">>>" { return this.newToken(DecafParserSymbols.BITWISE_ROTATE_RIGHT); }
"=" { return this.newToken(DecafParserSymbols.ASSIGN); }
"<" { return this.newToken(DecafParserSymbols.LESSER); }
">" { return this.newToken(DecafParserSymbols.GREATER); }
"<=" { return this.newToken(DecafParserSymbols.LESSER_OR_EQUAL); }
">=" { return this.newToken(DecafParserSymbols.GREATER_OR_EQUAL); }
"==" { return this.newToken(DecafParserSymbols.EQUAL); }
"!=" { return this.newToken(DecafParserSymbols.NOT_EQUAL); }
"&&" { return this.newToken(DecafParserSymbols.AND); }
"||" { return this.newToken(DecafParserSymbols.OR); }

//.* { this.updateLocation(); }

[ \t]+ { this.updateLocation(); }

[\r\n\f]+ { /* Ignore */ }

. { this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
