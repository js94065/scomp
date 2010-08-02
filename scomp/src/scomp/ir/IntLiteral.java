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
	
	private final DecafToken intLiteralToken;
	
	private final int value;
	
	/**
	 * 
	 * @param intLiteralToken
	 * <br>Range: any integer
	 */
	public IntLiteral(final DecafToken intLiteralToken){
		this.intLiteralToken = intLiteralToken;
		
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
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getValue(){
		return this.value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getRow() {
		return this.intLiteralToken.getRow();
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getColumn() {
		return this.intLiteralToken.getColumn();
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
