package jlex.demos.helloscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 
 * @author codistmonk (creation 2010-05-28)
 *
 */
public class UseHelloScanner {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			System.out.println("Scanning correct input");
			new Yylex(new ByteArrayInputStream("hello".getBytes())).yylex();
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		try {
			System.out.println("Scanning incorrect input");
			new Yylex(new ByteArrayInputStream("hell".getBytes())).yylex();
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
}
