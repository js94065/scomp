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
			System.out.println(parse("(a b c)"));
			
			System.out.println("Parsing correct input 2");
			System.out.println(parse("(a (() b))"));
			
			System.out.println("Parsing incorrect input");
			System.out.println(parse("(a))"));
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param string
	 * <br>Should not be null
	 * @return
	 * <br>A possibly null value
	 * @throws Exception if an error occurs
	 */
	public static final Object parse(final String string) throws Exception {
		System.out.flush();
		System.err.flush();
		
		return new ListParser(new Yylex(new ByteArrayInputStream(string.getBytes()))).parse().value;
	}
	
}
