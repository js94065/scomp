package scomp;

/**
 * This class defines a Decaf typed entity declaration.
 * 
 * @author codistmonk (creation 2010-07-13)
 *
 */
public abstract class AbstractTypedEntityDeclaration extends AbstractNode {
	
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
	public AbstractTypedEntityDeclaration(final Class<?> type, final String identifier) {
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
