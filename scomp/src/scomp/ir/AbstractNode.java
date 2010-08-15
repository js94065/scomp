package scomp.ir;

import static scomp.Tools.*;
import scomp.DecafToken;

/**
 * This is the base class for all elements of the intermediate representation tree.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public abstract class AbstractNode {
	
	private final DecafToken token;
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractNode(final DecafToken token) {
		this.token = token;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final DecafToken getToken() {
		return this.token;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getTokenRow() {
		return this.token.getRow();
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getTokenColumn() {
		return this.token.getColumn();
	}
	
	@Override
	public final int hashCode() {
		return this.doHashCode();
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <br>Also prints a message on the standard output when the result is {@code false}.
	 * <br>The reason for this behavior is to make testing easier.
	 * <br>The actual comparison is performed by {@link #doEquals(Object)}.
	 */
	@Override
	public final boolean equals(Object other) {
		final boolean result = this.doEquals(other);
		
		if (!result) {
			debugPrint(
					"\nExpected:", this,
					"\nActual:  ", other);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param visitor
	 * <br>Not null
	 */
	public abstract void accept(Visitor visitor);
	
	/**
	 * Should do what is specified in {@link Object#hashCode()}.
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	protected abstract int doHashCode();
	
	/**
	 * Should do what is specified in {@link Object#equals(Object)}.
	 * 
	 * @param other The reference object with which to compare
	 * <br>Maybe null
	 * @return
	 * <br>Range: any boolean
	 */
	protected abstract boolean doEquals(final Object other);
	
}
