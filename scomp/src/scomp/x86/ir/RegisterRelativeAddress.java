package scomp.x86.ir;

import scomp.x86.ir.Register.Name;

/**
 * This class represents x86 register relative address operands.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class RegisterRelativeAddress extends AbstractAddress {
	
	private final int offset;
	
	private final Name registerName;
	
	/**
	 * 
	 * @param size
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 * <br>Shared
	 * @param offset
	 * <br>Range: any integer
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	public RegisterRelativeAddress(final String size, final int offset, final Name registerName) {
		super(size);
		this.offset = offset;
		this.registerName = registerName;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any integer
	 */
	public final int getOffset() {
		return this.offset;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Name getRegisterName() {
		return this.registerName;
	}

	@Override
	public final String toString() {
		return this.getOffset() + "(%" +
				AbstractInstruction.getResizedName(this.getRegisterName(), this.getSize()).toString().toLowerCase() + ")";
	}
	
}
