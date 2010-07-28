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
	public abstract void visit(MethodDeclaration methodDeclaration);
	
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
	 * @param blockStatement
	 * <br>Not null
	 */
	public abstract void visit(BlockStatement blockStatement);
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	public abstract void visit(Block block);
	
}
