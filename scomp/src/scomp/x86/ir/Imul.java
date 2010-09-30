package scomp.x86.ir;

/**
 * This class represents the x86 "imul" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Imul extends AbstractBinaryInstruction {
	
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
	public Imul(final String sizeSuffix, final AbstractOperand firstOperand, final AbstractOperand secondOperand) {
		super(sizeSuffix, firstOperand, secondOperand);
	}
	
}
