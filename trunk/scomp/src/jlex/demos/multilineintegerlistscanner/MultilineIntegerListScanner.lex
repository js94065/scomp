package jlex.demos.multilineintegerlistscanner;

%%

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
%}

%%

[0-9]+ { this.updateLocation(); return new Yytoken(this.currentLine, this.currentColumn, Integer.parseInt(this.yytext())); }

//.* { this.updateLocation(); }

(\n|\f|\r)+ { /* Ignore */ }

(" "|\t)+ { this.updateLocation(); }

. { this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, yytext()); }
