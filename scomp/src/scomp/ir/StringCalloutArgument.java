package scomp.ir;

import scomp.DecafToken;
import scomp.Tools;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 * 
 */
public class StringCalloutArgument extends AbstractCalloutArgument {
	
	/**
	 * 
	 * @param token
	 * <br>Not null
	 * <br>Shared
	 */
	public StringCalloutArgument(final DecafToken token){
		super(token);
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getString(){
		return this.getToken().getValue().toString();
	}
	
	@Override
	protected final int doHashCode() {
		return this.getString().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final StringCalloutArgument that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getString().equals(that.getString());
	}
	
}
