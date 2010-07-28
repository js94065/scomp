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
	 * @param arrayFieldDeclaration
	 * <br>Not null
	 */
	public abstract void visit(ArrayFieldDeclaration arrayFieldDeclaration);
	
	/**
	 * 
	 * @param fieldDeclaration
	 * <br>Not null
	 */
	public abstract void visit(FieldDeclaration fieldDeclaration);
	
	/**
	 * 
	 * @param methodDeclaration
	 * <br>Not null
	 */
	public abstract void beginVisit(MethodDeclaration methodDeclaration);
	
	/**
	 * 
	 * @param methodDeclaration
	 * <br>Not null
	 */
	public abstract void endVisit(MethodDeclaration methodDeclaration);
	
	/**
	 * 
	 * @param parameterDeclaration
	 * <br>Not null
	 */
	public abstract void visit(ParameterDeclaration parameterDeclaration);
	
	/**
	 * 
	 * @param variableDeclaration
	 * <br>Not null
	 */
	public abstract void visit(VariableDeclaration variableDeclaration);
	
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
	
}
