package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf if statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class IfStatement extends AbstractStatement {
	
	private final AbstractExpression condition;
	
	private final Block thenBlock;
	
	private final Block elseBlock;
	
	/**
	 * 
	 * @param condition
	 * <br>Not null
	 * <br>Shared
	 * @param thenBlock
	 * <br>Not null
	 * <br>Shared
	 * @param elseBlock
	 * <br>Maybe null
	 * <br>Shared
	 */
	public IfStatement(final AbstractExpression condition, final Block thenBlock, final Block elseBlock) {
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.beginVisit(this);
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
	public final Block getThenBlock() {
		return this.thenBlock;
	}

	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final Block getElseBlock() {
		return this.elseBlock;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"condition{" + this.getCondition() + "} " +
				"thenBlock{" + this.getThenBlock() + "} " +
				"elseBlock{" + this.getElseBlock() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getCondition().hashCode() + this.getThenBlock().hashCode() + Tools.hashCode(this.getElseBlock());
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final IfStatement that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getCondition().equals(that.getCondition()) &&
				this.getThenBlock().equals(that.getThenBlock()) &&
				Tools.equals(this.getElseBlock(), that.getElseBlock());
	}
	
}
