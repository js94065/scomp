package scomp.ir;

import scomp.Tools;


/**
 * This class defines a Decaf break statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class BreakStatement extends AbstractStatement {
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + "{}";
	}
	
}