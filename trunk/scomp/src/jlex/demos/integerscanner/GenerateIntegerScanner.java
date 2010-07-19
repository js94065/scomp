package jlex.demos.integerscanner;

import static jlex.tools.JLexTools.getSourceDirectory;

import java.io.IOException;

import jlex.Main;

/**
 * This class contains a {@link #main(String[])} method used to generate "IntegerScanner.java".
 * <br>After running it, you should refresh the source tree of your IDE.
 * 
 * @author codistmonk (creation 2010-05-28)
 */
public class GenerateIntegerScanner {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			Main.main(new String[] { getSourceDirectory("src") + "IntegerScanner.lex" });
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
}
