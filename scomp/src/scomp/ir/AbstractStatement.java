package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf Statement.
 * 
 * @author js94065 (creation 2010-07-16)
 *
 */
public abstract class AbstractStatement extends AbstractNode {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractStatement(final DecafToken token) {
		super(token);
	}
	
	@Override
	protected int doHashCode() {
		return this.getClass().hashCode();
	}
	
	@Override
	protected boolean doEquals(final Object other) {
		final AbstractStatement that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null;
	}
	
}
