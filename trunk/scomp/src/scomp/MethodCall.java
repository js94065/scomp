package scomp;

import java.util.List;

/**
 * The Decaf method name.
 * 
 * @author Wilson (creation 2010-07-19)
 */
public final class MethodCall extends AbstractMethodCall<AbstractExpression> {
	
	/**
	 * 
	 * @param methodName
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 */
	public MethodCall(final String methodName, final List<AbstractExpression> arguments) {
		super(methodName, arguments);
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"methodName{" + this.getMethodName() + "} " +
				"arguments{" + this.getArguments() + "}" +
				"}";
	}
	
}
