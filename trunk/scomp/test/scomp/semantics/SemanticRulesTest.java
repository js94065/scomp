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
		final Program program = parse(PROGRAM_WITH_DUPLICATE_FIELDS);
		
		program.accept(new SemanticRules());
		
		assertEquals(Arrays.asList(
				"(:4:9) Duplicate identifier y",
				"(:5:10) Duplicate identifier x"
		), this.recorder.getMessages());
	}
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_WITH_DUPLICATE_FIELDS =
		"class Program {\n" +
		"\n" +
		"	boolean y;\n" +
		"	int x, y;\n" +
		"	boolean x[1];\n" +
		"\n" +
		"}";
	
}
