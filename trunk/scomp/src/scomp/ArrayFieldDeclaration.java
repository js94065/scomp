package scomp;

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

	/**
	 * 
	 * @return
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	public final int getElementCount() {
		return this.elementCount;
	}
	
}
