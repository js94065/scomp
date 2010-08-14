package scomp;

import static org.junit.Assert.*;

import static scomp.Tools.*;

import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-06-09)
 */
public class DecafParserTest {
	
	private MessageRecorder recorder;
	
	@Before
	public final void beforeEachTest() {
		this.recorder = new MessageRecorder();
		
		setHandler(Logger.getLogger(DecafParser.class.getName()), this.recorder);
	}
	
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
	public final void testProgramWithARecursiveMethod1() throws Exception {
		parse(PROGRAM_WITH_A_RECURSIVE_METHOD_1);
	}
	
	@Test
	public final void testProgramWithARecursiveMethod2() throws Exception {
		parse(PROGRAM_WITH_A_RECURSIVE_METHOD_2);
	}
	
	@Test
	public final void testProgramWithAMethodWithALoop() throws Exception {
		parse(PROGRAM_WITH_A_METHOD_WITH_A_LOOP);
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
			assertEquals(null, parse(MALFORMED_PROGRAM_1));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:1:1) Parse error"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testMalformedProgram2() {
		try {
			assertEquals(null, parse(MALFORMED_PROGRAM_2));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:1:6) Parse error"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testMalformedProgram3() {
		try {
			assertEquals(null, parse(MALFORMED_PROGRAM_3));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:1:14) Parse error"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithUnmatchedBraces1() {
		beforeEachTest();
		try {
			assertEquals(null, parse(PROGRAM_WITH_UNMATCHED_BRACES_1));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:1:16) Missing \"}\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithUnmatchedBraces2() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_UNMATCHED_BRACES_2));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:1:15) Unexpected \"}\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithMismatchingParentheses1() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_MISMATCHING_PARENTHESES_1));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:4:16) Unexpected \";\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithMismatchingParentheses2() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_MISMATCHING_PARENTHESES_2));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:4:17) Unexpected \")\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithMismatchingParentheses3() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_MISMATCHING_PARENTHESES_3));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:4:12) Unexpected \";\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithMissingSemiColon() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_MISSING_SEMICOLON));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:5:3) Unexpected \"return\""
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testProgramWithChainedEqualities() {
		try {
			assertEquals(null, parse(PROGRAM_WITH_CHAINED_EQUALITIES));
		} catch (final Exception exception) {
			assertTrue(exception.getMessage(), exception.getMessage().startsWith("["));
		}
		
		assertEquals(Arrays.asList(
				"(:5:14) Unexpected \"==\""
		), this.recorder.getMessages());
	}
	
	/**
	 * {@value}.
	 */
	public static final String SMALLEST_PROGRAM =
		"class Program{}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_FIELD =
		"class Program {\n" +
		"\n" +
		"	int x;\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_METHOD =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_FIELD_AND_A_METHOD =
		"class Program {\n" +
		"\n" +
		"	int x[42];\n" +
		"\n" +
		"	boolean f(int y) {\n" +
		"		return y == 42;" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
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
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_METHOD_WITH_PARAMETERS =
		"class Program {\n" +
		"\n" +
		"	void f(int x, boolean y) {\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
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
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_RECURSIVE_METHOD_1 =
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
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_RECURSIVE_METHOD_2 =
		"class Program {\n" +
		"\n" +
		"	int factorial(int n) {\n" +
		"		if (n <= 0) {\n" +
		"			return 1;" +
		"		}\n" +
		"\n" +
		"		{\n" +
		"			int result;\n" +
		"\n" +
		"			result = n;\n" +
		"			result = result * factorial(n - 1);\n" +
		"\n" +
		"			return result;\n" +
		"		}\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_A_METHOD_WITH_A_LOOP =
		"class Program {\n" +
		"\n" +
		"	int factorial(int n) {\n" +
		"		int result, i;\n" +
		"\n" +
		"		result = 1;\n" +
		"		i = n;\n" +
		"\n" +
		"		while (i > 1) {\n" +
		"			result = result * i;\n" +
		"			i = i - 1;\n" +
		"\n" +
		"			if (!(-i != -1)) {\n" +
		"				break;\n" +
		"			} else {\n" +
		"				continue;\n" +
		"			}\n" +
		"		}\n" +
		"\n" +
		"		return result;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
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
	
	/**
	 * {@value}.
	 */
	public static final String MALFORMED_PROGRAM_1 =
		"";
	
	/**
	 * {@value}.
	 */
	public static final String MALFORMED_PROGRAM_2 =
		"class";
	
	/**
	 * {@value}.
	 */
	public static final String MALFORMED_PROGRAM_3 =
		"class Program";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_UNMATCHED_BRACES_1 =
		"class Program {";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_UNMATCHED_BRACES_2 =
		"class Program }";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISMATCHING_PARENTHESES_1 =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		return (1 + 2;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISMATCHING_PARENTHESES_2 =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		return (1 + 2));\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISMATCHING_PARENTHESES_3 =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		return f(;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISSING_SEMICOLON =
		"class Program {\n" +
		"\n" +
		"	int f() {\n" +
		"		int x\n" +
		"		return 42;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_CHAINED_EQUALITIES =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"		boolean x;\n" +
		"		x = 1 == 2 == 3;\n" +
		"	}\n" +
		"\n" +
		"}";
	
}
