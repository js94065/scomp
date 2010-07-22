package scomp;

/**
 * This class defines part of the Decaf expression grammar; Location.
 * 
 * @author Wilson 2010-07-21
 */

public class ExpressionLocation extends Expression{
	
	private final Location location;
	
	public ExpressionLocation(final Location location){
		this.location = location;
	}

	public final Location getExpression(){
		return this.location;
	}
}
