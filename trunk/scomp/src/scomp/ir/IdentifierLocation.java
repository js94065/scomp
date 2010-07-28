package scomp.ir;

import scomp.Tools;


/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */
public final class IdentifierLocation extends AbstractLocation {
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public IdentifierLocation(final String identifier) {
		super(identifier);
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
				"identifier{" + this.getIdentifier() + "}" +
				"}";
	}
	
}
