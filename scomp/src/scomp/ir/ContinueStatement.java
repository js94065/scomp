package scomp.ir;

import scomp.Tools;


/**
 * This class defines a Decaf continue statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class ContinueStatement extends AbstractStatement {
	
	@Override
	public final void accept(final Visitor visitor) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + "{}";
	}
	
}
