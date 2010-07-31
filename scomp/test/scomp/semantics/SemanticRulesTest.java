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
				"(:17:15) Duplicate identifier f"
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
		"	void f() {\n" +
		"		boolean a, b;\n" +
		"\n" +
		"		{\n" +
		"			boolean y, f;\n" +
		"		}\n" +
		"	}\n" +
		"\n" +
		"}";
	
}