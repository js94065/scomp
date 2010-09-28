package scomp.ir;

/**
 * This class offers methods to help visit the child nodes.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractVisitor implements Visitor {
	
	/**
	 * 
	 * @param program
	 * <br>Not null
	 */
	protected final void visitChildren(final Program program) {
		for (final AbstractFieldDeclaration fieldDeclaration : program.getFieldDeclarations()) {
			fieldDeclaration.accept(this);
		}
		
		for (final MethodDeclaration methodDeclaration : program.getMethodDeclarations()) {
			methodDeclaration.accept(this);
		}
	}
	
	/**
	 * 
	 * @param method
	 * <br>Not null
	 */
	protected final void visitChildren(final MethodDeclaration method) {
		for (final ParameterDeclaration parameterDeclaration : method.getParameterDeclarations()) {
			parameterDeclaration.accept(this);
		}
		
		method.getBlock().accept(this);
	}
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	protected final void visitChildren(final Block block) {
		for (final VariableDeclaration variableDeclaration : block.getVariableDeclarations()) {
			variableDeclaration.accept(this);
		}
		
		for (final AbstractStatement statement : block.getStatements()) {
			statement.accept(this);
		}
	}
	
	/**
	 * 
	 * @param block
	 * <br>Not null
	 */
	protected final void visitChildren(final BlockStatement block) {
		block.getBlock().accept(this);
	}
	
	/**
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	protected final void visitChildren(final AssignmentStatement assignment) {
		assignment.getLocation().accept(this);
		
		assignment.getExpression().accept(this);
	}
	
	/**
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	protected final void visitChildren(final ReturnStatement returnStatement) {
		if (returnStatement.getExpression() != null) {
			returnStatement.getExpression().accept(this);
		}
	}
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	protected final void visitChildren(final ArrayLocation location) {
		location.getOffset().accept(this);
	}
	
	/**
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	protected final void visitChildren(final IfStatement ifStatement) {
		ifStatement.getCondition().accept(this);
		
		ifStatement.getThenBlock().accept(this);
		
		if (ifStatement.getElseBlock() != null) {
			ifStatement.getElseBlock().accept(this);
		}
	}
	
	/**
	 * 
	 * @param whileStatement
	 * <br>Not null
	 */
	protected final void visitChildren(final WhileStatement whileStatement) {
		whileStatement.getCondition().accept(this);
		
		whileStatement.getBlock().accept(this);
	}
	
	/**
	 * 
	 * @param operation
	 * <br>Not null
	 */
	protected final void visitChildren(final BinaryOperationExpression operation) {
		operation.getLeft().accept(this);
		
		operation.getRight().accept(this);
	}
	
	/**
	 * 
	 * @param minusExpression
	 * <br>Not null
	 */
	protected final void visitChildren(final MinusExpression minusExpression) {
		minusExpression.getOperand().accept(this);
	}
	
	/**
	 * 
	 * @param negation
	 * <br>Not null
	 */
	protected final void visitChildren(final NegationExpression negation) {
		negation.getOperand().accept(this);
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	protected final void visitChildren(final MethodCallExpression methodCall) {
		methodCall.getMethodCall().accept(this);
	}
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	protected final void visitChildren(final LocationExpression location) {
		location.getLocation().accept(this);
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	protected final void visitChildren(final MethodCall methodCall) {
		for (final AbstractExpression argument : methodCall.getArguments()) {
			argument.accept(this);
		}
	}
	
	/**
	 * 
	 * @param methodCallStatement
	 * <br>Not null
	 */
	protected final void visitChildren(final MethodCallStatement methodCallStatement) {
		methodCallStatement.getMethodCall().accept(this);
	}
		
}
