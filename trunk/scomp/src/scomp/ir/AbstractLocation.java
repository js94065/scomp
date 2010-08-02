package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf location.
 * 
 * @author Wilson (creation 2010-07-20)
 * 
 */
public abstract class AbstractLocation extends AbstractNode {
	
	private final DecafToken identifier;
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractLocation(final DecafToken identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getIdentifier() {
		return this.identifier.getValue().toString();
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getIdentifierRow() {
		return this.identifier.getRow();
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 */
	public final int getIdentifierColumn() {
		return this.identifier.getColumn();
	}
	
	@Override
	protected int doHashCode() {
		return this.getIdentifier().hashCode();
	}
	
	@Override
	protected boolean doEquals(final Object other) {
		final AbstractLocation that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getIdentifier().equals(that.getIdentifier());
	}
	
}
