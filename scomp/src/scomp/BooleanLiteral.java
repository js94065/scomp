package scomp;

public class BooleanLiteral extends Literal{

	private final String booleanLiteral;
	
	public BooleanLiteral(final String booleanLiteral){
		this.booleanLiteral = booleanLiteral;
	}
	
	public final String getBooleanLiteral(){
		return this.booleanLiteral;
	}
}
