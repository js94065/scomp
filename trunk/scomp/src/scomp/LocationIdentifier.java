package scomp;

/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class LocationIdentifier extends Location {

	private final String identifier;
	
	public LocationIdentifier(final String identifier) {
		this.identifier = identifier;
	}
	
	public final String getIdentifier() {
		return this.identifier;
	}
}
