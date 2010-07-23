package scomp;

/**
 * This class defines a Decaf while statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementWhile extends AbstractStatement {

	private final AbstractExpression abstractExpression;
	
	private final Block block;
	
	public StatementWhile(AbstractExpression abstractExpression, Block block) {
		this.abstractExpression = abstractExpression;
		this.block = block;
	}

	public AbstractExpression getExpr() {
		return abstractExpression;
	}

	public Block getBlock() {
		return block;
	}
	
}
