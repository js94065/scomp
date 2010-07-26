package scomp;

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
	public VariableDeclaration(final Class<?> type, String identifier) {
		super(type, identifier);
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
