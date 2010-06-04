package scomp;

import java_cup.runtime.Symbol;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 */
public class DecafToken extends Symbol {
	
	private final int row;
	
	private final int column;
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public DecafToken(final int symbolId, final int row, final int column) {
		super(symbolId);
		this.row = row;
		this.column = column;
	}
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer
	 * @param object
	 * <br>Can be null
	 * <br>Shared parameter
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public DecafToken(final int symbolId, final Object object, final int row, final int column) {
		super(symbolId, object);
		this.row = row;
		this.column = column;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getRow() {
		return this.row;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getColumn() {
		return this.column;
	}
	
}
