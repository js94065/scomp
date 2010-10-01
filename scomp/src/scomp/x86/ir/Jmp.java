package scomp.x86.ir;

/**
 * This class represents the x86 "jmp" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Jmp extends AbstractUnaryInstruction {
	
	/**
	 * 
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public Jmp(final AbstractOperand operand) {
		super(SIZE_SUFFIX_UNSPECIFIED, operand);
	}
	
}
