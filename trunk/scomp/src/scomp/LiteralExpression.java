package scomp;

/**
 * This class defines a Decaf expression for a literal.
 * 
 * @author Wilson (creation 2010-07-22)
 */
public final class LiteralExpression extends AbstractExpression {
	
	private final AbstractLiteral literal;
	
	/**
	 * 
	 * @param literal
	 * <br>Not null
	 * <br>Shared
	 */
	public LiteralExpression(final AbstractLiteral literal) {
		this.literal = literal;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractLiteral getLiteral(){
		return this.literal;
	}
	
}
