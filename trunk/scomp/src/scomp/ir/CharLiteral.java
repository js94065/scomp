package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines a Decaf char literal.
 * 
 * @author Wilson (creation 2010-07-21)
 *
 */
public final class CharLiteral extends AbstractLiteral{
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public CharLiteral(final DecafToken token){
		super(token);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public final Class<?> getType() {
		return char.class;
	}
	
	/**
	 * 
	 * @return
	 * <br>Range: any char
	 */
	public final char getValue(){
		return (Character) this.getToken().getValue();
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
		return Character.valueOf(this.getValue()).hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final CharLiteral that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getValue() == that.getValue();
	}
	
}
