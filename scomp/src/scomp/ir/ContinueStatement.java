package scomp.ir;

import scomp.DecafToken;


/**
 * This class defines a Decaf continue statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class ContinueStatement extends AbstractStatement {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public ContinueStatement(final DecafToken token) {
		super(token);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + "{}";
	}
	
}
