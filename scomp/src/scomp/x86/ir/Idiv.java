package scomp.x86.ir;

/**
 * This class represents the x86 "idiv" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Idiv extends AbstractBinaryInstruction {
	
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
	public Idiv(final String sizeSuffix, final AbstractOperand firstOperand, final AbstractOperand secondOperand) {
		super(sizeSuffix, firstOperand, secondOperand);
	}
	
}
