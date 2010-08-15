package scomp.ir;

import java.util.List;

import scomp.DecafToken;

/**
 * The Decaf method name.
 * 
 * @author Wilson (creation 2010-07-19)
 * 
 */
public final class MethodCall extends AbstractMethodCall<AbstractExpression> {
	
	private final DecafToken methodNameIdentifier;
	
	/**
	 * 
	 * @param methodNameIdentifier
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 */
	public MethodCall(final DecafToken methodNameIdentifier, final List<AbstractExpression> arguments) {
		super(methodNameIdentifier, methodNameIdentifier.getValue().toString(), arguments);
		this.methodNameIdentifier = methodNameIdentifier;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getMethodNameIdentifierRow() {
		return this.methodNameIdentifier.getRow();
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getMethodNameIdentifierColumn() {
		return this.methodNameIdentifier.getColumn();
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
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
