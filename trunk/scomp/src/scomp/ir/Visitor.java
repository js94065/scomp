package scomp.ir;

/**
 * 
 * @author codistmonk (creation 2010-07-28)
 *
 */
public interface Visitor {
	
	/**
	 * 
	 * @param program
	 * <br>Not null
	 */
	public abstract void visit(Program program);
	
	/**
	 * 
	 * @param field
	 * <br>Not null
	 */
	public abstract void visit(ArrayFieldDeclaration field);
	
	/**
	 * 
	 * @param literal
	 * <br>Not null
	 */
	public abstract void visit(IntLiteral literal);
	
	/**
	 * 
	 * @param field
	 * <br>Not null
	 */
	public abstract void visit(FieldDeclaration field);
	
	/**
	 * 
	 * @param method
	 * <br>Not null
	 */
	public abstract void visit(MethodDeclaration method);
	
	/**
	 * 
	 * @param parameter
	 * <br>Not null
	 */
	public abstract void visit(ParameterDeclaration parameter);
	
	/**
	 * 
	 * @param variable
	 * <br>Not null
	 */
	public abstract void visit(VariableDeclaration variable);
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	public abstract void visit(Block block);
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	public abstract void visit(BlockStatement block);
	
	/**
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	public abstract void visit(AssignmentStatement assignment);

	/**
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	public abstract void visit(ReturnStatement returnStatement);

	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void visit(ArrayLocation location);

	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void visit(LocationExpression location);

	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void visit(IdentifierLocation location);
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	public abstract void visit(MethodCallExpression methodCall);
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	public abstract void visit(MethodCall methodCall);
	
	/**
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	public abstract void visit(IfStatement ifStatement);
	
	/**
	 * 
	 * @param whileStatement
	 * <br>Not null
	 */
	public abstract void visit(WhileStatement whileStatement);
	
	/**
	 * 
	 * @param operation
	 * <br>Not null
	 */
	public abstract void visit(BinaryOperationExpression operation);
	
	/**
	 * 
	 * @param negation
	 * <br>Not null
	 */
	public abstract void visit(NegationExpression negation);

	public abstract void visit(BooleanLiteral booleanLiteral);

	public abstract void visit(BreakStatement breakStatement);

	public abstract void visit(CharLiteral charLiteral);

	public abstract void visit(ContinueStatement continueStatement);

	public abstract void visit(ExpressionCalloutArgument expressionCalloutArgument);

	public abstract void visit(LiteralExpression literalExpression);

	public abstract void visit(MethodCallStatement methodCallStatement);

	public abstract void visit(MethodCallout methodCallout);

	public abstract void visit(MinusExpression minusExpression);

	public abstract void visit(StringCalloutArgument stringCalloutArgument);

}
