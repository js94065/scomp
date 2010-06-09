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
	public final void testProgram() throws Exception {
		parse("class Program {}");
	}
	
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
