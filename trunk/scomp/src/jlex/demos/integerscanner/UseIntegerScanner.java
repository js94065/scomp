package jlex.demos.integerscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 
 * @author codistmonk (creation 2010-05-28)
 *
 */
public class UseIntegerScanner {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			System.out.println("Scanning correct input");
			new Yylex(new ByteArrayInputStream("42".getBytes())).yylex();
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		try {
			System.out.println("Scanning arbitrary input");
			new Yylex(new ByteArrayInputStream("hell".getBytes())).yylex();
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
}
