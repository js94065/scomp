package scomp;

/**
 * This class defines part of the Decaf expression grammar; Location.
 * 
 * @author Wilson (creation 2010-07-21)
 */
public final class LocationExpression extends AbstractExpression {
	
	private final AbstractLocation location;
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 * <br>Shared
	 */
	public LocationExpression(final AbstractLocation location){
		this.location = location;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractLocation getLocation(){
		return this.location;
	}
	
	@Override
	protected final int doHashCode() {
		return this.getLocation().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final LocationExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getLocation().equals(that.getLocation());
	}
	
}
