package scomp.x86.ir;

/**
 * This class represents the x86 "cmovx" instruction ('x' being a condition suffix).
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Cmov extends AbstractBinaryInstruction {
	
	private final ConditionSuffix conditionSuffix;
	
	/**
	 * 
	 * @param conditionSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 * @param firstOperand
	 * <br>Not null
	 * <br>Shared
	 * @param secondOperand
	 * <br>Not null
	 * <br>Shared
	 */
	public Cmov(final ConditionSuffix conditionSuffix, final String sizeSuffix, final AbstractOperand firstOperand, final AbstractOperand secondOperand) {
		super(sizeSuffix, firstOperand, secondOperand);
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
