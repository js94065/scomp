package scomp;

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
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Block getBlock() {
		return this.block;
	}
	
}
