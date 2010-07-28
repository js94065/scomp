package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf char literal.
 * 
 * @author Wilson (creation 2010-07-21)
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
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"value{" + this.getValue() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return Character.valueOf(this.getValue()).hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final CharLiteral that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getValue() == that.getValue();
	}
	
}
