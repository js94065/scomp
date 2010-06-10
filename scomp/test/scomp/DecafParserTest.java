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
		// TODO change following method into function as soon as parser can handle it
		"	void g() {\n" +
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
