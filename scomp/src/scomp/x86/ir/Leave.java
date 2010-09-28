package scomp.x86.ir;

/**
 * This class represents the x86 "leave" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Leave extends AbstractNullaryInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 */
	public Leave(final String sizeSuffix) {
		super(sizeSuffix);
	}
	
}
