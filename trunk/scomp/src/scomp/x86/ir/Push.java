package scomp.x86.ir;

/**
 * This class represents the x86 "push" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Push extends AbstractUnaryInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public Push(final String sizeSuffix, final AbstractOperand operand) {
		super(sizeSuffix, operand);
	}
	
}
