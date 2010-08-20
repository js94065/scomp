package scomp.semantics;

import static org.junit.Assert.*;

import static scomp.Tools.*;

import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import scomp.DecafParser;
import scomp.Tools.MessageRecorder;
import scomp.ir.Program;

/**
 * 
 * @author codistmonk (creation 2010-07-28)
 *
 */
public final class SemanticRulesTest {
	
	private MessageRecorder recorder;
	
	@Before
	public final void beforeEachTest() {
		this.recorder = new MessageRecorder();
		
		setHandler(Logger.getLogger(DecafParser.class.getName()), this.recorder);
	}
	
	@Test
	public final void testRule1() {
		final Program program = parse(PROGRAM_WITH_DUPLICATE_IDENTIFIERS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:3:13) Duplicate identifier Program",
				"(:5:9) Duplicate identifier y",
				"(:7:10) Duplicate identifier x",
				"(:9:7) Duplicate identifier x",
				"(:9:24) Duplicate identifier z",
				"(:10:11) Duplicate identifier a"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule2() {
		final Program program = parse(PROGRAM_WITH_UNDECLARED_IDENTIFIERS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:12:4) Undeclared identifier b",
				"(:12:6) Undeclared identifier d",
				"(:12:11) Undeclared identifier f",
				"(:13:4) Undeclared identifier main",
				"(:16:10) Undeclared identifier a"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule3() {
		final Program program = parse(PROGRAM_WITH_MISSING_MAIN);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:7:1) Missing method main(<no argument>)"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule4() {
		final Program program = parse(PROGRAM_WITH_INVALID_ARRAY_DECLARATIONS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:3:8) Array size must be greater than 0",
				"(:5:12) Array size must be greater than 0"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule5() {
		final Program program = parse(PROGRAM_WITH_INVALID_METHOD_CALLS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:14:3) The method f() is not applicable for the arguments (int)",
				"(:15:3) The method g(int) is not applicable for the arguments ()",
				"(:16:3) The method g(int) is not applicable for the arguments (boolean)"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule6() {
		final Program program = parse(PROGRAM_WITH_VOID_METHOD_CALL_USED_AS_EXPRESSION);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:14:7) The method f has type void and cannot be used as an expression",
				"(:14:7) Operand of arithmetic and relational operations must have type int."
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule7() {
		final Program program = parse(PROGRAM_WITH_VOID_METHOD_THAT_RETURNS_VALUE);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:9:7) The method main cannot have a return value"
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule8() {
		final Program program = parse(PROGRAM_WITH_METHODS_WITH_MISMATCH_RETURN_TYPES);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:9:10) The method w return type does not match the return value",
				"(:12:6) The method x return type does not match the return value"
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule9() {
		final Program program = parse(PROGRAM_WITH_METHOD_USED_AS_VARIABLE);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:10:11) The method y cannot be used as a variable",
				"(:10:11) Assignment location and expression have different types.",
				"(:11:7) Cannot used the reserved identifier Program as a variable",
				"(:12:7) The method y cannot be used as a variable", 
				"(:12:3) Assignment location and expression have different types."
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule10() {
		final Program program = parse(PROGRAM_WITH_IMPROPER_USE_OF_ARRAY_VARIABLE);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:9:3) The variable x is not an array type",
				"(:11:3) The array offset is not an int type"
		), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule11() {
		final Program program = parse(PROGRAM_WITH_IF_AND_WHILE_STATEMENTS);
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:7:3) If condition should have type boolean.",
				"(:13:3) If condition should have type boolean.",
				"(:16:3) While condition should have type boolean.",
				"(:22:3) While condition should have type boolean.",
				"(:24:7) Undeclared identifier u",
				"(:26:10) Undeclared identifier u"
				), this.recorder.getMessages());
				
	}
	
	@Test
	public final void testRule12() {
		final Program program = parse(PROGRAM_WITH_MISMATCH_ARITHMETIC_OPERATOR);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:9:7) Operand of arithmetic and relational operations must have type int.",
				"(:10:11) Operand of arithmetic and relational operations must have type int.",
				"(:11:7) Undeclared identifier u",
				"(:11:11) Undeclared identifier u",
				"(:12:7) Undeclared identifier u",
				"(:12:11) Operand of arithmetic and relational operations must have type int."
				), this.recorder.getMessages());
	}

	@Test
	public final void testRule13() {
		final Program program = parse(PROGRAM_WITH_MANY_EQUALITY_STATEMENTS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:6:7) Operand of equality operations must have same type, either int or boolean.",
				"(:7:7) Operand of equality operations must have same type, either int or boolean.",
				"(:8:7) Operand of equality operations must have same type, either int or boolean.",
				"(:9:7) Undeclared identifier u"
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule14() {
		final Program program = parse(PROGRAM_WITH_CONDITIONAL_OPERATORS_AND_NEGATION);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:6:7) Operand of conditional operations must have type boolean.",
				"(:7:7) Operand of conditional operations must have type boolean.",
				"(:8:7) Operand of negation operations must have type boolean.",
				"(:9:7) Operand of conditional operations must have type boolean.",
				"(:10:15) Operand of conditional operations must have type boolean.",
				"(:11:7) Operand of negation operations must have type boolean.",
				"(:12:7) Undeclared identifier u",
				"(:12:12) Undeclared identifier u",
				"(:13:8) Undeclared identifier u"
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule15() {
		final Program program = parse(TEST_RULE_15);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:12:3) Assignment location and expression have different types.",
				"(:13:3) Assignment location and expression have different types.",
				"(:14:7) Undeclared identifier u",
				"(:15:3) Undeclared identifier u"
				), this.recorder.getMessages());
				
	}
	
	@Test
	public final void testRule16() {
		final Program program = parse(TEST_RULE_16);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:9:3) Break statement is not contained within the body of a loop.",
				"(:14:3) Continue statement is not contained within the body of a loop."
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule17() {
		final Program program = parse(PROGRAM_WITH_UNARY_MINUS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:12:7) Unary minus expression is not an int type", 
				"(:13:7) Unary minus expression is not an int type"
				), this.recorder.getMessages());
				
	}
	
	@Test
	public final void testProgramWithNoError() {
		final Program program = parse(PROGRAM_WITH_NO_ERROR);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(), this.recorder.getMessages());
	}
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_DUPLICATE_IDENTIFIERS =
		"class Program {\n" +
		"\n" +
		"	boolean y, Program;\n" +
		"\n" +
		"	int x, y;\n" +
		"\n" +
		"	boolean x[1];\n" +
		"\n" +
		"	void x(int z, boolean z, int a) {\n" +
		"		boolean a, b, x, Program;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		boolean a, b;\n" +
		"\n" +
		"		{\n" +
		"			boolean y, main, a;\n" +
		"		}\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_UNDECLARED_IDENTIFIERS =
		"class Program {\n" +
		"\n" +
		"	boolean y, c[1];\n" +
		"\n" +
		"	int x(int z) {\n" +
		"		int w;\n" +
		"\n" +
		"		{\n" +
		"			int d;\n" +
		"		}\n" +
		"		{\n" +
		"			b[d] = f(y);\n" +
		"			main();\n" +
		"		}\n" +
		"\n" +
		"		return a + w + z;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISSING_MAIN =
		"class Program {\n" +
		"\n" +
		"	void main(int x) {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_INVALID_ARRAY_DECLARATIONS =
		"class Program {\n" +
		"\n" +
		"	int a[0], b[1];\n" +
		"\n" +
		"	boolean c[0], d[1];\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_INVALID_METHOD_CALLS =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"	void g(int x) {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		f();\n" +
		"		g(42);\n" +
		"		f(42);\n" +
		"		g();\n" +
		"		g(true);\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_VOID_METHOD_CALL_USED_AS_EXPRESSION =
		"class Program {\n" +
		"\n" +
		"	void f() {\n" +
		"		// Deliberately left empty\n" +
		"	}\n" +
		"\n" +
		"	int g() {\n" +
		"		return 42;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		int x;\n" +
		"\n" +
		"		x = f() + g();\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_VOID_METHOD_THAT_RETURNS_VALUE =
		"class Program {\n" +
		"\n" +
		"	boolean x;\n" +
		"\n" +
		"	void y() {\n" +
		"		return ;\n" +
		"	}\n"+
		"\n" +
		"	void main() {\n" +
		"		x = true;\n" +
		"		return x;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}
	 */
	public static final String PROGRAM_WITH_METHODS_WITH_MISMATCH_RETURN_TYPES =
		"class Program {\n" +
		"\n" +
		"	boolean y() {\n" +
		"		return 1 < 2 * 1;" +
		"	}\n" +
		"\n" +
		"	int z() {\n" +
		"		return 1;" +
		"	}\n" +
		"\n" +
		"	boolean w() {\n" +
		"		return 2;" +
		"	}\n" +
		"\n" +
		"	int x() {\n" +
		"		return false;" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_IF_AND_WHILE_STATEMENTS = 
		"class Program {\n" +
		"\n" +
		"	void main() {\n" +
		"		int x;\n" +
		"		boolean y;\n" +
		"		y = false;\n" +
		"		if (x) {\n" +
		"		}\n" +
		"		if (y) {\n" +
		"		}\n" +
		"		if (4 < 3) {\n" +
		"		}\n" +
		"		if (3) {\n" +
		"		}\n" +
		"		\n" +
		"		while (x) {\n" +
		"		}\n" +
		"		while (y) {\n" +
		"		}\n" +
		"		while (4<3) {\n" +
		"		}\n" +
		"		while (4) {\n" +
		"		}\n" +
		"		if (u) {\n" + // rule 2 compatibility check
		"		}\n" +
		"		while (u) {\n" +
		"		}\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MISMATCH_ARITHMETIC_OPERATOR=
		"class Program {\n" +
		"\n" +
		"	boolean f() {\n" +
		"		int x,y;\n" +
		"		boolean z;\n" +
		"		y = 4;\n" +
		"		z = true;\n" +
		"		x = y + 2;\n" +
		"		x = z + 2;\n" +
		"		x = 2 + z;\n" +
		"		x = u + u;\n" + // rule 2 compatibility check
		"		x = u + true;\n" + 
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_MANY_EQUALITY_STATEMENTS =
		"class Program {\n" +
		"\n" +
		"	boolean f() {\n" +
		"		boolean x,y,z;\n" +
		"		x = 3 == 2;\n" +
		"		y = true == 2;\n" +
		"		z = x != 2;\n" +
		"		z = 2 != x;\n" +
		"		z = u == 2;\n" + // rule 2 compatibility check
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_CONDITIONAL_OPERATORS_AND_NEGATION = 
		"class Program {\n" +
		"\n" +
		"	boolean f() {\n" +
		"		boolean x;\n" +
		"		int a;\n" +
		"		x = 2 && true;\n" +
		"		x = 2 || true;\n" +
		"		x = !3;\n" +
		"		x = a && true;\n" +
		"		x = true || a;\n" +
		"		x = !a;\n" +
		"		x = u && u;\n" + // rule 2 compatibility check
		"		x = !u;\n" + 
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String TEST_RULE_15 = 
		"class Program {\n" +
		"\n" +
		"	boolean f() {\n" +
		"		boolean x,y;\n" +
		"		int a,b;\n" +
		"		x = true;\n" +
		"		y = false;\n" +
		"		a = 2;\n" +
		"		b = 3;\n" +
		"		x = y;\n" +
		"		a = b;\n" +
		"		x = 2;\n" +
		"		x = a;\n" +
		"		x = u;\n" + // rule 2 compatibility check
		"		u = a;\n" + 
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String TEST_RULE_16 = 
		"class Program {\n" +
		"\n" +
		"	boolean f() {\n" +
		"		int a;\n" +
		"		a=0;\n" +
		"		while (a<3) {\n" +
		"			break;\n" +
		"		}\n" +
		"		break;\n" +
		"		while (a<3) {\n" +
		"			a=a+1;\n" +
		"			continue;\n" +
		"		}\n" +
		"		continue;\n" +
		"		while (true) {\n" +
		"			while (true) {\n" +
		"				while (true) {\n " +
		"					break;\n" +
		"				}\n" +
		"				break;\n" +
		"			}\n" +
		"			break;\n" +
		"		}\n" +
		"		while (false) {\n" +
		"			if (false) {\n" +
		"				while (false) {\n" +
		"					continue;\n" +
		"				}\n" +
		"			}\n" +
		"		}\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_METHOD_USED_AS_VARIABLE =
		"class Program {\n" +
		"\n" +
		"	int v;\n" +
		"	boolean x;\n" +
		"\n" +
		"	void y() {\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		v = 1;" +
		"		y = 1;\n" +
		"		x = Program;\n" +
		"		v = y;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_IMPROPER_USE_OF_ARRAY_VARIABLE = 
		"class Program {\n" +
		"	int a[2];\n" +
		"	int x;\n" +
		"\n" +
		"	int y() {\n" +
		"		return 1;" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		x[0] = 2;\n" +
		"		a[0 + 1] = 1;\n" +
		"		a[true] = 0;\n" +
		"		a[y()] = 3;\n" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_UNARY_MINUS = 
		"class Program {\n" +
		"	int x, y;\n" +
		"	boolean z;\n" +
		"\n" +
		"	int w() {\n" +
		"		return 1;" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		x = -2;\n" +
		"		y = -x;\n" +
		"		x = -w();\n" +
		"		x = -z;\n" +
		"		y = -true;" +
		"	}\n" +
		"\n" +
		"}";
	
	/**
	 * {@value}.
	 * <br>This test case was designed to make some of the checks fail
	 * after reading their implementation in r315.
	 */
	public static final String PROGRAM_WITH_NO_ERROR =
		"class Program {\n" +
		"\n" +
		"	void main() {\n" +
		"		int main;\n" +
		"\n" +
		"		main = 0;\n" +
		"\n" +
		"		while (true) {\n" +
		"			if (true) {\n" +
		"				break;\n" +
		"			} else {\n" +
		"				continue;\n" +
		"			}\n" +
		"		}\n" +
		"\n" +
		"		return;" +
		"	}\n" +
		"\n" +
		"}";
	
}
