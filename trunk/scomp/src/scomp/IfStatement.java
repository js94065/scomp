package scomp;

/**
 * This class defines a Decaf if statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class IfStatement extends AbstractStatement {
	
	private final Expression expression;
	
	private final Block thenBlock;
	
	private final Block elseBlock;
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 * @param thenBlock
	 * <br>Not null
	 * <br>Shared
	 * @param elseBlock
	 * <br>Maybe null
	 * <br>Shared
	 */
	public IfStatement(final Expression expression, final Block thenBlock, final Block elseBlock) {
		this.expression = expression;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Expression getExpression() {
		return this.expression;
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
	
}
