package scomp.x86.ir;

/**
 * This class represents the x86 "enter" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Enter extends AbstractBinaryInstruction {
	
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
	public Enter(final String sizeSuffix, final AbstractOperand firstOperand, final AbstractOperand secondOperand) {
		super(sizeSuffix, firstOperand, secondOperand);
	}
	
}
