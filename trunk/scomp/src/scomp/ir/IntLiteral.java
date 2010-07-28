package scomp.ir;

import scomp.Tools;

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
	
	@Override
	public final void accept(final Visitor visitor) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getValue(){
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
		return Integer.valueOf(this.getValue()).hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final IntLiteral that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getValue() == that.getValue();
	}
	
}
