package scomp;

/**
 * 
 * @author wilson (creation 2010-07-21)
 *
 */
public final class CharLiteral extends AbstractLiteral{
	
	private final char value;
	
	/**
	 * 
	 * @param value
	 * <br>Range: any char
	 */
	public CharLiteral(final char value){
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any char
	 */
	public final char getValue(){
		return this.value;
	}
	
}