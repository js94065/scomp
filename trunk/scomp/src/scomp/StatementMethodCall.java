package scomp;

/**
 * This class defines a Decaf method call statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementMethodCall extends Statement {

	private final MethodCall methodCall;
	
	public StatementMethodCall(MethodCall methodCall) {
		this.methodCall = methodCall;
	}

	public MethodCall getMethodCall() {
		return methodCall;
	}

}
