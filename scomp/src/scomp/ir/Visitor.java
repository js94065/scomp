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
	 * @param assignment
	 * <br>Not null
	 */
	public abstract void visit(AssignmentStatement assignment);

	/**
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	public abstract void beginVisit(ReturnStatement returnStatement);
	
	/**
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	public abstract void endVisit(ReturnStatement returnStatement);
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void beginVisit(ArrayLocation location);
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void endVisit(ArrayLocation location);
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void beginVisit(LocationExpression location);
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	public abstract void endVisit(LocationExpression location);
	
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
	public abstract void beginVisit(MethodCallExpression methodCall);
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	public abstract void endVisit(MethodCallExpression methodCall);
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	public abstract void beginVisit(MethodCall methodCall);
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	public abstract void endVisit(MethodCall methodCall);
	
	/**
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	public abstract void beginVisit(IfStatement ifStatement);
	
	/**
	 * 
	 * @param whileStatement
	 * <br>Not null
	 */
	public abstract void beginVisit(WhileStatement whileStatement);
	
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

}
