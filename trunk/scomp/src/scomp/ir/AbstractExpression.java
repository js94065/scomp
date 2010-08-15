package scomp.ir;

import scomp.DecafToken;

/**
 * This is the base class for all Decaf expressions.
 * 
 * @author js94065 (creation 2010-07-17)
 * 
 */
public abstract class AbstractExpression extends AbstractNode {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractExpression(final DecafToken token) {
		super(token);
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 */
	public abstract Class<?> getType();
	
}
