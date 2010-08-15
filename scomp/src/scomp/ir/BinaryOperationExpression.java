package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf binary operation expression.
 * 
 * @author codistmonk (creation 2010-07-23)
 *
 */
public final class BinaryOperationExpression extends AbstractExpression {
	
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
	public BinaryOperationExpression(final AbstractExpression left,
			final String operator, final AbstractExpression right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
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
	
	@Override
	public final Class<?> getType() {
		
		if(this.operator.equals("<") ||
				this.operator.equals(">") ||
				this.operator.equals("<=") ||
				this.operator.equals(">=") ||
				this.operator.equals("==") ||
				this.operator.equals("!=") ||
				this.operator.equals("&&") ||
				this.operator.equals("||")) {
			return boolean.class;
		}
		
		return int.class;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"left{" + this.getLeft() + "} " +
				"operator{" + this.getOperator() + "} " +
				"right{" + this.getRight() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getLeft().hashCode() + this.getOperator().hashCode() + this.getRight().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final BinaryOperationExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getLeft().equals(that.getLeft()) &&
				this.getOperator().equals(that.getOperator()) &&
				this.getRight().equals(that.getRight());
	}

}
