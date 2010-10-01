package scomp.x86.ir;

/**
 * This class represents the x86 "jx" instruction ('x' being a condition suffix).
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class J extends AbstractUnaryInstruction {
	
	private final ConditionSuffix conditionSuffix;
	
	/**
	 * 
	 * @param conditionSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param operand
	 * <br>Not null
	 * <br>Shared
	 */
	public J(final ConditionSuffix conditionSuffix, final AbstractOperand operand) {
		super(SIZE_SUFFIX_UNSPECIFIED, operand);
		this.conditionSuffix = conditionSuffix;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final ConditionSuffix getConditionSuffix() {
		return this.conditionSuffix;
	}
	
	@Override
	protected final String getSuffix() {
		return this.getConditionSuffix().toString().toLowerCase() + super.getSuffix();
	}
	
}
