package scomp;

/**
 * This class defines a Decaf return statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementReturn extends Statement {

	private final Expr expr;
	
	public StatementReturn(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}
	
	
}
