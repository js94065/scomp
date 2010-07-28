package scomp.ir;

import scomp.Tools;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */
public class CalloutArgumentStringLiteral extends AbstractCalloutArgument {
	
	private final String string;
	
	/**
	 * 
	 * @param string
	 * <br>Not null
	 * <br>Shared
	 */
	public CalloutArgumentStringLiteral(final String string){
		this.string = string;
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
	public final String getString(){
		return this.string;
	}
	
	@Override
	protected final int doHashCode() {
		return this.getString().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final CalloutArgumentStringLiteral that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getString().equals(that.getString());
	}
	
}
