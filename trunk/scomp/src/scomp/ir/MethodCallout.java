package scomp.ir;

import java.util.List;

import scomp.Tools;

/**
 * The Decaf method callout.
 * 
 * @author Wilson (creation 2010-07-19)
 * 
 */
public final class MethodCallout extends AbstractMethodCall<AbstractCalloutArgument> {
	
	/**
	 * 
	 * @param stringLiteral
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 */
	public MethodCallout(final String stringLiteral, final List<AbstractCalloutArgument> arguments) {
		super(stringLiteral, arguments);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		// TODO
		Tools.debugPrint("TODO");
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
