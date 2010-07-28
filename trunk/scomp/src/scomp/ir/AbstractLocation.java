package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf location.
 * 
 * @author Wilson (creation 2010-07-20)
 */
public abstract class AbstractLocation extends AbstractNode {
	
	private final String identifier;
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractLocation(final String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getIdentifier() {
		return this.identifier;
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
