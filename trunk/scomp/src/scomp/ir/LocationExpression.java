package scomp.ir;

import scomp.Tools;

/**
 * This class defines part of the Decaf expression grammar; Location.
 * 
 * @author Wilson (creation 2010-07-21)
 * 
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
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.beginVisit(this);
		
		this.getLocation().accept(visitor);
		
		visitor.endVisit(this);
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
	public final Class<?> getType() {
		// TODO
		Tools.debugPrint("TODO");
		
		//return null;
		return boolean.class;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"location{" + this.getLocation() + "}" +
				"}";
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
