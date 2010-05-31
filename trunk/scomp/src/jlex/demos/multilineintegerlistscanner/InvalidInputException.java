package jlex.demos.multilineintegerlistscanner;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 */
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
	
	/**
	 * 
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param string
	 * <br>Should not be null
	 */
	public InvalidInputException(final int row, final int column, final String string) {
		super("Invalid input at (" + row + ", " + column + "): " + string);
	}
	
}
