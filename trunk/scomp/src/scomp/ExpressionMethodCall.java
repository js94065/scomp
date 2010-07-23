package scomp;

/**
 * This class defines part of the Decaf expression grammar; MethodCall.
 * 
 * @author Wilson 2010-07-21
 */

public class ExpressionMethodCall extends AbstractExpression{

	private final MethodCall methodCall;
	
	public ExpressionMethodCall(final MethodCall methodCall){
		this.methodCall = methodCall;
	}
	
	public final MethodCall getMethodCall(){
		return this.methodCall;
	}
}
