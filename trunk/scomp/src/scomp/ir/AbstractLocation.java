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
	
	private AbstractTypedEntityDeclaration declaration;
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 */
	public AbstractLocation(final DecafToken identifier) {
		super(identifier);
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final AbstractTypedEntityDeclaration getDeclaration() {
		return this.declaration;
	}
	
	/**
	 * 
	 * @param declaration
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final void setDeclaration(final AbstractTypedEntityDeclaration declaration) {
		this.declaration = declaration;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getIdentifier() {
		return this.getToken().getValue().toString();
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
