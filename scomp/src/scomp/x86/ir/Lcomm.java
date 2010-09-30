package scomp.x86.ir;

/**
 * This class represents the x86 GAS ".lcomm" directive.
 * 
 * @author codistmonk (creation 2010-09-30)
 *
 */
public final class Lcomm extends AbstractDirective {
	
	private final int variableByteCount;
	
	private final String globalName;
	
	private final Integer variableCount;
	
	/**
	 * 
	 * @param variableByteCount
	 * <br>Range: { 4, 8 }
	 * @param globalName
	 * <br>Not null
	 * <br>Shared
	 * @param variableCount
	 * <br>Maybe null
	 * <br>Shared
	 */
	public Lcomm(final int variableByteCount, final String globalName, final Integer variableCount) {
		this.variableByteCount = variableByteCount;
		this.globalName = globalName;
		this.variableCount = variableCount;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: { 4, 8 }
	 */
	public final int getVariableByteCount() {
		return this.variableByteCount;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getGlobalName() {
		return this.globalName;
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final Integer getVariableCount() {
		return this.variableCount;
	}
	
	@Override
	public final String toString() {
		return ".lcomm " + this.getGlobalName() + ", " + this.getVariableByteCount() +
				(this.getVariableCount() == null ? "" : " * " + this.getVariableCount());
	}
	
}
