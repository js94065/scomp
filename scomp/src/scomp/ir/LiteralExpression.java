package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf expression for a literal.
 * 
 * @author Wilson (creation 2010-07-22)
 * 
 */
public final class LiteralExpression extends AbstractExpression {
	
	private final AbstractLiteral literal;
	
	/**
	 * 
	 * @param literal
	 * <br>Not null
	 * <br>Shared
	 */
	public LiteralExpression(final AbstractLiteral literal) {
		this.literal = literal;
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
	public final AbstractLiteral getLiteral(){
		return this.literal;
	}
	
	@Override
	public final boolean isLocation() {
		return false;
	}
	
	@Override
	public final Class<?> getType() {
		return this.getLiteral().getType();
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"literal{" + this.getLiteral() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getLiteral().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final LiteralExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getLiteral().equals(that.getLiteral());
	}

	@Override
	public boolean isMethodCall() {
		return false;
	}
	
}
