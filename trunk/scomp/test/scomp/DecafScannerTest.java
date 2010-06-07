package scomp;

import static org.junit.Assert.*;
import static scomp.DecafParserSymbols.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecafScannerTest {

	/**
	 * Tests CHAR_LITERAL
	 */
	@Test
	public void charLiteral() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("\'a\'".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "a");
			assertEquals(token.sym, 21);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test an incorrect CHAR_LITERAL.  
	 */
	@Test(expected=AssertionError.class)
	public void charLiteralIncorrect() throws AssertionError, IOException {
		// input a string
		Yylex lexer = new Yylex(new ByteArrayInputStream("\"a\"".getBytes()));
		// should throw an exception here
		DecafToken token = (DecafToken) lexer.next_token();
		assertEquals(token.sym, 21);
	}

	/**
	 * Tests STRING_LITERAL.
	 */
	@Test
	public void stringLiteral() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("\"test1\"".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "test1");
			assertEquals(token.sym, 22);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**`
	 * Test an incorrect STRING_LITERAL
	 */
	@Test(expected=AssertionError.class)
	public void stringLiteralIncorrect() throws AssertionError, IOException {
		// input an identifier
		Yylex lexer = new Yylex(new ByteArrayInputStream("test1,".getBytes()));  
		// should throw an exception here
		DecafToken token = (DecafToken) lexer.next_token();
		assertEquals(token.sym, 22);
	}
	
	/**
	 * Test INT_LITERAL 
	 */
	@Test
	public void intLiteral() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("42".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, 42);
			assertEquals(token.sym, 19);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test IDENTIFIER
	 */
	@Test
	public void identifier() {
		try {
			Yylex lexer = new Yylex(new ByteArrayInputStream("something".getBytes()));
			DecafToken token = (DecafToken) lexer.next_token();
			assertEquals(token.value, "something");
			assertEquals(token.sym, 23);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test InvalidInputException is thrown for invalid input.  
	 * For this test, we use a modified CHAR_LITERAL.
	 */
	@Test(expected=InvalidInputException.class)
	public void invalidInputChar() throws InvalidInputException, IOException {
		Yylex lexer = new Yylex(new ByteArrayInputStream("\'ab\'".getBytes()));
		// should throw an exception here
		DecafToken token = (DecafToken) lexer.next_token();
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
	 * @param input
	 * <br>Should not be null
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	private static final Yylex createScanner(final String input) {
		return new Yylex(new ByteArrayInputStream(input.getBytes()));
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
	
	private static final int UNSPECIFIED_ROW = Integer.MAX_VALUE;
	
	private static final int UNSPECIFIED_COLUMN = Integer.MAX_VALUE;
	
}
