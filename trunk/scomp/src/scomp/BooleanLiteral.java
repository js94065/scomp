package scomp;

/**
 * This class defines a Decaf boolean literal.
 * 
 * @author Wilson (creation 2010-07-21)
 *
 */
public final class BooleanLiteral extends AbstractLiteral {
	
	private final boolean value;
	
	/**
	 * 
	 * @param value
	 * <br>Range: any boolean
	 */
	public BooleanLiteral(final boolean value){
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any boolean
	 */
	public final boolean getValue(){
		return this.value;
	}
	
}
