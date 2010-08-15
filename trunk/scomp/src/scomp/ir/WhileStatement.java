package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf while statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class WhileStatement extends AbstractStatement {
	
	private final AbstractExpression condition;
	
	private final Block block;
	
	/**
	 * 
	 * @param token 
	 * <br>Not null
	 * <br>Shared
	 * @param condition
	 * <br>Not null
	 * <br>Shared
	 * @param block
	 * <br>Not null
	 * <br>Shared
	 */
	public WhileStatement(final DecafToken token, final AbstractExpression condition, final Block block) {
		super(token);
		this.condition = condition;
		this.block = block;
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
	public final AbstractExpression getCondition() {
		return this.condition;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Block getBlock() {
		return this.block;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"condition{" + this.getCondition() + "} " +
				"block{" + this.getBlock() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getCondition().hashCode() + this.getBlock().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final WhileStatement that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getCondition().equals(that.getCondition()) &&
				this.getBlock().equals(that.getBlock());
	}
	
}
