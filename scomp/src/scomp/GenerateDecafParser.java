package scomp;

import java.io.File;

/**
 * This class contains a {@link #main(String[])} method used to generate "Yylex.java", "DecafParser.java" and "DecafParserSymbols.java".
 * <br>After running it, you should refresh the source tree of your IDE.
 *
 * @author codistmonk (creation 2010-06-03)
 */
public class GenerateDecafParser {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		final String directory = "src/" + GenerateDecafParser.class.getPackage().getName().replaceAll("\\.", "/") + "/";
		final String parserName = "DecafParser";
		final String symbolsName = "DecafParserSymbols";
		
        try {
        	System.out.println("Generating scanner");
        	
    		jlex.Main.main(new String[] { directory + "Yylex.lex" });
    		
    		System.out.println("Generating parser");
    		
    		java_cup.Main.main(new String[] { "-parser", parserName, "-symbols", symbolsName, directory + parserName + ".cup" });
    		
    		final File parser = new File(parserName + ".java");
    		final File symbols = new File(symbolsName + ".java");
    		
    		System.out.println("Moving " + parser + " into " + directory);
    		
    		parser.renameTo(new File(directory, parser.getName()));
    		
    		System.out.println("Moving " + symbols + " into " + directory);
    		
    		symbols.renameTo(new File(directory, symbols.getName()));
    		
    		System.out.println("All done");
		} catch (final Exception exception) {
        	System.out.flush();
        	System.err.flush();
        	
			exception.printStackTrace();
		}
	}
	
}
