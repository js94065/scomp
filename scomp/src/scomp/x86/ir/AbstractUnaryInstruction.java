package scomp.x86.ir;

/**
 * This is the base class for x86 instructions that take 2 operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractUnaryInstruction extends AbstractInstruction {
	
	private final AbstractOperand operand;
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	protected AbstractUnaryInstruction(final String sizeSuffix, final AbstractOperand operand) {
		super(sizeSuffix);
		this.operand = operand;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractOperand getOperand() {
		return this.operand;
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName().toLowerCase() + this.getSizeSuffix() + " " + this.getOperand();
	}
	
}
