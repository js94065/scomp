package scomp.x86.ir;

/**
 * This class represents the x86 "call" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Call extends AbstractUnaryInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public Call(final String sizeSuffix, final AbstractOperand operand) {
		super(sizeSuffix, operand);
	}
	
}
