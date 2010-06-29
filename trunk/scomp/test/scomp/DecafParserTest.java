package scomp;

import static org.junit.Assert.*;
import java_cup.runtime.Symbol;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-06-09)
 *
 */
public class DecafParserTest {
	
	@Test
	public final void testSmallestProgram() throws Exception {
		parse(SMALLEST_PROGRAM);
	}
	
	@Test
	public final void testProgramWithAField() throws Exception {
		parse(PROGRAM_WITH_A_FIELD);
	}
	
	@Test
	public final void testProgramWithAMethod() throws Exception {
		parse(PROGRAM_WITH_A_METHOD);
	}
	
	@Test
	public final void testProgramWithFieldsAndMethods() throws Exception {
		parse(PROGRAM_WITH_FIELDS_AND_METHODS);
	}
	
	@Test
	public final void testProgramWithAFieldAndAMethod() throws Exception {
		parse(PROGRAM_WITH_A_FIELD_AND_A_METHOD);
	}
	
	@Test
	public final void testProgramWithMethodWithParameters() throws Exception {
		parse(PROGRAM_WITH_METHOD_WITH_PARAMETERS);
	}
	
	@Test
	public final void testProgramARecursiveMethod() throws Exception {
		parse(PROGRAM_WITH_A_RECURSIVE_METHOD);
	}
	
	@Test
	public final void testProgramWithMethodWithVariousExpressions() throws Exception {
		parse(PROGRAM_WITH_METHOD_WITH_VARIOUS_EXPRESSIONS);
	}
	
	@Test
	public final void testProgramWithMethodWithParametersAndComplexBlock() throws Exception {
		parse(PROGRAM_WITH_METHOD_WITH_PARAMETERS_AND_COMPLEX_BLOCK);
	}
	
	@Test
	public final void testMalformedProgram1() {
		try {
			parse(MALFORMED_PROGRAM_1);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Parse error on line 1", exception.getMessage());
		}
	}
	
	@Test
	public final void testMalformedProgram2() {
		try {
			parse(MALFORMED_PROGRAM_2);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Parse error on line 1", exception.getMessage());
		}
	}
	
	@Test
	public final void testMalformedProgram3() {
		try {
			parse(MALFORMED_PROGRAM_3);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Parse error on line 1", exception.getMessage());
		}
	}
	
	@Test
	public final void testProgramWithUnmatchedBraces1() {
		try {
			parse(PROGRAM_WITH_UNMATCHED_BRACES_1);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Missing '}' at (1, 16)", exception.getMessage());
		}
	}
	
	@Test
	public final void testProgramWithUnmatchedBraces2() {
		try {
			parse(PROGRAM_WITH_UNMATCHED_BRACES_2);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Unmatched '}' at (1, 15)", exception.getMessage());
		}
	}
	
	@Test
	public final void testProgramWithMismatchingParentheses1() {
		try {
			parse(PROGRAM_WITH_MISMATCHING_PARENTHESES_1);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Missing ')' at (4, 16)", exception.getMessage());
		}
	}
	
	@Test
	public final void testProgramWithMismatchingParentheses2() {
		try {
			parse(PROGRAM_WITH_MISMATCHING_PARENTHESES_2);
			
			fail("This line shouldn't be reached");
		} catch (final Exception exception) {
			assertEquals("Unmatched ')' at (4, 17)", exception.getMessage());
		}
	}
	
	public static final String SMALLEST_PROGRAM =
		"class Program{}";
	
