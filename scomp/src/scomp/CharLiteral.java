package scomp;

public class CharLiteral extends Literal{
	
	private final char charLiteral;
	
	public CharLiteral(final char charLiteral){
		this.charLiteral = charLiteral;
	}
	
	public final char getCharLiteral(){
		return this.charLiteral;
	}
}
