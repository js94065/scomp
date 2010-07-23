package scomp;

/**
 * This class defines a Decaf location statement.
 * 
 * @author js94065 (creation 2010-07-17)
 *
 */

public class StatementLocation extends AbstractStatement {

	private final AbstractLocation abstractLocation;
	
	private final AbstractExpression abstractExpression;
	
	public StatementLocation(final AbstractLocation abstractLocation, 
			final AbstractExpression abstractExpression) {
		this.abstractLocation = abstractLocation;
		this.abstractExpression = abstractExpression;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractLocation getLocation() {
		return this.abstractLocation;
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