	public static final String PROGRAM_WITH_A_FIELD =
		"class Program {\n" +
		"\n" +
		"	int x;\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_A_METHOD =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_A_FIELD_AND_A_METHOD =
		"class Program {\n" +
		"\n" +
		"	int x;\n" +
		"\n" +
		"	void f() {\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_FIELDS_AND_METHODS =
		"class Program {\n" +
		"\n" +
		"	int x, y[1], z;\n" +
		"	boolean a, b[2], c;\n" +
		"\n" +
		"	void f() {\n" +
		"	}\n" +
		"\n" +
		"	int g() {\n" +
		"		return 42;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_METHOD_WITH_PARAMETERS =
		"class Program {\n" +
		"\n" +
		"	void f(int x, boolean y) {\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_METHOD_WITH_VARIOUS_EXPRESSIONS =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"		// Declarations\n" +
		"		int a, b;\n" +
		"		boolean c, d;\n" +
		"		// Int expressions\n" +
		"		a = -42;\n\n" +
		"		b = 0x33;\n" +
		"		a = 'a';\n" +
		"		a = a + b;\n" +
		"		a = a - b;\n" +
		"		a = a * b;\n" +
		"		a = a / b;\n" +
		"		a = a % b;\n" +
		"		a = a << b;\n" +
		"		a = a >> b;\n" +
		"		a = a >>> b;\n" +
		"		// Boolean expressions\n" +
		"		c = true;\n" +
		"		d = false;\n" +
		"		c = !d;\n" +
		"		c = a < b;\n" +
		"		c = a > b;\n" +
		"		c = a <= b;\n" +
		"		c = a >= b;\n" +
		"		c = c == d;\n" +
		"		c = c != d;\n" +
		"		c = c && d;\n" +
		"		c = c || d;\n" +
		"		// Precedence\n" +
		"		a = a + b + a;\n" +
		"		a = a + b - a;\n" +
		"		a = a + b * a;\n" +
		"		a = a + b / a;\n" +
		"		a = a + b % a;\n" +
		"		a = a + b << a;\n" +
		"		a = a + b >> a;\n" +
		"		a = a + b >>> a;\n" +
		"		c = a < b == d;\n" +
		"		c = a > b != d;\n" +
		"		c = a <= b && d;\n" +
		"		c = a >= b || d;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_A_RECURSIVE_METHOD =
		"class Program {\n" +
		"\n" +
		"	int factorial(int n) {\n" +
		"		if (n <= 0) {\n" +
		"			return 1;" +
		"		}\n" +
		"\n" +
		"		return n * factorial(n - 1);\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_METHOD_WITH_PARAMETERS_AND_COMPLEX_BLOCK =
		"class Program {\n" +
		"\n" +
		"	boolean f(int a, boolean b) {\n" +
		"		int c, d;\n" +
		"		boolean e, f;\n" +
		"\n" +
		"		c = a;\n" +
		"		d = a + c;\n" +
		"		e = a < d;\n" +
		"\n" +
		"		if (e) {\n" +
		"			f = b && (((a / c) - d * d) % 42 == 0);\n" +
		"		}" +
		"		else {" +
		"			f = false;\n" +
		"		}\n" +
		"\n" +
		"		callout(\"printf\", \"Hello world!\\n\");\n" +
		"\n" +
		"		while (c != 0) {\n" +
		"			c = c / 2;\n" +
		"			if (c < 0) {\n" +
		"				break;\n" +
		"			}\n" +
		"			else {\n" +
		"				continue;\n" +
		"			}\n" +
		"		}\n" +
		"\n" +
		"		return f;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String MALFORMED_PROGRAM_1 =
		"";
	
	public static final String MALFORMED_PROGRAM_2 =
		"class";
	
	public static final String MALFORMED_PROGRAM_3 =
		"class Program";
	
	public static final String PROGRAM_WITH_UNMATCHED_BRACES_1 =
		"class Program {";
	
	public static final String PROGRAM_WITH_UNMATCHED_BRACES_2 =
		"class Program }";
	
	public static final String PROGRAM_WITH_MISMATCHING_PARENTHESES_1 =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		return (1 + 2;" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_MISMATCHING_PARENTHESES_2 =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		return (1 + 2));" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * 
	 * @param input
	 * <br>Should not be null
	 * @return
	 * <br>A possibly null value
	 * @throws Exception if an error occurs
	 */
	public static final Symbol parse(final String input) throws Exception {
		final DecafParser parser = new DecafParser(DecafScannerTest.createScanner(input));
		
		return parser.parse();
	}
	
}
