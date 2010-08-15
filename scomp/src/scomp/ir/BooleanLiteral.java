package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf boolean literal.
 * 
 * @author Wilson (creation 2010-07-21)
 *
 */
public final class BooleanLiteral extends AbstractLiteral {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public BooleanLiteral(final DecafToken token){
		super(token);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final Class<?> getType() {
		return boolean.class;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any boolean
	 */
	public final boolean getValue(){
		return (Boolean) this.getToken().getValue();
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"value{" + this.getValue() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return Boolean.valueOf(this.getValue()).hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final BooleanLiteral that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getValue() == that.getValue();
	}
	
}
