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
	public abstract void beginVisit(Program program);
	
	/**
	 * 
	 * @param program
	 * <br>Not null
	 */
	public abstract void endVisit(Program program);
	
	/**
	 * 
	 * @param field
	 * <br>Not null
	 */
	public abstract void visit(ArrayFieldDeclaration field);
	
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
	public abstract void beginVisit(MethodDeclaration method);
	
	/**
	 * 
	 * @param method
	 * <br>Not null
	 */
	public abstract void endVisit(MethodDeclaration method);
	
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
	public abstract void beginVisit(Block block);
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	public abstract void endVisit(Block block);
	
	/**
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	public abstract void beginVisit(AssignmentStatement assignment);
	
	/**
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	public abstract void endVisit(AssignmentStatement assignment);
	
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
	 * @param operation
	 * <br>Not null
	 */
	public abstract void beginVisit(BinaryOperationExpression operation);
	
	/**
	 * 
	 * @param operation
	 * <br>Not null
	 */
	public abstract void endVisit(BinaryOperationExpression operation);
	
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
	
}
