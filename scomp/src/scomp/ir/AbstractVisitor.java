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
	protected void visitChildren(final Program program) {
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
	protected void visitChildren(final MethodDeclaration method) {
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
	protected void visitChildren(final Block block) {
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
	protected void visitChildren(final BlockStatement block) {
		block.getBlock().accept(this);
	}
	
	/**
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	protected void visitChildren(final AssignmentStatement assignment) {
		assignment.getLocation().accept(this);
		
		assignment.getExpression().accept(this);
	}
	
	/**
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	protected void visitChildren(final ReturnStatement returnStatement) {
		if (returnStatement.getExpression() != null) {
			returnStatement.getExpression().accept(this);
		}
	}
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	protected void visitChildren(final ArrayLocation location) {
		location.getOffset().accept(this);
	}
	
	/**
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	protected void visitChildren(final IfStatement ifStatement) {
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
	protected void visitChildren(final WhileStatement whileStatement) {
		whileStatement.getCondition().accept(this);
		
		whileStatement.getBlock().accept(this);
	}
	
	/**
	 * 
	 * @param operation
	 * <br>Not null
	 */
	protected void visitChildren(final BinaryOperationExpression operation) {
		operation.getLeft().accept(this);
		
		operation.getRight().accept(this);
	}
	
	/**
	 * 
	 * @param minusExpression
	 * <br>Not null
	 */
	protected void visitChildren(final MinusExpression minusExpression) {
		minusExpression.getOperand().accept(this);
	}
	
	/**
	 * 
	 * @param negation
	 * <br>Not null
	 */
	protected void visitChildren(final NegationExpression negation) {
		negation.getOperand().accept(this);
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	protected void visitChildren(final MethodCallExpression methodCall) {
		methodCall.getMethodCall().accept(this);
	}
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	protected void visitChildren(final LocationExpression location) {
		location.getLocation().accept(this);
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	protected void visitChildren(final MethodCall methodCall) {
		for (int i = methodCall.getArguments().size() - 1; i >= 0; --i) {
			methodCall.getArguments().get(i).accept(this);
		}
	}
	
	/**
	 * 
	 * @param methodCallStatement
	 * <br>Not null
	 */
	protected void visitChildren(final MethodCallStatement methodCallStatement) {
		methodCallStatement.getMethodCall().accept(this);
	}
	
	/**
	 * 
	 * @param expressionCalloutArgument
	 * <br>Not null
	 */
	protected void visitChildren(final ExpressionCalloutArgument expressionCalloutArgument) {
		expressionCalloutArgument.getExpression().accept(this);
	}
	
	/**
	 * 
	 * @param methodCallout
	 * <br>Not null
	 */
	protected void visitChildren(final MethodCallout methodCallout) {
		for (int i = methodCallout.getArguments().size() - 1; i >= 0; --i) {
			methodCallout.getArguments().get(i).accept(this);
		}
	}
	
}
