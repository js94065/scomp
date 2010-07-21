package scomp;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class CalloutArgumentExpression extends CalloutArgument {
	
	private final Expression expression;
	
	public CalloutArgumentExpression(final Expression expression){
		this.expression = expression;
	}
	
	public final Expression getExpression(){
		return this.expression;
	}
}
