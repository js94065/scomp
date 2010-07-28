package scomp.ir;

import scomp.DecafToken;

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
	public ParameterDeclaration(final Class<?> type, final DecafToken identifier) {
		super(type, identifier);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
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
