package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * 
 * @author codistmonk (creation 2010-07-26)
 *
 */
public final class NegationExpression extends AbstractExpression {
	
	private final AbstractExpression expression;
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public NegationExpression(final DecafToken token, final AbstractExpression expression) {
		super(token);
		this.expression = expression;
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
		return boolean.class;
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
		final NegationExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getExpression().equals(that.getExpression());
	}

}
