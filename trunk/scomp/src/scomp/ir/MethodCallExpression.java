package scomp.ir;

import scomp.Tools;

/**
 * This class defines part of the Decaf expression grammar; MethodCall.
 * 
 * @author Wilson 2010-07-21
 */
public final class MethodCallExpression extends AbstractExpression {
	
	private final AbstractMethodCall<?> methodCall;
	
	private Class<?> type;
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 * <br>Shared
	 */
	public MethodCallExpression(final AbstractMethodCall<?> methodCall){
		super(methodCall.getToken());
		this.methodCall = methodCall;
	}

	public final void setType(Class<?> type) {
		this.type = type;
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
	public final AbstractMethodCall<?> getMethodCall(){
		return this.methodCall;
	}
	
	@Override
	public final Class<?> getType() {
		return this.type;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"methodCall{" + this.getMethodCall() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getMethodCall().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final MethodCallExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getMethodCall().equals(that.getMethodCall());
	}

}
