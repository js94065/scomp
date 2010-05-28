package jlex.demos.helloscanner;

import java.io.IOException;

import jlex.Main;

/**
 * 
 * @author codistmonk (creation 2010-05-28)
 *
 */
public class GenerateHelloScanner {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			Main.main(new String[] { "src/jlex/demos/helloscanner/HelloScanner" });
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
}
