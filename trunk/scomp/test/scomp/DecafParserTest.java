package scomp;

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
	public final void testProgramWithAFieldAndAMethod() throws Exception {
		parse(PROGRAM_WITH_A_FIELD_AND_A_METHOD);
	}
	
	@Test
	public final void testProgramWithMethodWithParameters() throws Exception {
		parse(PROGRAM_WITH_METHOD_WITH_PARAMETERS);
	}
	
	@Test
	public final void testProgramWithMethodWithParametersAndComplexBlock() throws Exception {
		parse(PROGRAM_WITH_METHOD_WITH_PARAMETERS_AND_COMPLEX_BLOCK);
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
		"	int x, y;\n" +
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
	
	public static final String PROGRAM_WITH_METHOD_WITH_PARAMETERS_AND_COMPLEX_BLOCK =
		"class Program {\n" +
		"\n" +
		"	boolean f(int a, boolean b) {\n" +
		"		int c, d;\n" +
		"		boolean e, f;\n" +
		"\n" +
		"		c = a;\n" +
		"		d = a + c;\n" +
//		"		e = a < d;\n" +
//		"\n" +
//		"		if (e) {\n" +
//		"			f = b && (((a / c) - d * d) % 42 == 0);\n" +
//		"		}" +
//		"		else {" +
//		"			f = false;\n" +
//		"		}\n" +
//		"\n" +
		"		return f;\n" +
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
