package scomp;

/**
 * This class defines a Decaf location statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementLocation extends AbstractStatement {

	private final Location location;
	
	private final AbstractExpression abstractExpression;
	
	public StatementLocation(final Location location, 
			final AbstractExpression abstractExpression) {
		this.location = location;
		this.abstractExpression = abstractExpression;
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
	public final AbstractExpression getExpression() {
		return this.abstractExpression;
	}

}
