package scomp;

/**
 * This class defines a Decaf while statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementWhile extends AbstractStatement {

	private final Expression expression;
	
	private final Block block;
	
	public StatementWhile(Expression expression, Block block) {
		this.expression = expression;
		this.block = block;
	}

	public Expression getExpr() {
		return expression;
	}

	public Block getBlock() {
		return block;
	}
	
}
