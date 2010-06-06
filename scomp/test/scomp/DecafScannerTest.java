package scomp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java_cup.symbol;
import scomp.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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
}
