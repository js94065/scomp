package scomp;

/**
 * This class defines a Decaf return statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public class ReturnStatement extends AbstractStatement {
	
	private final Expression expression;
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public ReturnStatement(final Expression expression) {
		this.expression = expression;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Expression getExpression() {
		return this.expression;
	}
	
}
