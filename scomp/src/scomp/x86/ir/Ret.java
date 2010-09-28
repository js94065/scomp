package scomp.x86.ir;

/**
 * This class represents the x86 "ret" instruction.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Ret extends AbstractNullaryInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 */
	public Ret(final String sizeSuffix) {
		super(sizeSuffix);
	}
	
}
