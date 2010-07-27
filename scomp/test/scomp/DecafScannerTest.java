package scomp;

import static scomp.DecafParserSymbols.*;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author js94065 (creation 2010-06-06)
 * @author codistmonk (modifications since 2010-06-06)
 *
 */
public class DecafScannerTest {
	
	@Test
	public final void testCharLiteral() throws IOException {
		match("\'a\'", token(CHAR_LITERAL, 'a'));
	}
	
	@Test
	public final void testStringLiteral() throws IOException {
		match("\"test1\"", token(STRING_LITERAL, "test1"));
	}
	
	@Test
	public final void testIdentifier() throws IOException {
		match("something", token(IDENTIFIER, "something"));
	}
	
	@Test
	public final void testCombination() throws IOException {
		match("0xxx42",
				token(INT_LITERAL, "0"),
				token(IDENTIFIER, "xxx42")
		);
	}
	
	/**
	 * Test InvalidInputException is thrown for invalid input.  
	 * <br>For this test, we use a modified CHAR_LITERAL.
	 * 
	 * @throws InvalidInputException if some input cannot be converted into a token
	 * @throws IOException if a reading error occurs
	 */
	@Test(expected=InvalidInputException.class)
	public final void testInvalidInputChar() throws InvalidInputException, IOException {
		createScanner("\'ab\'").next_token();
	}
	
	@Test
	public final void testBraceTypes() throws IOException {
		match("( ) { } [ ]", 
				token(LEFT_PARENTHESIS),
				token(RIGHT_PARENTHESIS),
				token(LEFT_BRACE),
				token(RIGHT_BRACE),
				token(LEFT_BRACKET),
				token(RIGHT_BRACKET)
				);
	}
	

	@Test
	public final void testOperators() throws IOException {
		match(", ; ! + - * % << >> >>> < > <= >= == != && || =", 
				token(COMMA),
				token(SEMICOLON),
				token(NOT),
				token(PLUS),
				token(MINUS),
				token(TIMES),
				token(MODULO),
				token(ARITHMETIC_SHIFT_LEFT),
				token(ARITHMETIC_SHIFT_RIGHT),
				token(BITWISE_ROTATE_RIGHT),
				token(LESS),
				token(GREATER),
				token(LESS_OR_EQUAL),
				token(GREATER_OR_EQUAL),
				token(EQUAL),
				token(NOT_EQUAL),
				token(AND),
				token(OR),
				token(ASSIGN)
				);
	}
	
	@Test
	public final void testIntLiterals() throws IOException {
		match("42 0x42",
				token(INT_LITERAL, "42"),
				token(INT_LITERAL, "0x42")
				);
	}
	
	@Test
	public final void testKeywords() throws IOException {
		match("boolean break callout class continue else false if int return true void while",
				token(BOOLEAN),
				token(BREAK),
				token(CALLOUT),
				token(CLASS),
				token(CONTINUE),
				token(ELSE),
				token(BOOLEAN_LITERAL, false),
				token(IF),
				token(INT),
				token(RETURN),
				token(BOOLEAN_LITERAL, true),
				token(VOID),
				token(WHILE)
		);
	}
	
	@Test
	public final void testSmallestProgram() throws IOException {
		match(DecafParserTest.SMALLEST_PROGRAM,
				token(CLASS),
				token(IDENTIFIER, "Program"),
				token(LEFT_BRACE),
				token(RIGHT_BRACE)
		);
	}
	
	@Test
	public final void testEscapedLiteralCharacters() throws IOException {
		match("'\\\'' '\\\"' '\\\\' '\\t' '\\n'",
				token(CHAR_LITERAL, '\''),
				token(CHAR_LITERAL, '\"'),
				token(CHAR_LITERAL, '\\'),
				token(CHAR_LITERAL, '\t'),
				token(CHAR_LITERAL, '\n'));
	}
	
	@Test(expected=InvalidInputException.class)
	public final void testInvalidEscapedLiteralCharacters() throws IOException {
		scan("'\\a'");
	}
	
	@Test
	public final void testEscapedCharactersInStringLiteral() throws IOException {
		match("\"\\\'\\\"\\\\\\t\\n\"",
				token(STRING_LITERAL, "\'\"\\\t\n"));
	}
	
	@Test(expected=InvalidInputException.class)
	public final void testInvalidEscapedCharactersInStringLiteral() throws IOException {
		scan("\"\\a\"");
	}
	
	private static final int UNSPECIFIED_ROW = Integer.MAX_VALUE;
	
	private static final int UNSPECIFIED_COLUMN = Integer.MAX_VALUE;
	
	/**
	 * Decomposes {@code input} using {@link #scan(String)} and matches the result against {@code expectedTokens} using {@link Assert#assertArrayEquals(Object[], Object[])}.
	 * 
	 * @param input
	 * <br>Should not be null
	 * @param expectedTokens
	 * <br>Should not be null
	 * @throws IOException if an error occurs while reading the input
	 * @throws AssertionError if {@code input} doesn't decompose into {@code expectedTokens}
	 */
	private static final void match(final String input, final DecafToken... expectedTokens) throws IOException {
		assertArrayEquals(expectedTokens, scan(input).toArray());
	}
	
	/**
	 * Decomposes {@code input} into a list of {@link DecafToken} using an instance of {@link Yylex}.
	 * 
	 * @param input
	 * <br>Should not be null
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 * @throws IOException if an error occurs while reading the input
	 */
	private static final List<DecafToken> scan(final String input) throws IOException {
		final Yylex scanner = createScanner(input);
		final List<DecafToken> result = new ArrayList<DecafToken>();
		DecafToken token = (DecafToken) scanner.next_token();
		
		while (token != null) {
			result.add(token);
			token = (DecafToken) scanner.next_token();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 * @param value
	 * <br>Can be null
	 * <br>Shared parameter
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	private static final DecafToken token(final int symbolId, final Object value) {
		return new DecafToken(symbolId, value, UNSPECIFIED_ROW, UNSPECIFIED_COLUMN, value == null ? "" : value.toString());
	}
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	private static final DecafToken token(final int symbolId) {
		return token(symbolId, null);
	}
	
	/**
	 * 
	 * @param input
	 * <br>Should not be null
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	public static final Yylex createScanner(final String input) {
		return new Yylex(new ByteArrayInputStream(input.getBytes()));
	}
	
}
