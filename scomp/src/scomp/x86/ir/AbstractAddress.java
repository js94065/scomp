package scomp.x86.ir;

/**
 * This is the base class for some x86 address operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractAddress extends AbstractOperand {
	
	private final String size;
	
	/**
	 * 
	 * @param size
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 * <br>Shared
	 */
	public AbstractAddress(final String size) {
		this.size = size;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 * <br>Shared
	 */
	public final String getSize() {
		return this.size;
	}
	
}
