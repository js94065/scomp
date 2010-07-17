package scomp;

/**
 * This class defines a Decaf block statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */


public class StatementBlock extends Statement {

	private final Block block;
	
	public StatementBlock(Block block) {
		this.block = block;
	}

	public Block getBlock() {
		return block;
	}
	
}
