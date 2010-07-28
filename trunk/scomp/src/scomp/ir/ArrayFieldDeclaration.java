package scomp.ir;

import scomp.Tools;

/**
 * This class defines a Decaf array field declaration.
 * 
 * @author codistmonk (creation 2010-07-13)
 *
 */
public final class ArrayFieldDeclaration extends AbstractFieldDeclaration {
	
	private final int elementCount;
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param elementCount 
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	public ArrayFieldDeclaration(final Class<?> type, final String identifier, final int elementCount) {
		super(type, identifier);
		this.elementCount = elementCount;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	public final int getElementCount() {
		return this.elementCount;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"elementType{" + this.getType().getName() + "} " +
				"identifier{" + this.getIdentifier() + "} " +
				"elementCount{" + this.getElementCount() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return super.doHashCode() + this.getElementCount();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final ArrayFieldDeclaration that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getElementCount() == that.getElementCount();
	}
	
}
