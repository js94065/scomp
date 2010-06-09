package scomp;

import static org.junit.Assert.*;
import static scomp.DecafParserSymbols.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author js94065 (creation 2010-06-06)
 *
 */
public class DecafScannerTest {

	/**
	 * Tests CHAR_LITERAL
	 */
	@Test
	public void testCharLiteral() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("\'a\'".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "a");
			assertEquals(token.sym, CHAR_LITERAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests STRING_LITERAL.
	 */
	@Test
	public void testStringLiteral() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("\"test1\"".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "test1");
			assertEquals(token.sym, STRING_LITERAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test IDENTIFIER
	 */
	@Test
	public void testIdentifier() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("something".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "something");
			assertEquals(token.sym, IDENTIFIER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test InvalidInputException is thrown for invalid input.  
	 * For this test, we use a modified CHAR_LITERAL.
	 */
	@Test(expected=InvalidInputException.class)
	public void testInvalidInputChar() throws InvalidInputException, IOException {
		Yylex lexer = new Yylex(new ByteArrayInputStream("\'ab\'".getBytes()));
		// should throw an exception here
		DecafToken token = (DecafToken) lexer.next_token();
	}
	
	/**
	 * Tests for Brace types
	 */
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
	

	/**
	 * Tests for operators.
	 */
	@Test
	public final void testOperators() throws IOException {
		match(", ; ! + - * % << >> >>> < > <= >= == != && ||", 
				token(COMMA),
				token(SEMI_COLON),
				token(NOT),
				token(PLUS),
				token(MINUS),
				token(TIMES),
				token(MODULO),
				token(ARITHMETIC_SHIFT_LEFT),
				token(ARITHMETIC_SHIFT_RIGHT),
				token(BITWISE_ROTATE_RIGHT),
				token(LESSER),
				token(GREATER),
				token(LESSER_OR_EQUAL),
				token(GREATER_OR_EQUAL),
				token(EQUAL),
				token(NOT_EQUAL),
				token(AND),
				token(OR)
				);
	}
	
	@Test
	public final void testIntLiterals() throws IOException {
		match("42 0x42",
				token(INT_LITERAL, 42),
				token(INT_LITERAL, 0x42)
				);
	}
	
	/**
	 * Tests for keywords.
	 * @throws IOException
	 */
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
		match("class Program{}",
				token(CLASS),
				token(IDENTIFIER, "Program"),
				token(LEFT_BRACE),
				token(RIGHT_BRACE)
		);
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
		return new DecafToken(symbolId, value, UNSPECIFIED_ROW, UNSPECIFIED_COLUMN);
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
