package scomp;

/**
 * This class defines a Decaf method call statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class MethodCallStatement extends AbstractStatement {

	private final MethodCall methodCall;
	
	public MethodCallStatement(MethodCall methodCall) {
		this.methodCall = methodCall;
	}

	public MethodCall getMethodCall() {
		return methodCall;
	}

}
