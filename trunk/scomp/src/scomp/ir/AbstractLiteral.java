package scomp.ir;

/**
 * This is the base class for Decaf literals.
 * 
 * @author wilson (creation 2010-07-21)
 *
 */
public abstract class AbstractLiteral extends AbstractNode {
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public abstract Class<?> getType();
	
}
