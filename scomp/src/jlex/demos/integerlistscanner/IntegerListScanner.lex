package jlex.demos.integerlistscanner;

/**
 * @author js94065 (creation 2010-05-28)
 */
class Yytoken {
	
	private final int intValue;
	
	Yytoken(final int intValue) {
		this.intValue = intValue;
	}
	
	public final int getIntValue() {
		return this.intValue;
	}
	
}

%%
%%
[0-9]+ { return new Yytoken(Integer.parseInt(yytext()))/* TODO */; }
" "+   { /* Input list is separated by spaces */}
