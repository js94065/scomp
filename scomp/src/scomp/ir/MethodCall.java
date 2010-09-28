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
	
	private MethodDeclaration declaration;
	
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
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final MethodDeclaration getDeclaration() {
		return this.declaration;
	}
	
	/**
	 * 
	 * @param declaration
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final void setDeclaration(MethodDeclaration declaration) {
		this.declaration = declaration;
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
