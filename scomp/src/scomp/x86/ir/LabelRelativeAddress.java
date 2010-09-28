package scomp.x86.ir;

import static scomp.x86.ir.Register.Name.RIP;

/**
 * This class represents x86 label relative address operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class LabelRelativeAddress extends AbstractAddress {
	
	private final String labelName;
	
	/**
	 * 
	 * @param size
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 * <br>Shared
	 * @param labelName
	 * <br>Not null
	 * <br>Shared
	 */
	public LabelRelativeAddress(final String size, final String labelName) {
		super(size);
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
		return this.getLabelName() + "(%" +
				AbstractInstruction.getResizedName(RIP, this.getSize()).toString().toLowerCase() + ")";
	}
	
}
