package jlex.demos.multilineintegerlistscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 */
public class UseMultilineIntegerListScanner {
	
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
    	System.out.println("Scanning correct input");
        scan(
				"1 2\n" +
				"// This is a comment\n" +
				" 3 4 5"
        );
        
        System.out.println("Scanning incorrect input");
        scan(
				"1 2\n" +
				"// This is a comment\n" +
				" 3 error 5"
        );
    }
    
    /**
     * 
     * @param string
     * <br>Should not be null
     */
	private static final void scan(final String string) {
		try {
			final Yylex lexer =  new Yylex(new ByteArrayInputStream(string.getBytes()));
			final List<Integer> list = new ArrayList<Integer>();
			
			Yytoken token = lexer.yylex();
			
			while (token != null) {
				System.out.println("Integer detected at " + token.getRow() + " " + token.getColumn());
				list.add(token.getIntValue());
				token = lexer.yylex();
			}
			
			System.out.println(list);
		} catch (final IOException exception) {
			System.out.flush();
			exception.printStackTrace();
		} catch (final InvalidInputException exception) {
			System.out.flush();
			System.err.println(exception.getMessage());
		}
	}
	
}
