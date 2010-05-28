package jlex.demos.integerlistscanner;

/**
 * @author js94065 (creation 2010-05-28)
 */
class Yytoken { int field; Yytoken(int f) { field=f; } } 

%%
%%
[0-9]+ { return yylex(); }
" "*   { /* Input list is separated by spaces */}
.      { /* Ignore all other characters */ }
