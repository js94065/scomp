package scomp;

/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */
public final class ArrayLocation extends AbstractLocation {
	
	private final AbstractExpression offset;
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param offset
	 * <br>Not null
	 * <br>Shared
	 */
	public ArrayLocation(final String identifier, final AbstractExpression offset) {
		super(identifier);
		this.offset = offset;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getOffset() {
		return this.offset;
	}
	
}
