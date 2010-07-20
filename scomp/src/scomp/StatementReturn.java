package scomp;

/**
 * This class defines a Decaf return statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementReturn extends Statement {

	private final Expression expression;
	
	public StatementReturn(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpr() {
		return expression;
	}
	
	
}
