package jlex.demos.integerlistscanner;

import java.io.IOException;

import jlex.Main;

/**
 * 
 * @author js94065 (creation 2010-05-28)
 *
 */

public class GenerateIntegerListScanner {
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
        try {
            Main.main(new String[] { "src/jlex/demos/integerlistscanner/IntegerListScanner.lex" });
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }
    
}
