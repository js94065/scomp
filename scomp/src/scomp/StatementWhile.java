package scomp;

/**
 * This class defines a Decaf while statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementWhile extends Statement {

	private final Expr expr;
	
	private final Block block;
	
	public StatementWhile(Expr expr, Block block) {
		this.expr = expr;
		this.block = block;
	}

	public Expr getExpr() {
		return expr;
	}

	public Block getBlock() {
		return block;
	}
	
}
