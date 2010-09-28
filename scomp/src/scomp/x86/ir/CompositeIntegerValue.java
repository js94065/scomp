package scomp.x86.ir;

/**
 * This class represents immediate integer operands of the form "$(a * b)" (in base 10).
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class CompositeIntegerValue extends AbstractOperand {
	
	private final int a;
	
	private final int b;
	
	/**
	 * 
	 * @param a
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE}
	 * @param b
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE}
	 */
	public CompositeIntegerValue(final int a, final int b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE}
	 */
	public final int getA() {
		return this.a;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE}
	 */
	public final int getB() {
		return this.b;
	}
	
	@Override
	public final String toString() {
		return "$(" + this.getA() + " * " + this.getB() + ")";
	}
	
}
