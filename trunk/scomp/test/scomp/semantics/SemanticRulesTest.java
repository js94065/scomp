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
				"(:5:9) Duplicate identifier y",
				"(:7:10) Duplicate identifier x",
				"(:9:7) Duplicate identifier x",
				"(:9:24) Duplicate identifier z",
				"(:10:11) Duplicate identifier a",
				"(:17:12) Duplicate identifier y",
				"(:17:15) Duplicate identifier main"
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
				"Operand of arithmetic and relational operations must have type int.",
				"Operand of arithmetic and relational operations must have type int.",
				"Operand of arithmetic and relational operations must have type int.",
				"Operand of arithmetic and relational operations must have type int.",
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
				"Operand of arithmetic and relational operations must have type int.",
				"Operand of arithmetic and relational operations must have type int.",
				"(:14:7) The method f has type void and cannot be used as an expression"
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
	public final void testRule11() {
		final Program program = parse(PROGRAM_WITH_IF_AND_WHILE_STATEMENTS);
		
		program.accept(new SemanticRules());
		
		/*
		assertEquals(Arrays.asList(
				), this.recorder.getMessages());
				*/
	}
	
	@Test
	public final void testRule12() {
		final Program program = parse(PROGRAM_WITH_MISMATCH_ARITHMETIC_OPERATOR);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"Operand of arithmetic and relational operations must have type int."
				), this.recorder.getMessages());
	}

	@Test
	public final void testRule13() {
		final Program program = parse(PROGRAM_WITH_MANY_EQUALITY_STATEMENTS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"Operand of equality operator must have same type, either int or boolean.",
				"Operand of equality operator must have same type, either int or boolean."
				), this.recorder.getMessages());
	}
	
	@Test
	public final void testRule14() {
		final Program program = parse(PROGRAM_WITH_CONDITIONAL_OPERATORS_AND_LOGICAL_NOT);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"Operand of conditional operations and logical not must have type boolean.",
				"Operand of conditional operations and logical not must have type boolean.",
				"Operand of conditional operations and logical not must have type boolean."
				), this.recorder.getMessages());
		
	}
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_DUPLICATE_IDENTIFIERS =
		"class Program {\n" +
		"\n" +
		"	boolean y;\n" +
		"\n" +
		"	int x, y;\n" +
		"\n" +
		"	boolean x[1];\n" +
		"\n" +
		"	void x(int z, boolean z, int a) {\n" +
		"		boolean a, b;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		boolean a, b;\n" +
		"\n" +
		"		{\n" +
		"			boolean y, main;\n" +
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
	 * */
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
		"}"
		;
	
	public static final String PROGRAM_WITH_IF_AND_WHILE_STATEMENTS = 
		"class Program {\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_MISMATCH_ARITHMETIC_OPERATOR=
		"class Program {\n" +
		"	boolean f() {\n" +
		"		int x;\n" +
		"		x = true + 2;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";

	public static final String PROGRAM_WITH_MANY_EQUALITY_STATEMENTS =
		"class Program {\n" +
		"	boolean f() {\n" +
		"		boolean x,y,z;\n" +
		"		x = 3 == 2;\n" +
		"		y = true == 2;\n" +
		"		z = 'c' == 2;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";
	
	public static final String PROGRAM_WITH_CONDITIONAL_OPERATORS_AND_LOGICAL_NOT = 
		"class Program {\n" +
		"	boolean f() {\n" +
		"		boolean x,y,z;\n" +
		"		x = 2 && true;\n" +
		"		y = 2 || true;\n" +
		"		z = !3;\n" +
		"	}\n" +
		"\n" +
		"	void main() {\n" +
		"		// Deliberatly left empty\n" +
		"	}\n" +
		"\n" +
		"}";

}
