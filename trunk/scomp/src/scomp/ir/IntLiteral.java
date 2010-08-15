package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf int literal.
 * 
 * @author Wilson (creation 2010-07-21)
 * 
 */
public final class IntLiteral extends AbstractLiteral {
	
	private final int value;
	
	/**
	 * 
	 * @param intLiteralToken
	 * <br>Range: any integer
	 */
	public IntLiteral(final DecafToken intLiteralToken){
		super(intLiteralToken);
		
		final String valueRepresentation = intLiteralToken.getInputString();
		
		if (valueRepresentation.startsWith("0x")) {
			this.value = Integer.parseInt(valueRepresentation.substring(2), 16);
		} else {
			this.value = Integer.parseInt(valueRepresentation);
		}
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final Class<?> getType() {
		return int.class;
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
