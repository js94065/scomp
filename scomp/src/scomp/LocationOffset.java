package scomp;

/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class LocationOffset extends Location {

	private final String identifier;
	private final Expression expression;
	
	public LocationOffset(final String identifier, final Expression expression) {
		this.identifier = identifier;
		this.expression = expression;
	}
	
	public final String getIdentifier() {
		return this.identifier;
	}
	
	public final Expression getExpression() {
		return this.expression;
	}
}
