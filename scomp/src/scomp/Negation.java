package scomp;

/**
 * 
 * @author codistmonk (creation 2010-07-26)
 *
 */
public final class Negation extends AbstractExpression {
	
	private final AbstractExpression expression;
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public Negation(final AbstractExpression expression) {
		this.expression = expression;
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
		final Negation that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getExpression().equals(that.getExpression());
	}
	
}
