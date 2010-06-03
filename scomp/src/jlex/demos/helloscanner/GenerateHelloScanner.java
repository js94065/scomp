package jlex.demos.helloscanner;

import java.io.IOException;

import jlex.Main;

/**
 * This class contains a {@link #main(String[])} method used to generate "HelloScanner.java".
 * <br>After running it, you should refresh the source tree of your IDE.
 * 
 * @author codistmonk (creation 2010-05-28)
 */
public class GenerateHelloScanner {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			Main.main(new String[] { "src/jlex/demos/helloscanner/HelloScanner.lex" });
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
}
