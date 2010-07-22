package scomp;

public class ExpressionLiteral extends Expression{
	
	private final AbstractLiteral abstractLiteral;
	
	public ExpressionLiteral(final AbstractLiteral abstractLiteral) {
		this.abstractLiteral = abstractLiteral;
	}
	
	public final AbstractLiteral getLiteral(){
		return this.abstractLiteral;
	}
}
