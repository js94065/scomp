package scomp.ir;

import scomp.Tools;


/**
 * This class defines a Decaf field declaration.
 * 
 * @author codistmonk (creation 2010-07-26)
 *
 */
public final class ParameterDeclaration extends AbstractTypedEntityDeclaration {
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public ParameterDeclaration(final Class<?> type, final String identifier) {
		super(type, identifier);
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
				"type{" + this.getType().getName() + "} " +
				"identifier{" + this.getIdentifier() + "}" +
				"}";
	}
	
}
