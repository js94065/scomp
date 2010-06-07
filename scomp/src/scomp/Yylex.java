package scomp;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"47:9,48,50,47,51,50,47:18,49,36,28,26:2,40,44,25,19,20,39,37,34,38,29,46,31" +
",30:9,26,35,41,43,42,26:2,33:6,29:20,23,27,24,26,29,26,5,1,9,16,4,14,29,18," +
"13,29,8,3,29,6,2,29:2,7,12,11,10,15,17,32,29:2,21,45,22,26,47,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,108,
"0,1,2,1:6,3,4,1:2,5,1:4,6,7,8,9,10,1:4,11,1:4,12,10,1,13,1,10:11,14,15,1,16" +
",17,18,19:2,20,21,22,23,24,13,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39," +
"40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,10,63," +
"64,65,66,67,68,69")[0];

	private int yy_nxt[][] = unpackFromString(70,52,
"1,2,100:2,84,100:2,102,100,104,100,85,100,49,105,86,100,106,100,3,4,5,6,7,8" +
",9,50:2,54,100,10,51,100:2,11,12,13,14,15,16,17,18,19,57,60,63,65,50,20:2,2" +
"1:2,-1:53,100,107,100:4,87,100:11,-1:10,100:5,-1:19,48:24,-1,48,52,-1,48:18" +
",-1:2,48,-1:32,10:2,-1:63,24,-1:49,25,-1,26,-1:50,27,28,-1:56,20:2,-1:52,21" +
":2,-1,100:18,-1:10,100:5,-1:60,36,-1:10,32:49,-1,32,-1,35,-1:2,35:2,-1:3,35" +
",-1:4,35,-1,35,-1:13,35:2,-1,35,-1:43,34,-1:27,100:5,53,100:7,22,100:4,-1:1" +
"0,100:5,-1:48,10:2,61,-1:25,48,-1:4,48,-1:13,48,-1:2,48,-1:24,100:10,33,100" +
":7,-1:10,100:5,-1:19,55:24,-1,55,58,23,55:18,-1:2,55,-1:3,100:3,37,100:14,-" +
"1:10,100:5,-1:61,29,-1:14,55,-1:4,55,-1:13,55,-1:2,55,-1:24,100:3,38,100:14" +
",-1:10,100:5,-1:62,30,-1:8,100:15,39,100:2,-1:10,100:5,-1:63,31,-1:7,100:7," +
"40,100:10,-1:10,100:5,-1:64,32,-1:6,100:11,41,100:6,-1:10,100:5,-1:19,100:3" +
",42,100:14,-1:10,100:5,-1:19,100:3,43,100:14,-1:10,100:5,-1:19,100:5,44,100" +
":12,-1:10,100:5,-1:19,100:5,45,100:12,-1:10,100:5,-1:19,100:10,46,100:7,-1:" +
"10,100:5,-1:19,100:3,47,100:14,-1:10,100:5,-1:19,100:11,56,100:6,-1:10,100:" +
"5,-1:19,100:9,59,100:8,-1:10,100:5,-1:19,100:12,62,100:5,-1:10,100:5,-1:19," +
"100:4,64,100:13,-1:10,100:5,-1:19,100:11,66,100:6,-1:10,100:5,-1:19,100:11," +
"67,100:6,-1:10,100:5,-1:19,100:2,68,100:15,-1:10,100:5,-1:19,100:6,69,100:1" +
"1,-1:10,100:5,-1:19,100:4,70,100:13,-1:10,100:5,-1:19,100:9,71,100:8,-1:10," +
"100:5,-1:19,100:9,72,100:8,-1:10,100:5,-1:19,100:2,73,100:15,-1:10,100:5,-1" +
":19,100:6,74,100:11,-1:10,100:5,-1:19,100,75,100:16,-1:10,100:5,-1:19,100:3" +
",76,100:14,-1:10,100:5,-1:19,100:10,94,100:7,-1:10,100:5,-1:19,100:5,95,100" +
":12,-1:10,100:5,-1:19,100:4,77,100:13,-1:10,100:5,-1:19,100:2,103,100:15,-1" +
":10,100:5,-1:19,100:2,78,100:15,-1:10,100:5,-1:19,100:12,79,100:5,-1:10,100" +
":5,-1:19,100:9,80,100:8,-1:10,100:5,-1:19,100:10,97,100:7,-1:10,100:5,-1:19" +
",100:3,81,100:14,-1:10,100:5,-1:19,100:12,99,100:5,-1:10,100:5,-1:19,100,82" +
",100:16,-1:10,100:5,-1:19,100:5,83,100:12,-1:10,100:5,-1:19,100:2,96,100:15" +
",-1:10,100:5,-1:19,100:3,88,100:14,-1:10,100:5,-1:19,100:2,98,100:15,-1:10," +
"100:5,-1:19,100,89,90,100,91,100:13,-1:10,100:5,-1:19,100:4,92,100:13,-1:10" +
",100:5,-1:19,100:17,93,-1:10,100:5,-1:19,100,101,100:16,-1:10,100:5,-1:18");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -3:
						break;
					case 3:
						{ return this.newToken(DecafParserSymbols.LEFT_PARENTHESIS); }
					case -4:
						break;
					case 4:
						{ return this.newToken(DecafParserSymbols.RIGHT_PARENTHESIS); }
					case -5:
						break;
					case 5:
						{ return this.newToken(DecafParserSymbols.LEFT_BRACE); }
					case -6:
						break;
					case 6:
						{ return this.newToken(DecafParserSymbols.RIGHT_BRACE); }
					case -7:
						break;
					case 7:
						{ return this.newToken(DecafParserSymbols.LEFT_BRACKET); }
					case -8:
						break;
					case 8:
						{ return this.newToken(DecafParserSymbols.RIGHT_BRACKET); }
					case -9:
						break;
					case 9:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -10:
						break;
					case 10:
						{ return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext())); }
					case -11:
						break;
					case 11:
						{ return this.newToken(DecafParserSymbols.COMMA); }
					case -12:
						break;
					case 12:
						{ return this.newToken(DecafParserSymbols.SEMI_COLON); }
					case -13:
						break;
					case 13:
						{ return this.newToken(DecafParserSymbols.NOT); }
					case -14:
						break;
					case 14:
						{ return this.newToken(DecafParserSymbols.PLUS); }
					case -15:
						break;
					case 15:
						{ return this.newToken(DecafParserSymbols.MINUS); }
					case -16:
						break;
					case 16:
						{ return this.newToken(DecafParserSymbols.TIMES); }
					case -17:
						break;
					case 17:
						{ return this.newToken(DecafParserSymbols.MODULO); }
					case -18:
						break;
					case 18:
						{ return this.newToken(DecafParserSymbols.LESSER); }
					case -19:
						break;
					case 19:
						{ return this.newToken(DecafParserSymbols.GREATER); }
					case -20:
						break;
					case 20:
						{ this.updateLocation(); }
					case -21:
						break;
					case 21:
						{ /* Ignore */ }
					case -22:
						break;
					case 22:
						{ return this.newToken(DecafParserSymbols.IF); }
					case -23:
						break;
					case 23:
						{ return this.newToken(DecafParserSymbols.STRING_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }
					case -24:
						break;
					case 24:
						{ return this.newToken(DecafParserSymbols.NOT_EQUAL); }
					case -25:
						break;
					case 25:
						{ return this.newToken(DecafParserSymbols.ARITHMETIC_SHIFT_LEFT); }
					case -26:
						break;
					case 26:
						{ return this.newToken(DecafParserSymbols.LESSER_OR_EQUAL); }
					case -27:
						break;
					case 27:
						{ return this.newToken(DecafParserSymbols.ARITHMETIC_SHIFT_RIGHT); }
					case -28:
						break;
					case 28:
						{ return this.newToken(DecafParserSymbols.GREATER_OR_EQUAL); }
					case -29:
						break;
					case 29:
						{ return this.newToken(DecafParserSymbols.EQUAL); }
					case -30:
						break;
					case 30:
						{ return this.newToken(DecafParserSymbols.AND); }
					case -31:
						break;
					case 31:
						{ return this.newToken(DecafParserSymbols.OR); }
					case -32:
						break;
					case 32:
						{ this.updateLocation(); }
					case -33:
						break;
					case 33:
						{ return this.newToken(DecafParserSymbols.INT); }
					case -34:
						break;
					case 34:
						{ return this.newToken(DecafParserSymbols.CHAR_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }
					case -35:
						break;
					case 35:
						{ return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext().substring(2), 16)); }
					case -36:
						break;
					case 36:
						{ return this.newToken(DecafParserSymbols.BITWISE_ROTATE_RIGHT); }
					case -37:
						break;
					case 37:
						{ return this.newToken(DecafParserSymbols.ELSE); }
					case -38:
						break;
					case 38:
						{ return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, true); }
					case -39:
						break;
					case 39:
						{ return this.newToken(DecafParserSymbols.VOID); }
					case -40:
						break;
					case 40:
						{ return this.newToken(DecafParserSymbols.BREAK); }
					case -41:
						break;
					case 41:
						{ return this.newToken(DecafParserSymbols.CLASS); }
					case -42:
						break;
					case 42:
						{ return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, false); }
					case -43:
						break;
					case 43:
						{ return this.newToken(DecafParserSymbols.WHILE); }
					case -44:
						break;
					case 44:
						{ return this.newToken(DecafParserSymbols.RETURN); }
					case -45:
						break;
					case 45:
						{ return this.newToken(DecafParserSymbols.BOOLEAN); }
					case -46:
						break;
					case 46:
						{ return this.newToken(DecafParserSymbols.CALLOUT); }
					case -47:
						break;
					case 47:
						{ return this.newToken(DecafParserSymbols.CONTINUE); }
					case -48:
						break;
					case 49:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -49:
						break;
					case 50:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -50:
						break;
					case 51:
						{ return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext())); }
					case -51:
						break;
					case 53:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -52:
						break;
					case 54:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -53:
						break;
					case 56:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -54:
						break;
					case 57:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -55:
						break;
					case 59:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -56:
						break;
					case 60:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -57:
						break;
					case 62:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -58:
						break;
					case 63:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -59:
						break;
					case 64:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -60:
						break;
					case 65:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -61:
						break;
					case 66:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -62:
						break;
					case 67:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -63:
						break;
					case 68:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -64:
						break;
					case 69:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -65:
						break;
					case 70:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -66:
						break;
					case 71:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -67:
						break;
					case 72:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -68:
						break;
					case 73:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -69:
						break;
					case 74:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -70:
						break;
					case 75:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -71:
						break;
					case 76:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -72:
						break;
					case 77:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -73:
						break;
					case 78:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -74:
						break;
					case 79:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -75:
						break;
					case 80:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -76:
						break;
					case 81:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -77:
						break;
					case 82:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -78:
						break;
					case 83:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -79:
						break;
					case 84:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -80:
						break;
					case 85:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -81:
						break;
					case 86:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -82:
						break;
					case 87:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -83:
						break;
					case 88:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -84:
						break;
					case 89:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -85:
						break;
					case 90:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -86:
						break;
					case 91:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -87:
						break;
					case 92:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -88:
						break;
					case 93:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -89:
						break;
					case 94:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -90:
						break;
					case 95:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -91:
						break;
					case 96:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -92:
						break;
					case 97:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -93:
						break;
					case 98:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -94:
						break;
					case 99:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -95:
						break;
					case 100:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -96:
						break;
					case 101:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -97:
						break;
					case 102:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -98:
						break;
					case 103:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -99:
						break;
					case 104:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -100:
						break;
					case 105:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -101:
						break;
					case 106:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -102:
						break;
					case 107:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -103:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
