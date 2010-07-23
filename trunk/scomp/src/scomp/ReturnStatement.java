package scomp;

/**
 * This class defines a Decaf return statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public class ReturnStatement extends AbstractStatement {
	
	private final AbstractExpression abstractExpression;
	
	/**
	 * 
	 * @param abstractExpression
	 * <br>Not null
	 * <br>Shared
	 */
	public ReturnStatement(final AbstractExpression abstractExpression) {
		this.abstractExpression = abstractExpression;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getExpression() {
		return this.abstractExpression;
	}
	
}
