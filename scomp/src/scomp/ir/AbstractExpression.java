package scomp.ir;

/**
 * This is the base class for all Decaf expressions.
 * 
 * @author js94065 (creation 2010-07-17)
 * 
 */
public abstract class AbstractExpression extends AbstractNode {
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 */
	public abstract Class<?> getType();
	
}
