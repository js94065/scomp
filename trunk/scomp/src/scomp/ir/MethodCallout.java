package scomp.ir;

import java.util.List;

import scomp.DecafToken;

/**
 * The Decaf method callout.
 * 
 * @author Wilson (creation 2010-07-19)
 * 
 */
public final class MethodCallout extends AbstractMethodCall<AbstractCalloutArgument> {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 */
	public MethodCallout(final DecafToken token, final List<AbstractCalloutArgument> arguments) {
		super(token, token.getValue().toString(), arguments);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"stringLiteral{" + this.getMethodName() + "} " +
				"arguments{" + this.getArguments() + "}" +
				"}";
	}
	
}
