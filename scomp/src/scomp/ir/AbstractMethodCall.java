package scomp.ir;

import static scomp.Tools.emptyIfNull;

import java.util.List;

import scomp.Tools;

/**
 * This class defines a Decaf method call.
 * 
 * @param <A> The type of the call arguments
 * 
 * @author Wilson (creation 2010-07-19)
 */
public abstract class AbstractMethodCall<A> extends AbstractNode {
	
	private final String methodName;
	
	private final List<A> arguments;
	
	/**
	 * 
	 * @param methodName
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 */
	public AbstractMethodCall(final String methodName, final List<A> arguments) {
		this.methodName = methodName;
		this.arguments = emptyIfNull(arguments);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getMethodName() {
		return this.methodName;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<A> getArguments(){
		return this.arguments;
	}
	
	@Override
	protected final int doHashCode() {
		return this.getMethodName().hashCode() + this.getArguments().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		@SuppressWarnings("unchecked")
		final AbstractMethodCall<A> that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getMethodName().equals(that.getMethodName()) &&
				this.getArguments().equals(that.getArguments());
	}
	
}
