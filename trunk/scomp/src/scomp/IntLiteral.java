package scomp;

/**
 * This class defines a Decaf int literal.
 * 
 * @author Wilson (creation 2010-07-21)
 */
public final class IntLiteral extends AbstractLiteral {
	
	private final int value;
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 */
	public IntLiteral(final int value){
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getValue(){
		return this.value;
	}
	
}
