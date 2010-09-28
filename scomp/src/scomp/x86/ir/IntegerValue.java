package scomp.x86.ir;

/**
 * This class represents simple x86 immediate integer operands (in base 10).
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class IntegerValue extends AbstractOperand {
	
	private final int value;
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 */
	public IntegerValue(final int value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getValue() {
		return this.value;
	}
	
	@Override
	public final String toString() {
		return "$" + this.getValue();
	}
	
}
