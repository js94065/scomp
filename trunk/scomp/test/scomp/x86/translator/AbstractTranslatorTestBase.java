package scomp.x86.translator;

import org.junit.Test;

import scomp.Tools;

/**
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslatorTestBase {
	
	@Test
	public final void testSimple() {
		test("simple");
	}
	
	@Test
	public final void testPrint42() {
		test("print42");
	}
	
	@Test
	public final void testPrintA() {
		test("printa");
	}
	
	@Test
	public final void testPrintAB() {
		test("printab");
	}
	
	@Test
	public final void testCall() {
		test("call");
	}
	
	@Test
	public final void testReturn() {
		test("return");
	}
	
	@Test
	public final void testUnaryMinus() {
		test("unaryminus");
	}
	
	@Test
	public final void testAddition() {
		test("addition");
	}
	
	@Test
	public final void testSubtraction() {
		test("subtraction");
	}
	
	@Test
	public final void testMultiplication() {
		test("multiplication");
	}
	
	@Test
	public final void testDivision() {
		test("division");
	}
	
	@Test
	public final void testModulo() {
		test("modulo");
	}
	
	@Test
	public final void testShiftLeft() {
		test("shiftleft");
	}
	
	@Test
	public final void testShiftRight() {
		test("shiftright");
	}
	
	@Test
	public final void testRotateRight() {
		test("rotateright");
	}
	
	@Test
	public final void testLess() {
		test("less");
	}
	
	@Test
	public final void testLessOrEqual() {
		test("lessorequal");
	}
	
	@Test
	public final void testGreater() {
		test("greater");
	}
	
	@Test
	public final void testGreaterOrEqual() {
		test("greaterorequal");
	}
	
	@Test
	public final void testIntEqual() {
		test("intequal");
	}
	
	@Test
	public final void testIntNotEqual() {
		test("intnotequal");
	}
	
	@Test
	public final void testNegation() {
		test("negation");
	}
	
	@Test
	public final void testAnd() {
		test("and");
	}
	
	@Test
	public final void testAndShortCircuit() {
		test("andshortcircuit");
	}
	
	@Test
	public final void testOr() {
		test("or");
	}
	
	@Test
	public final void testOrShortCircuit() {
		test("orshortcircuit");
	}
	
	@Test
	public final void testBooleanEqual() {
		test("booleanequal");
	}
	
	@Test
	public final void testBooleanNotEqual() {
		test("booleannotequal");
	}
	
	@Test
	public final void testArguments() {
		test("arguments");
	}
	
	@Test
	public final void testIfElse() {
		test("ifelse");
	}
	
	@Test
	public final void testWhile() {
		test("while");
	}
	
	@Test
	public final void testBreakContinue() {
		test("breakcontinue");
	}
	
	@Test
	public final void testRecursive() {
		test("recursive");
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	protected abstract String getOSName();
	
	/**
	 * 
	 * @param testName
	 * <br>Not null
	 */
	private static final void test(final String testName) {
		// TODO
		parseTestFile(testName);
	}
	
	/**
	 * 
	 * @param testName
	 * <br>Not null
	 * @return
	 * <br>Maybe null
	 */
	private static final scomp.ir.Program parseTestFile(final String testName) {
		return Tools.parseFile("test/scomp/data/decaf/" + testName + ".decaf");
	}
	
}
