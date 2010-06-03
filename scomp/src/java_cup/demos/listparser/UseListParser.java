package java_cup.demos.listparser;

import java.io.ByteArrayInputStream;

/**
 * 
 * @author codistmonk (creation 2010-06-03)
 */
public class UseListParser {
	
	/**
	 * @param arguments
	 * <br>Unused
	 */
	public static final void main(final String[] arguments) {
		try {
			System.out.println("Parsing correct input 1");
			System.out.println(new ListParser(new Yylex(new ByteArrayInputStream("(a b c)".getBytes()))).parse().value);
			
			System.out.println("Parsing correct input 2");
			System.out.println(new ListParser(new Yylex(new ByteArrayInputStream("(a (() b))".getBytes()))).parse().value);
			
			System.out.println("Parsing incorrect input");
			System.out.flush();
			System.err.flush();
			System.out.println(new ListParser(new Yylex(new ByteArrayInputStream("(a))".getBytes()))).parse().value);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
