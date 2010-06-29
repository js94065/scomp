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
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
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
	 * <br>Range: any integer constant defined in {@link DecafParserSymbols}
	 * @param value
	 * <br>Can be null
	 * <br>Shared parameter
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public DecafToken(final int symbolId, final Object value, final int row, final int column) {
		super(symbolId, value);
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
	 * <br>A possibly null value
	 * <br>A shared value
	 */
	public final Object getValue() {
		return this.value;
	}
	
	@Override
	public boolean equals(final Object object) {
		final DecafToken that = Tools.cast(this.getClass(), object);
		
		return that != null && this.getSymbolId() == that.getSymbolId() && Tools.equals(this.getValue(), that.getValue());
	}
	
	@Override
	public final int hashCode() {
		return Tools.hashCode(this.getSymbolId()) + Tools.hashCode(this.getValue());
	}

	@Override
	public final String toString() {
		return super.toString() + ": (" + (this.getValue() == null ? "" : this.getValue().getClass()) + ") " + this.getValue();
	}
	
}
