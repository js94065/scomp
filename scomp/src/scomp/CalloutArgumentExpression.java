package scomp;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class CalloutArgumentExpression extends CalloutArgument {
	
	private final AbstractExpression abstractExpression;
	
	public CalloutArgumentExpression(final AbstractExpression abstractExpression){
		this.abstractExpression = abstractExpression;
	}
	
	public final AbstractExpression getExpression(){
		return this.abstractExpression;
	}
}
