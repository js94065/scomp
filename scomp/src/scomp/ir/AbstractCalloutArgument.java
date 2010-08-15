package scomp.ir;

import scomp.DecafToken;

/**
 * This class defines Decaf CalloutArgument.
 * 
 * @author Wilson (creation 2010-07-19)
 * 
 */
public abstract class AbstractCalloutArgument extends AbstractNode {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractCalloutArgument(final DecafToken token) {
		super(token);
	}
	
}
