package scomp.ir;

import scomp.Tools;

/**
 * Defines part of the location grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */
public final class ArrayLocation extends AbstractLocation {
	
	private final AbstractExpression offset;
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param offset
	 * <br>Not null
	 * <br>Shared
	 */
	public ArrayLocation(final String identifier, final AbstractExpression offset) {
		super(identifier);
		this.offset = offset;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getOffset() {
		return this.offset;
	}
	
	@Override
	protected final int doHashCode() {
		return super.doHashCode() + this.getOffset().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final ArrayLocation that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getOffset().equals(that.getOffset());
	}
	
}
