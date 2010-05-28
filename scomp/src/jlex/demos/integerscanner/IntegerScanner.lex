package jlex.demos.integerscanner;

/**
 * @author codistmonk (creation 2010-05-28)
 */
class Yytoken {
	// Nothing
}

%%
%%
[0-9]+ { System.out.println("Integer detected: " + yytext()); }
.      { /* Ignore all other characters */ }
