package scomp.ir;

import scomp.Tools;

/**
 * 
 * @author codistmonk (creation 2010-07-26)
 *
 */
public final class MinusExpression extends AbstractExpression {
	
	private final AbstractExpression expression;
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public MinusExpression(final AbstractExpression expression) {
		this.expression = expression;
	}
	
	@Override
	public final boolean isLocation() {
		return this.getExpression().isLocation();
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
	public final AbstractExpression getExpression() {
		return this.expression;
	}
	
	@Override
	public final Class<?> getType() {
		return this.getExpression().getType();
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + "{" +
				"expression{" + this.getExpression() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getExpression().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final MinusExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getExpression().equals(that.getExpression());
	}

	@Override
	public boolean isMethodCall() {
		return false;
	}
	
}
