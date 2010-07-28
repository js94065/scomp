package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf typed entity declaration.
 * 
 * @author codistmonk (creation 2010-07-13)
 *
 */
public abstract class AbstractTypedEntityDeclaration extends AbstractNode {
	
	private final Class<?> type;
	
	private final DecafToken identifier;
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractTypedEntityDeclaration(final Class<?> type, final DecafToken identifier) {
		this.type = type;
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Class<?> getType() {
		return this.type;
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
		return this.getType().hashCode() + this.getIdentifier().hashCode();
	}
	
	@Override
	protected boolean doEquals(final Object other) {
		final AbstractTypedEntityDeclaration that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getType().equals(that.getType()) &&
				this.getIdentifier().equals(that.getIdentifier());
	}
	
}
