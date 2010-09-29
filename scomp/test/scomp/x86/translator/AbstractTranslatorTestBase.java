package scomp.x86.translator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import scomp.Tools;
import scomp.semantics.SemanticRules;

/**
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslatorTestBase {
	
	@Test
	public final void testSimple() {
		this.test("simple");
	}
	
	@Test
	public final void testCallout() {
		this.test("callout");
	}
	
	@Test
	public final void testLocalVariable() {
		this.test("localvariable");
	}
	
	@Test
	public final void testGlobalVariableArray() {
		this.test("globalvariablearray");
	}
	
	@Test
	public final void testCall() {
		this.test("call");
	}
	
	@Test
	public final void testReturn() {
		this.test("return");
	}
	
	@Test
	public final void testUnaryMinus() {
		this.test("unaryminus");
	}
	
	@Test
	public final void testAddition() {
		this.test("addition");
	}
	
	@Test
	public final void testSubtraction() {
		this.test("subtraction");
	}
	
	@Test
	public final void testMultiplication() {
		this.test("multiplication");
	}
	
	@Test
	public final void testDivision() {
		this.test("division");
	}
	
	@Test
	public final void testModulo() {
		this.test("modulo");
	}
	
	@Test
	public final void testShiftLeft() {
		this.test("shiftleft");
	}
	
	@Test
	public final void testShiftRight() {
		this.test("shiftright");
	}
	
	@Test
	public final void testRotateRight() {
		this.test("rotateright");
	}
	
	@Test
	public final void testLess() {
		this.test("less");
	}
	
	@Test
	public final void testLessOrEqual() {
		this.test("lessorequal");
	}
	
	@Test
	public final void testGreater() {
		this.test("greater");
	}
	
	@Test
	public final void testGreaterOrEqual() {
		this.test("greaterorequal");
	}
	
	@Test
	public final void testIntEqual() {
		this.test("intequal");
	}
	
	@Test
	public final void testIntNotEqual() {
		this.test("intnotequal");
	}
	
	@Test
	public final void testNegation() {
		this.test("negation");
	}
	
	@Test
	public final void testAnd() {
		this.test("and");
	}
	
	@Test
	public final void testAndShortCircuit() {
		this.test("andshortcircuit");
	}
	
	@Test
	public final void testOr() {
		this.test("or");
	}
	
	@Test
	public final void testOrShortCircuit() {
		this.test("orshortcircuit");
	}
	
	@Test
	public final void testBooleanEqual() {
		this.test("booleanequal");
	}
	
	@Test
	public final void testBooleanNotEqual() {
		this.test("booleannotequal");
	}
	
	@Test
	public final void testArguments() {
		this.test("arguments");
	}
	
	@Test
	public final void testIfElse() {
		this.test("ifelse");
	}
	
	@Test
	public final void testWhile() {
		this.test("while");
	}
	
	@Test
	public final void testBreakContinue() {
		this.test("breakcontinue");
	}
	
	@Test
	public final void testRecursive() {
		this.test("recursive");
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
	private final void test(final String testName) {
		final AbstractTranslator translator = this.newTranslator();
		final scomp.ir.Program program = parseTestFile(testName);

		program.accept(new SemanticRules());
		program.accept(translator);
		
		assertEquals(getSimplifiedString(testName + "_" + this.getOSName()), translator.getProgram().toString());
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private final AbstractTranslator newTranslator() {
		return TranslatorFactory.newTranslator(this.getOSName());
	}
	
	/**
	 * 
	 * @param testName
	 * <br>Not null
	 * @return
	 * <br>Maybe null
	 */
	private static final scomp.ir.Program parseTestFile(final String testName) {
		return Tools.parseFile(getDecafTestFilePath(testName));
	}
	
	/**
	 * Returns a string containing the non-comment and non-empty lines
	 * of the x86 test file corresponding to {@code osSpecificTestName}.
	 * 
	 * @param osSpecificTestName
	 * <br>Not null
	 * @return
	 * <br>Not null
	 */
	private static final String getSimplifiedString(final String osSpecificTestName) {
		try {
			final StringBuilder result = new StringBuilder();
			final Scanner scanner = new Scanner(new File(getX86TestFilePath(osSpecificTestName)));
			
			while (scanner.hasNext()) {
				final String line = scanner.nextLine().trim();
				
				if (!line.isEmpty() && !line.startsWith("#")) {
					result.append(line + "\n");
				}
			}
			
			return result.toString();
		} catch (final FileNotFoundException exception) {
			throw Tools.unchecked(exception);
		}
	}
	
	/**
	 * 
	 * @param testName
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final String getDecafTestFilePath(final String testName) {
		return "test/scomp/data/decaf/" + testName + ".decaf";
	}
	
	/**
	 * 
	 * @param osSpecificTestName
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final String getX86TestFilePath(final String osSpecificTestName) {
		return "test/scomp/data/x86/" + osSpecificTestName + ".s";
	}
}
