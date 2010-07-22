package scomp;

public class ExpressionLiteral extends Expression{
	
	private final Literal literal;
	
	public ExpressionLiteral(final Literal literal) {
		this.literal = literal;
	}
	
	public final Literal getLiteral(){
		return this.literal;
	}
}
