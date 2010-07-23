package scomp;

/**
 * This class defines a Decaf binary operation expression.
 * 
 * @author codistmonk (creation 2010-07-23)
 *
 */
public final class BinaryOperation extends AbstractExpression {
	
	private final AbstractExpression left;
	
	private final String operator;
	
	private final AbstractExpression right;
	
	/**
	 * 
	 * @param left
	 * <br>Not null
	 * <br>Shared
	 * @param operator
	 * <br>Not null
	 * <br>Shared
	 * @param right
	 * <br>Not null
	 * <br>Shared
	 */
	public BinaryOperation(final AbstractExpression left,
			final String operator, final AbstractExpression right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getLeft() {
		return this.left;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getOperator() {
		return this.operator;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getRight() {
		return this.right;
	}
	
}
