package scomp;

import java_cup.runtime.Symbol;

/**
 * 
 * @author codistmonk (creation 2010-05-31)
 */
public class DecafToken extends Symbol {
	
	private final int row;
	
	private final int column;
	
	private final String inputString;
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param inputString 
	 * <br>Not null
	 * <br>Shared
	 */
	public DecafToken(final int symbolId, final int row, final int column, final String inputString) {
		this(symbolId, null, row, column, inputString);
	}
	
	/**
	 * 
	 * @param symbolId
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 * @param value
	 * <br>Can be null
	 * <br>Shared parameter
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param inputString 
	 * <br>Not null
	 * <br>Shared
	 */
	public DecafToken(final int symbolId, final Object value, final int row, final int column, final String inputString) {
		super(symbolId, value);
		this.row = row;
		this.column = column;
		this.inputString = inputString;
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
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 */
	public final int getSymbolId() {
		return this.sym;
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final Object getValue() {
		return this.value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getInputString() {
		return this.inputString;
	}

	@Override
	public final boolean equals(final Object object) {
		final DecafToken that = Tools.cast(this.getClass(), object);
		
		return that != null && this.getSymbolId() == that.getSymbolId() && Tools.equals(this.getValue(), that.getValue());
	}
	
	@Override
	public final int hashCode() {
		return Tools.hashCode(this.getSymbolId()) + Tools.hashCode(this.getValue());
	}

	@Override
	public final String toString() {
		return "(:" + this.getRow() + ":" + this.getColumn() + ") " + super.toString() + " " + this.getInputString();
	}
	
}
