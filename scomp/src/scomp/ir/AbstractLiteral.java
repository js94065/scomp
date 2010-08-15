package scomp.ir;

import scomp.DecafToken;

/**
 * This is the base class for Decaf literals.
 * 
 * @author wilson (creation 2010-07-21)
 *
 */
public abstract class AbstractLiteral extends AbstractNode {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractLiteral(final DecafToken token) {
		super(token);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public abstract Class<?> getType();
	
}
