package scomp;

/**
 * This class defines a Decaf field declaration.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public final class FieldDeclaration extends AbstractNode {
	
	private final Class<?> type;
	
	private final String identifier;
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public FieldDeclaration(final Class<?> type, final String identifier) {
		this.type = type;
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Class<?> getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * 
	 * @author codistmonk (creation 2010-07-13)
	 */
	public static enum Type {
		
		INT, BOOLEAN;
		
	}
	
}
