package scomp;


/**
 * This class defines a Decaf if statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */


public class StatementIf extends Statement {

	private final Expression expression;
	
	private final Block ifBlock;
	
	private final Block elseBlock;
	
	public StatementIf(Expression expression, Block ifBlock, Block elseBlock) {
		this.expression = expression;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public Expression getExpr() {
		return expression;
	}

	public Block getIfBlock() {
		return ifBlock;
	}

	public Block getElseBlock() {
		return elseBlock;
	}
	
}
