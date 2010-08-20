package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * 
 * @author codistmonk (creation 2010-07-26)
 *
 */
public final class MinusExpression extends AbstractExpression {
	
	private final AbstractExpression operand;
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public MinusExpression(final DecafToken token, final AbstractExpression operand) {
		super(token);
		this.operand = operand;
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
	public final AbstractExpression getOperand() {
		return this.operand;
	}

	@Override
	public final Class<?> getType() {
		return int.class;
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + "{" +
				"expression{" + this.getOperand() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getOperand().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final MinusExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getOperand().equals(that.getOperand());
	}

}
