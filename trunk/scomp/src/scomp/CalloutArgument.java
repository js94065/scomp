package scomp;

/**
 * This class defines Decaf CalloutArgument.
 * 
 * @author Wilson (creation 2010-07-19)
 */

public class CalloutArgument extends AbstractNode{

	private final Expression expression;
	private final String stringLiteral;
	
	public CalloutArgument(final Expression expression, final String stringLiteral){
		this.expression = expression;
		this.stringLiteral = stringLiteral;
	}
	
	public final Expression getExpression(){
		return this.expression;
	}
	
	public final String getStringLiteral(){
		return this.stringLiteral;
	}
}
