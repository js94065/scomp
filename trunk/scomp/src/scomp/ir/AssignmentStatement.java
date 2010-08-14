package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf location statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class AssignmentStatement extends AbstractStatement {
	
	private final AbstractLocation location;
	
	private final AbstractExpression expression;
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 * <br>Shared
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 */
	public AssignmentStatement(final AbstractLocation location, final AbstractExpression expression) {
		this.location = location;
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
	public final AbstractLocation getLocation() {
		return this.location;
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
		return
				this.getClass().getSimpleName() + "{" +
				"location{" + this.getLocation() + "} " +
				"expression{" + this.getExpression() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getLocation().hashCode() + this.getExpression().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final AssignmentStatement that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getLocation().equals(that.getLocation()) &&
				this.getExpression().equals(that.getExpression());
	}

}
