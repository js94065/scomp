package scomp.x86.ir;

/**
 * This is the base class for x86 instructions that take 2 operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractBinaryInstruction extends AbstractInstruction {
	
	private final AbstractOperand firstOperand;
	
	private final AbstractOperand secondOperand;
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param firstOperand
	 * <br>Not null
	 * <br>Shared
	 * @param secondOperand
	 * <br>Not null
	 * <br>Shared
	 */
	protected AbstractBinaryInstruction(final String sizeSuffix,
			final AbstractOperand firstOperand, final AbstractOperand secondOperand) {
		super(sizeSuffix);
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractOperand getFirstOperand() {
		return this.firstOperand;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractOperand getSecondOperand() {
		return this.secondOperand;
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName().toLowerCase() + this.getSizeSuffix() +
				" " + this.getFirstOperand() + ", " + this.getSecondOperand();
	}
	
}
