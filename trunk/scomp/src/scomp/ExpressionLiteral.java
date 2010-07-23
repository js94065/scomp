package scomp;

public class ExpressionLiteral extends AbstractExpression{
	
	private final AbstractLiteral abstractLiteral;
	
	public ExpressionLiteral(final AbstractLiteral abstractLiteral) {
		this.abstractLiteral = abstractLiteral;
	}
	
	public final AbstractLiteral getLiteral(){
		return this.abstractLiteral;
	}
}
