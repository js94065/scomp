package jlex.demos.multilineintegerlistscanner;

import java.io.IOException;

import jlex.Main;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 *
 */
public class GenerateMultilineIntegerListScanner {
	
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
        try {
    		Main.main(new String[] { "src/jlex/demos/multilineintegerlistscanner/MultilineIntegerListScanner.lex" });
        } catch (final IOException exception) {
    		exception.printStackTrace();
        }
    }
    
}
