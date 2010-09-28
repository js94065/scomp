package scomp.x86.ir;

/**
 * This class represents simple x86 immediate integer operands (in base 10).
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class LabelOperand extends AbstractOperand {
	
	private final String labelName;
	
	/**
	 * 
	 * @param labelName
	 * <br>Not null
	 * <br>Shared
	 */
	public LabelOperand(final String labelName) {
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
		return this.getLabelName();
	}
	
}
