package scomp;

/**
 * This class defines Decaf CalloutArgument.
 * 
 * @author Wilson (creation 2010-07-19)
 */

public class CalloutArgument extends AbstractNode{

	private final Expr expression;
	private final String stringLiteral;
	
	public CalloutArgument(final Expr expression, final String stringLiteral){
		this.expression = expression;
		this.stringLiteral = stringLiteral;
	}
	
	public final Expr getExpression(){
		return this.expression;
	}
	
	public final String getStringLiteral(){
		return this.stringLiteral;
	}
}
