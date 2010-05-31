package jlex.demos.multilineintegerlistscanner;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 */
public class Yytoken {
	
	private final int intValue;
	
	private final int row;
	
	private final int column;
	
	/**
	 * 
	 * @param row
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 * @param intValue
	 * <br>Range: any integer
	 */
	public Yytoken(final int row, final int column, final int intValue) {
		this.row = row;
		this.column = column;
		this.intValue = intValue;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	public final int getRow() {
		return this.row;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	public final int getColumn() {
		return this.column;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getIntValue() {
		return this.intValue;
	}
	
}
