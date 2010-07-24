package scomp;

/**
 * This class defines a Decaf method call statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */
public final class MethodCallStatement extends AbstractStatement {
	
	private final AbstractMethodCall<?> methodCall;
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 * <br>Shared
	 */
	public MethodCallStatement(final AbstractMethodCall<?> methodCall) {
		this.methodCall = methodCall;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractMethodCall<?> getMethodCall() {
		return this.methodCall;
	}
	
}
