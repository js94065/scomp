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
		/* 31 */ YY_NOT_ACCEPT,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
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
		/* 94 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"42:9,43,45,42,46,45,42:18,44,34,28,26:2,35,39,25,19,20,35:2,26,35,29,41,31," +
"30:9,26:2,36,38,37,26:2,33:6,29:20,23,27,24,26,29,26,5,1,9,16,4,14,29,18,13" +
",29,8,3,29,6,2,29:2,7,12,11,10,15,17,32,29:2,21,40,22,26,42,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,95,
"0,1,2,1:6,3,4,5,6,7,8,1,9,8,1,10,8:11,11,12,1,13,1,14,15,16,17,16,18,5,19,2" +
"0,21,22,23,10,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,4" +
"4,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,8,62,63,64,65,66,67,68")[0];

	private int yy_nxt[][] = unpackFromString(69,47,
"1,2,87:2,71,87:2,89,87,91,87,72,87,32,92,73,87,93,87,3,4,5,6,7,8,9,33:2,38," +
"87,10,34,87:2,11,35,39,43,42,46,50,52,33,12:2,13:2,-1:48,87,94,87:4,74,87:1" +
"1,-1:10,87:5,-1:14,31:24,-1,31,36,-1,31:13,-1:2,31,-1:32,10:2,-1:53,35,-1:5" +
"1,12:2,-1:47,13:2,-1,87:18,-1:10,87:5,-1:14,16:44,-1,16,-1,19,-1:2,19:2,-1:" +
"3,19,-1:4,19,-1,19,-1:13,19:2,-1,19,-1:38,18,-1:22,87:5,37,87:7,14,87:4,-1:" +
"10,87:5,-1:43,10:2,48,-1:20,31,-1:4,31,-1:13,31,-1:2,31,-1:19,87:10,17,87:7" +
",-1:10,87:5,-1:14,40:24,-1,40,44,15,40:13,-1:2,40,-1:38,35,-1,35,-1:9,87:3," +
"20,87:14,-1:10,87:5,-1:50,47,35,-1:14,40,-1:4,40,-1:13,40,-1:2,40,-1:19,87:" +
"3,21,87:14,-1:10,87:5,-1:52,35,-1:44,35,-1:10,87:15,22,87:2,-1:10,87:5,-1:5" +
"3,35,-1:7,87:7,23,87:10,-1:10,87:5,-1:54,16,-1:6,87:11,24,87:6,-1:10,87:5,-" +
"1:14,87:3,25,87:14,-1:10,87:5,-1:14,87:3,26,87:14,-1:10,87:5,-1:14,87:5,27," +
"87:12,-1:10,87:5,-1:14,87:5,28,87:12,-1:10,87:5,-1:14,87:10,29,87:7,-1:10,8" +
"7:5,-1:14,87:3,30,87:14,-1:10,87:5,-1:14,87:11,41,87:6,-1:10,87:5,-1:14,87:" +
"9,45,87:8,-1:10,87:5,-1:14,87:12,49,87:5,-1:10,87:5,-1:14,87:4,51,87:13,-1:" +
"10,87:5,-1:14,87:11,53,87:6,-1:10,87:5,-1:14,87:11,54,87:6,-1:10,87:5,-1:14" +
",87:2,55,87:15,-1:10,87:5,-1:14,87:6,56,87:11,-1:10,87:5,-1:14,87:4,57,87:1" +
"3,-1:10,87:5,-1:14,87:9,58,87:8,-1:10,87:5,-1:14,87:9,59,87:8,-1:10,87:5,-1" +
":14,87:2,60,87:15,-1:10,87:5,-1:14,87:6,61,87:11,-1:10,87:5,-1:14,87,62,87:" +
"16,-1:10,87:5,-1:14,87:3,63,87:14,-1:10,87:5,-1:14,87:10,81,87:7,-1:10,87:5" +
",-1:14,87:5,82,87:12,-1:10,87:5,-1:14,87:4,64,87:13,-1:10,87:5,-1:14,87:2,9" +
"0,87:15,-1:10,87:5,-1:14,87:2,65,87:15,-1:10,87:5,-1:14,87:12,66,87:5,-1:10" +
",87:5,-1:14,87:9,67,87:8,-1:10,87:5,-1:14,87:10,84,87:7,-1:10,87:5,-1:14,87" +
":3,68,87:14,-1:10,87:5,-1:14,87:12,86,87:5,-1:10,87:5,-1:14,87,69,87:16,-1:" +
"10,87:5,-1:14,87:5,70,87:12,-1:10,87:5,-1:14,87:2,83,87:15,-1:10,87:5,-1:14" +
",87:3,75,87:14,-1:10,87:5,-1:14,87:2,85,87:15,-1:10,87:5,-1:14,87,76,77,87," +
"78,87:13,-1:10,87:5,-1:14,87:4,79,87:13,-1:10,87:5,-1:14,87:17,80,-1:10,87:" +
"5,-1:14,87,88,87:16,-1:10,87:5,-1:13");

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
						{ return this.newToken(DecafParserSymbols.OPERATOR, this.yytext()); }
					case -12:
						break;
					case 12:
						{ this.updateLocation(); }
					case -13:
						break;
					case 13:
						{ /* Ignore */ }
					case -14:
						break;
					case 14:
						{ return this.newToken(DecafParserSymbols.IF); }
					case -15:
						break;
					case 15:
						{ return this.newToken(DecafParserSymbols.STRING_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }
					case -16:
						break;
					case 16:
						{ this.updateLocation(); }
					case -17:
						break;
					case 17:
						{ return this.newToken(DecafParserSymbols.INT); }
					case -18:
						break;
					case 18:
						{ return this.newToken(DecafParserSymbols.CHAR_LITERAL, this.yytext().substring(1, this.yytext().length() - 1)); }
					case -19:
						break;
					case 19:
						{ return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext().substring(2), 16)); }
					case -20:
						break;
					case 20:
						{ return this.newToken(DecafParserSymbols.ELSE); }
					case -21:
						break;
					case 21:
						{ return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, true); }
					case -22:
						break;
					case 22:
						{ return this.newToken(DecafParserSymbols.VOID); }
					case -23:
						break;
					case 23:
						{ return this.newToken(DecafParserSymbols.BREAK); }
					case -24:
						break;
					case 24:
						{ return this.newToken(DecafParserSymbols.CLASS); }
					case -25:
						break;
					case 25:
						{ return this.newToken(DecafParserSymbols.BOOLEAN_LITERAL, false); }
					case -26:
						break;
					case 26:
						{ return this.newToken(DecafParserSymbols.WHILE); }
					case -27:
						break;
					case 27:
						{ return this.newToken(DecafParserSymbols.RETURN); }
					case -28:
						break;
					case 28:
						{ return this.newToken(DecafParserSymbols.BOOLEAN); }
					case -29:
						break;
					case 29:
						{ return this.newToken(DecafParserSymbols.CALLOUT); }
					case -30:
						break;
					case 30:
						{ return this.newToken(DecafParserSymbols.CONTINUE); }
					case -31:
						break;
					case 32:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -32:
						break;
					case 33:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -33:
						break;
					case 34:
						{ return this.newToken(DecafParserSymbols.INT_LITERAL, Integer.parseInt(this.yytext())); }
					case -34:
						break;
					case 35:
						{ return this.newToken(DecafParserSymbols.OPERATOR, this.yytext()); }
					case -35:
						break;
					case 37:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -36:
						break;
					case 38:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -37:
						break;
					case 39:
						{ return this.newToken(DecafParserSymbols.OPERATOR, this.yytext()); }
					case -38:
						break;
					case 41:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -39:
						break;
					case 42:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -40:
						break;
					case 43:
						{ return this.newToken(DecafParserSymbols.OPERATOR, this.yytext()); }
					case -41:
						break;
					case 45:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -42:
						break;
					case 46:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -43:
						break;
					case 47:
						{ return this.newToken(DecafParserSymbols.OPERATOR, this.yytext()); }
					case -44:
						break;
					case 49:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -45:
						break;
					case 50:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -46:
						break;
					case 51:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -47:
						break;
					case 52:
						{ this.updateLocation(); throw new InvalidInputException(this.currentLine, this.currentColumn, this.yytext()); }
					case -48:
						break;
					case 53:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -49:
						break;
					case 54:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -50:
						break;
					case 55:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -51:
						break;
					case 56:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -52:
						break;
					case 57:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -53:
						break;
					case 58:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -54:
						break;
					case 59:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -55:
						break;
					case 60:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -56:
						break;
					case 61:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -57:
						break;
					case 62:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -58:
						break;
					case 63:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -59:
						break;
					case 64:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
					case -60:
						break;
					case 65:
						{ return this.newToken(DecafParserSymbols.IDENTIFIER, this.yytext()); }
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
