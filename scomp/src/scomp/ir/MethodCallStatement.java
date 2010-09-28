package scomp.ir;

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
		super(methodCall.getToken());
		this.methodCall = methodCall;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
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
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"methodCall{" + this.getMethodCall() + "} " +
				"}";
	}
	
}
