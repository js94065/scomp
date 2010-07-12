package scomp;

import static org.junit.Assert.*;
import static scomp.DecafParserTest.*;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public class DecafSemanticsTest {
	
	@Test
	public final void testProgram() throws Exception {
		final Program program = parse(SMALLEST_PROGRAM);
		
		assertNotNull(program);
	}
	
}
