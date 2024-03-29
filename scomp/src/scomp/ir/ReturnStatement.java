package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf return statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public class ReturnStatement extends AbstractStatement {
	
	private final AbstractExpression abstractExpression;
	
	/**
	 * 
	 * @param token 
	 * <br>Not null
	 * <br>Shared
	 * @param abstractExpression
	 * <br>Maybe null
	 * <br>Shared
	 */
	public ReturnStatement(final DecafToken token, final AbstractExpression abstractExpression) {
		super(token);
		this.abstractExpression = abstractExpression;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final AbstractExpression getExpression() {
		return this.abstractExpression;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"expression{" + this.getExpression() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return Tools.hashCode(this.getExpression());
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final ReturnStatement that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				Tools.equals(this.getExpression(), that.getExpression());
	}
	
}
