package java_cup.demos.listparser;

import java.io.File;

/**
 * 
 * @author codistmonk (creation 2010-06-03)
 */
public class GenerateListParser {

	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		final String directory = "src/" + GenerateListParser.class.getPackage().getName().replaceAll("\\.", "/") + "/";
		final String parserName = "ListParser";
		final String symbolsName = "ListParserSymbols";
		
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
			exception.printStackTrace();
		}
	}

}
