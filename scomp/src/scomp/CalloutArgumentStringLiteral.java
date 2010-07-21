package scomp;

/**
 * This class defines part of the CalloutArgument grammar.
 * 
 * @author Wilson (creation 2010-07-20)
 */

public class CalloutArgumentStringLiteral extends CalloutArgument {
	private final String stringLiteral;
	
	public CalloutArgumentStringLiteral(final String stringLiteral){
		this.stringLiteral = stringLiteral;
	}
	
	public final String getStringLiteral(){
		return this.stringLiteral;
	}
}
