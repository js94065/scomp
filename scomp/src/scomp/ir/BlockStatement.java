package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf block statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class BlockStatement extends AbstractStatement {
	
	private final Block block;
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 * <br>Shared
	 */
	public BlockStatement(final Block block) {
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
	public final Block getBlock() {
		return this.block;
	}
	
	@Override
	public final String toString() {
		return this.getBlock().toString();
	}
	
	@Override
	protected final int doHashCode() {
		return this.getBlock().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final BlockStatement that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getBlock().equals(that.getBlock());
	}
	
}
