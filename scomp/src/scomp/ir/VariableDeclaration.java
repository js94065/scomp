package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf Variable Declaration.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */
public final class VariableDeclaration extends AbstractTypedEntityDeclaration {

	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public VariableDeclaration(final Class<?> type, DecafToken identifier) {
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
				"type{" + this.getType() + "} " +
				"identifier{" + this.getIdentifier() + "}" +
				"}";
	}
	
}
