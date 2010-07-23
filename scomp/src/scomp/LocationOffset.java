package scomp;

/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class LocationOffset extends Location {

	private final String identifier;
	private final AbstractExpression abstractExpression;
	
	public LocationOffset(final String identifier, final AbstractExpression abstractExpression) {
		this.identifier = identifier;
		this.abstractExpression = abstractExpression;
	}
	
	public final String getIdentifier() {
		return this.identifier;
	}
	
	public final AbstractExpression getExpression() {
		return this.abstractExpression;
	}
}
