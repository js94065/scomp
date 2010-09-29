package scomp.x86.ir;

/**
 * This class represents the x86 "pop" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Pop extends AbstractUnaryInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public Pop(final String sizeSuffix, final AbstractOperand operand) {
		super(sizeSuffix, operand);
	}
	
}
