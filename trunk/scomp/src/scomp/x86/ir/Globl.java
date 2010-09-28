package scomp.x86.ir;

/**
 * This class represent a ".globl" x86 GAS directive.
 * <br>The name is not misspelled (it is ".globl", not ".global").
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Globl extends AbstractDirective {
	
	private final String labelName;
	
	/**
	 * 
	 * @param labelName
	 * <br>Not null
	 * <br>Shared
	 */
	public Globl(final String labelName) {
		this.labelName = labelName;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getLabelName() {
		return this.labelName;
	}

	@Override
	public final String toString() {
		return ".globl " + this.getLabelName();
	}
	
}
