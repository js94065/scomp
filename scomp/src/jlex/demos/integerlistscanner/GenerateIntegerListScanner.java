package jlex.demos.integerlistscanner;

import static jlex.tools.JLexTools.getSourceDirectory;

import java.io.IOException;

import jlex.Main;

/**
 * This class contains a {@link #main(String[])} method used to generate "IntegerListScanner.java".
 * <br>After running it, you should refresh the source tree of your IDE.
 * 
 * @author js94065 (creation 2010-05-28)
 */
public class GenerateIntegerListScanner {
	
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
        try {
            Main.main(new String[] { getSourceDirectory("src") + "IntegerListScanner.lex" });
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }
    
}
