package scomp.ir;

import scomp.Tools;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 * 
 */
public final class ExpressionCalloutArgument extends AbstractCalloutArgument {
	
	private final AbstractExpression expression;
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public ExpressionCalloutArgument(final AbstractExpression expression){
		super(expression.getToken());
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
	public final AbstractExpression getExpression(){
		return this.expression;
	}
	
	@Override
	protected final int doHashCode() {
		return this.getExpression().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final ExpressionCalloutArgument that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getExpression().equals(that.getExpression());
	}
	
}
