package scomp.ir;

import scomp.DecafToken;


/**
 * This class defines a Decaf break statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class BreakStatement extends AbstractStatement {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public BreakStatement(final DecafToken token) {
		super(token);
	}
	
	// This is used for Semantic rule 16.  Break Statements
	// should always be inside a loop.
	private AbstractStatement loop;
	
	public final void setLoop(AbstractStatement loop) {
		this.loop = loop;
	}
	
	public final AbstractStatement getLoop() {
		return this.loop;
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
