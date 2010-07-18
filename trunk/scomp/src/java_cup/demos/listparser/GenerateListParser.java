package java_cup.demos.listparser;

import java.io.File;

/**
 * This class contains a {@link #main(String[])} method used to generate "Yylex.java", "ListParser.java" and "ListParserSymbols.java".
 * <br>After running it, you should refresh the source tree of your IDE.
 * 
 * @author codistmonk (creation 2010-06-03)
 */
public class GenerateListParser {

	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		final String directory = ("src." + GenerateListParser.class.getPackage().getName() + ".").replaceAll("\\.", File.separator);
		final String parserName = "ListParser";
		final String symbolsName = "ListParserSymbols";
		
        try {
        	System.out.println("Generating scanner");
        	
    		jlex.Main.main(new String[] { directory + "Yylex.lex" });
    		
    		System.out.println("Generating parser");
    		
    		java_cup.Main.main(new String[] { "-parser", parserName, "-symbols", symbolsName, directory + parserName + ".cup" });
    		
    		final File parser = new File(parserName + ".java");
    		final File symbols = new File(symbolsName + ".java");
    		
    		moveToDirectory(parser, directory);
    		moveToDirectory(symbols, directory);
    		
    		System.out.println("All done");
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param file
	 * <br>Not null
	 * <br>Input-output
	 * @param directoryPath
	 * <br>Not null
	 * @return {@code true} if the operation succeeds
	 */
	public static final boolean moveToDirectory(final File file, final String directoryPath) {
		final File destination = new File(directoryPath, file.getName());
		
		System.out.println("Moving " + file.getAbsolutePath() + " to " + destination.getAbsolutePath() + " ...");
		
		if (destination.exists() && !destination.delete()) {
			System.err.println("Warning: The destination file (" + destination.getAbsolutePath() + ") exists and could not be deleted");
		}
		
		final boolean result = file.renameTo(destination);
		
		if (result) {
			System.out.println("Success");
		} else {
			System.err.println("Failure");
		}
		
		return result;
	}

}
