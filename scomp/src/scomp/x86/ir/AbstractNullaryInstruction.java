package scomp.x86.ir;

/**
 * This is the base class for x86 instructions that don't take operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractNullaryInstruction extends AbstractInstruction {
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 */
	protected AbstractNullaryInstruction(final String sizeSuffix) {
		super(sizeSuffix);
	}
	
	@Override
	public final String toString() {
		return this.getClass().getSimpleName().toLowerCase() + this.getSizeSuffix();
	}
	
}
