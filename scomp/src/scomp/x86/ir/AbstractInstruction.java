package scomp.x86.ir;

/**
 * This is the base class for x86 instructions.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractInstruction extends AbstractProgramElement {
	
	private final String sizeSuffix;
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 */
	protected AbstractInstruction(final String sizeSuffix) {
		this.sizeSuffix = sizeSuffix;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getSizeSuffix() {
		return this.sizeSuffix;
	}
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_UNSPECIFIED = "";
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_32 = "l";
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_64 = "q";
	
}
