package scomp;

/**
 * 
 * @author wilson (creation 2010-07-21)
 *
 */
public final class IntLiteral extends AbstractLiteral {
	
	private final int intLiteral;
	
	public IntLiteral(final String intLiteral){
		this.intLiteral = Integer.parseInt(intLiteral);
	}
	
	public final int getValue(){
		return this.intLiteral;
	}
}
