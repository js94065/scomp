package scomp.ir;

/**
 * This class is the base class for Decaf field declarations.
 * 
 * @author codistmonk (creation 2010-07-26)
 */
public abstract class AbstractFieldDeclaration extends AbstractTypedEntityDeclaration {
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractFieldDeclaration(final Class<?> type, final String identifier) {
		super(type, identifier);
	}
	
}
