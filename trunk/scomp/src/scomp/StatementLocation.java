package scomp;

/**
 * This class defines a Decaf location statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementLocation extends AbstractStatement {

	private final Location location;
	
	private final Expression expression;
	
	public StatementLocation(final Location location, 
			final Expression expression) {
		this.location = location;
		this.expression = expression;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Location getLocation() {
		return this.location;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Expression getExpression() {
		return this.expression;
	}

}
