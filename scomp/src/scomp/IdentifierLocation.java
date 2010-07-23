package scomp;

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
	
}
