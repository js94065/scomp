package scomp;


/**
 * This class defines a Decaf if statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */


public class StatementIf extends Statement {

	private final Expr expr;
	
	private final Block ifBlock;
	
	private final Block elseBlock;
	
	public StatementIf(Expr expr, Block ifBlock, Block elseBlock) {
		this.expr = expr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public Expr getExpr() {
		return expr;
	}

	public Block getIfBlock() {
		return ifBlock;
	}

	public Block getElseBlock() {
		return elseBlock;
	}
	
}
