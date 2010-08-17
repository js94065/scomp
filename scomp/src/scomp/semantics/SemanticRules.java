package scomp.semantics;

import static scomp.Tools.*;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import scomp.DecafParser;
import scomp.ir.AbstractExpression;
import scomp.ir.AbstractFieldDeclaration;
import scomp.ir.AbstractLocation;
import scomp.ir.AbstractStatement;
import scomp.ir.AbstractTypedEntityDeclaration;
import scomp.ir.ArrayFieldDeclaration;
import scomp.ir.ArrayLocation;
import scomp.ir.AssignmentStatement;
import scomp.ir.BinaryOperationExpression;
import scomp.ir.Block;
import scomp.ir.BlockStatement;
import scomp.ir.BooleanLiteral;
import scomp.ir.BreakStatement;
import scomp.ir.CharLiteral;
import scomp.ir.ContinueStatement;
import scomp.ir.ExpressionCalloutArgument;
import scomp.ir.FieldDeclaration;
import scomp.ir.IdentifierLocation;
import scomp.ir.IfStatement;
import scomp.ir.IntLiteral;
import scomp.ir.LiteralExpression;
import scomp.ir.LocationExpression;
import scomp.ir.MethodCall;
import scomp.ir.MethodCallExpression;
import scomp.ir.MethodCallStatement;
import scomp.ir.MethodCallout;
import scomp.ir.MethodDeclaration;
import scomp.ir.MinusExpression;
import scomp.ir.NegationExpression;
import scomp.ir.ParameterDeclaration;
import scomp.ir.Program;
import scomp.ir.ReturnStatement;
import scomp.ir.StringCalloutArgument;
import scomp.ir.VariableDeclaration;
import scomp.ir.Visitor;
import scomp.ir.WhileStatement;

/**
 * This is the visitor responsible for checking the Decaf semantic rules.
 * 
 * @author codistmonk (creation 2010-07-28)
 *
 */
public final class SemanticRules implements Visitor {
	
	private final Deque<Map<String, AbstractTypedEntityDeclaration>> scopes;
	
	private final Logger logger;
	
	private String nameOfMethod = "";
	
	public SemanticRules() {
		this.scopes = new LinkedList<Map<String,AbstractTypedEntityDeclaration>>();
		this.logger = Logger.getLogger(DecafParser.class.getName());
	}
	
	@Override 
	public final void visit(final Program program) {
		this.pushNewScope();
		
		for (final AbstractFieldDeclaration fieldDeclaration : program.getFieldDeclarations()) {
			fieldDeclaration.accept(this);
		}
		
		for (final MethodDeclaration methodDeclaration : program.getMethodDeclarations()) {
			methodDeclaration.accept(this);
		}
		
		this.checkRule3(program);
		
		this.popCurrentScope();
	}
	
	@Override
	public final void visit(final ArrayFieldDeclaration field) {
		this.checkRule1(field);
		this.checkRule4(field);
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		this.checkRule1(field);
	}
	
	@Override
	public final void visit(final MethodDeclaration method) {
		this.nameOfMethod = method.getIdentifier();
		this.checkRule1(method);
		
		this.pushNewScope();
		
		for (final ParameterDeclaration parameterDeclaration : method.getParameterDeclarations()) {
			parameterDeclaration.accept(this);
		}
		
		method.getBlock().accept(this);
		
		this.popCurrentScope();
	}
	
	@Override
	public final void visit(final ParameterDeclaration parameter) {
		this.checkRule1(parameter);
	}
	
	@Override
	public final void visit(final VariableDeclaration variable) {
		this.checkRule1(variable);
	}
	
	@Override
	public final void visit(final Block block) {
		this.pushNewScope();
		
		for (final VariableDeclaration variableDeclaration : block.getVariableDeclarations()) {
			variableDeclaration.accept(this);
		}
		
		for (final AbstractStatement statement : block.getStatements()) {
			statement.accept(this);
		}
		
		this.popCurrentScope();
	}
	
	@Override
	public final void visit(final BlockStatement block) {
		block.getBlock().accept(this);
	}
	
	
	@Override
	public final void visit(final AssignmentStatement assignment) {
		assignment.getLocation().accept(this);
		assignment.getExpression().accept(this);
		
		checkRule15(assignment);
	}
	
	@Override
	public final void visit(final ReturnStatement returnStatement) {
		this.checkRule7(returnStatement);
		this.checkRule8(returnStatement);
		
		if (returnStatement.getExpression() != null) {
			returnStatement.getExpression().accept(this);
		}
		
	}
	
	@Override
	public void visit(final ArrayLocation location) {
		this.checkRule2(location);
		this.checkRule10(location);
		
		location.getOffset().accept(this);
	}

	@Override
	public final void visit(final IdentifierLocation location) {
		this.checkRule2(location);
	}
	
	@Override
	public final void visit(final IntLiteral literal) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void visit(final IfStatement ifStatement) {
		this.checkRule11(ifStatement);
		
		ifStatement.getCondition().accept(this);
		ifStatement.getThenBlock().accept(this);
		if (ifStatement.getElseBlock()!= null) {
			ifStatement.getElseBlock().accept(this);
		}
		
	}
	
	@Override
	public final void visit(final WhileStatement whileStatement) {
		this.checkRule11(whileStatement);
		
		whileStatement.getCondition().accept(this);
		whileStatement.getBlock().accept(this);
	}
	
	@Override
	public final void visit(final BinaryOperationExpression operation) {
		// for locations and method calls we must get set the type using 
		// the scopes
		if (operation.getLeft()==null || operation.getRight() == null) {
			System.out.println("BinaryOperationExpression has null arguments");
		}

		operation.getLeft().accept(this);
		operation.getRight().accept(this);
		
		this.checkRule12(operation);
		this.checkRule13(operation);
		this.checkRule14(operation);
		
	}
	

	@Override
	public void visit(MinusExpression minusExpression) {
		
		// visit child first, then we can use the child's
		// type as the expression's type.
		minusExpression.getExpression().accept(this);
		
	}
	
	@Override
	public final void visit(final NegationExpression operation) {
		if (operation.getExpression()==null) {
            System.out.println("NegationExpression has null arguments");
	    }
	    
		// visit child first, then we can use the child's
		// type as the expression's type.
		
	    operation.getExpression().accept(this);
	    
		this.checkRule14(operation);
		
	}
	
	@Override
	public void visit(LiteralExpression literalExpression) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCallExpression methodCallExpression) {
		
		// Set MethodCallExpression types
		String identifier = methodCallExpression.getMethodCall().getMethodName();
		if (this.getCurrentScope().containsKey(identifier)) {
			methodCallExpression.setType(this.getCurrentScope().get(identifier).getType());
		}
		
		this.checkRule6(methodCallExpression);
		
		methodCallExpression.getMethodCall().accept(this);
	}
	
	
	@Override
	public final void visit(final LocationExpression location) {
		
		// Set LocationExpression types
		String identifier = location.getLocation().getIdentifier();
		if (this.getCurrentScope().containsKey(identifier)) {
			location.setType(this.getCurrentScope().get(identifier).getType());
		}
		
		location.getLocation().accept(this);
	}
	
	@Override
	public final void visit(final MethodCall methodCall) {
		this.checkRule2(methodCall);
		this.checkRule5(methodCall);
		
		for (final AbstractExpression argument : methodCall.getArguments()) {
			argument.accept(this);
		}
	}
	
	
	/**
	 * 
	 * @param entity
	 * <br>Not null
	 */
	private final void checkRule1(final AbstractTypedEntityDeclaration entity) {
		if (this.getCurrentScope().containsKey(entity.getIdentifier())) {
			this.logError(entity.getTokenRow(), entity.getTokenColumn(),
					"Duplicate identifier " + entity.getIdentifier());
		} else {
			this.getCurrentScope().put(entity.getIdentifier(), entity);
		}
	}
	
	/**
	 * 
	 * @param location
	 * <br>Not null
	 */
	private final void checkRule2(final AbstractLocation location) {
		if (!this.getCurrentScope().containsKey(location.getIdentifier())) {
			this.logError(location.getTokenRow(), location.getTokenColumn(),
					"Undeclared identifier " + location.getIdentifier());
		}
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	private final void checkRule2(final MethodCall methodCall) {
		if (!this.getCurrentScope().containsKey(methodCall.getMethodName())) {
			this.logError(methodCall.getMethodNameIdentifierRow(), methodCall.getMethodNameIdentifierColumn(),
					"Undeclared identifier " + methodCall.getMethodName());
		}
	}
	
	/**
	 * 
	 * @param program
	 * <br>Not null
	 */
	private final void checkRule3(final Program program) {
		final MethodDeclaration mainMethod = cast(MethodDeclaration.class, this.getCurrentScope().get("main"));
		
		if (mainMethod == null || mainMethod.getParameterDeclarations().size() != 0) {
			this.logError(program.getTokenRow(), program.getTokenColumn(),
					"Missing method main(<no argument>)");
		}
	}
	
	/**
	 * 
	 * @param field
	 * <br>Not null
	 */
	private final void checkRule4(final ArrayFieldDeclaration field) {
		if (field.getElementCount().getValue() <= 0) {
			this.logError(field.getElementCount().getTokenRow(), field.getElementCount().getTokenColumn(),
					"Array size must be greater than 0");
		}
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	private final void checkRule5(final MethodCall methodCall) {
		final String methodName = methodCall.getMethodName();
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getCurrentScope().get(methodName));
		
		if (method != null) {
			final String expectedSignature = getSignature(method.getParameterDeclarations());
			final String actualSignature = getSignature(methodCall.getArguments());
			
			if (!expectedSignature.equals(actualSignature)) {
				this.logError(methodCall.getMethodNameIdentifierRow(), methodCall.getMethodNameIdentifierColumn(),
						"The method " + methodName + expectedSignature +
						" is not applicable for the arguments " + actualSignature);
			}
		}
	}
	
	/**
	 * 
	 * @param methodCallExpression
	 * <br>Not null
	 */
	private final void checkRule6(final MethodCallExpression methodCallExpression) {
		final MethodCall methodCall = cast(MethodCall.class, methodCallExpression.getMethodCall());
		
		if (methodCall == null) {
			return;
		}
		
		final String methodName = methodCall.getMethodName();
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getCurrentScope().get(methodName));
		
		if (method != null && method.getType().equals(void.class)) {
			this.logError(methodCall.getMethodNameIdentifierRow(), methodCall.getMethodNameIdentifierColumn(),
					"The method " + methodName + " has type void and cannot be used as an expression");
		}
	}
	
	/**
	 * 
	 * @param returnStatement
	 * <br> not null
	 */
	private final void checkRule7(final ReturnStatement returnStatement) {
		AbstractTypedEntityDeclaration method = this.getCurrentScope().get(this.nameOfMethod);
		
		if( (method.getType() == void.class) && (returnStatement.getExpression() != null) ) {
			this.logError(method.getTokenRow(), method.getTokenColumn(),
					"The method " + this.nameOfMethod + " cannot have a return value");
		}
	}
	
	/**
	 * 
	 * @param returnStatement
	 * <br> not null
	 */
	private final void checkRule8(final ReturnStatement returnStatement) {
		AbstractTypedEntityDeclaration method = this.getCurrentScope().get(this.nameOfMethod);
		
		if( (method.getType() != void.class) && (returnStatement.getExpression() != null) ) {
			if( method.getType() != returnStatement.getExpression().getType() ) {
				this.logError(method.getTokenRow(), method.getTokenColumn(),
						"The method " + this.nameOfMethod + " return type does not match the return value");
			}
		}
	}
	
	/**
	 * Rule 9 exactly like Rule 2
	 */
	
	/**
	 * @param location
	 * <br> not null
	 */
	private final void checkRule10(ArrayLocation location) {
		if(!this.getCurrentScope().get(location.getIdentifier()).getType().equals(ArrayLocation.class)) {
			this.logError(location.getTokenRow(), location.getTokenColumn(),
					"The variable " + location.getIdentifier() + " is not an array type");
		}
		
		if(!location.getOffset().getType().equals(int.class)) {
			this.logError(location.getTokenRow(), location.getTokenColumn(),
					"The array offset is not an int type for array variable " + location.getIdentifier());
		}
	}
	
	
	/**
	 * 
	 * @param ifStatement
	 * <br> not null
	 */
	private final void checkRule11(final IfStatement ifStatement) {
		if (!ifStatement.getCondition().getType().equals(boolean.class)) {
			this.logError("If condition should have type boolean.");
		}
	}
	
	
	/**
	 * 
	 * @param whileStatement
	 * <br> not null
	 */
	private final void checkRule11(final WhileStatement whileStatement) {
		if (!whileStatement.getCondition().getType().equals(boolean.class)) {
			this.logError("While condition should have type boolean.");
		}
	}
	
	/*
	 * General comment about type checking.
	 * if type == null, then undeclared identifier (rule 2)
	 * if we see a violation of rule 2, then rule 2 takes precedence
	 * and we do not indicate error for current rule
	 */
	
	/**
	 * 
	 * @param operation
	 * <br> not null
	 */
	private final void checkRule12(BinaryOperationExpression operation) {
		if ( operation.getOperator().equals("+") ||
				operation.getOperator().equals("-") ||
				operation.getOperator().equals("*") ||
				operation.getOperator().equals("/") ||
				operation.getOperator().equals("%") ||
				operation.getOperator().equals("<<") ||
				operation.getOperator().equals(">>") ||
				operation.getOperator().equals(">>>") || 
				operation.getOperator().equals("<") ||
				operation.getOperator().equals(">") ||
				operation.getOperator().equals("<=") ||
				operation.getOperator().equals(">=") 
			) {
			if (operation.getLeft().getType() != null) {
				if (!operation.getLeft().getType().equals(int.class)) {
					this.logError("Operand of arithmetic and relational operations must " +
							"have type int.");
				}
			}
			// left and right have distinct error locations
			// update later with actual positions
			if (operation.getRight().getType() != null) {
				if (!operation.getRight().getType().equals(int.class)) {
					this.logError("Operand of arithmetic and relational operations must " +
							"have type int.");
				}
			}

		}
	}
	
	
	/**
	 * 
	 * @param operation
	 * <br> not null
	 */
	private final void checkRule13(BinaryOperationExpression operation) {
		if ( operation.getOperator().equals("==") ||
				operation.getOperator().equals("!=") ) {
			if (operation.getLeft().getType()==null || operation.getRight().getType()==null) {
				return;
			}
			if (operation.getLeft().getType().equals(int.class) && 
					operation.getRight().getType().equals(int.class)) {
				// do nothing
			} 
			else if (operation.getLeft().getType().equals(boolean.class) && 
					operation.getRight().getType().equals(boolean.class)) {
				// do nothing
			} else {
				this.logError("Operand of equality operator must have same type, " +
						"either int or boolean.");
			}
		}
	}
	
	/**
	 * 
	 * @param operation
	 * <br> not null
	 */
	private final void checkRule14(BinaryOperationExpression operation) {
		if ( operation.getOperator().equals("&&") ||
				operation.getOperator().equals("||") ) {
			if (operation.getLeft().getType()!=null) {
				if (!operation.getLeft().getType().equals(boolean.class) ) {
					this.logError("Operand of conditional operations and logical not must have type boolean.");
				} 
			}
			// left and right have distinct error locations
			// update later with actual positions
			if (operation.getRight().getType()!=null) {
				if (!operation.getRight().getType().equals(boolean.class) ) { 
					this.logError("Operand of conditional operations and logical not must have type boolean.");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param negation
	 * <br> not null
	 */
	private final void checkRule14(NegationExpression negation) {
		if (!negation.getExpression().getType().equals(boolean.class)) {
			this.logError("Operand of conditional operations and logical not must have type boolean.");
		}
		else if (!negation.getType().equals(boolean.class)) {
			this.logError("Operand of conditional operations and logical not must have type boolean.");
		}
	}
	
	/**
	 * 
	 * @param assignment
	 * <br> not null
	 */
	private final void checkRule15(AssignmentStatement assignment) {
		Class<?> locationType = null;
		Class<?> expressionType = null;
		if (this.getCurrentScope().containsKey(assignment.getLocation().getIdentifier())) {
			locationType = this.getCurrentScope().get(assignment.getLocation().getIdentifier()).getType();
		}
		expressionType = assignment.getExpression().getType();
		
		if (locationType==null || expressionType == null) {
			// if null -> rule 2 violation 
			return;
		}
		else if (!locationType.equals(expressionType)) {
			this.logError("Assignment location and expression have different types");
		}
		
	}
	
	/**
	 * 
	 * @param row
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param column 
	 * <br>Range: {@code [1 .. Integer.MAX_VALUE]}
	 * @param message
	 * <br>Maybe null
	 */
	private final void logError(final int row, final int column, final String message) {
		this.logError("(:" + row + ":" + column + ") " + message);
	}
	
	/**
	 * 
	 * @param message
	 * <br>Maybe null
	 */
	private final void logError(final String message) {
		this.logger.log(Level.SEVERE, message);
	}
	
	private final void pushNewScope() {
		if (this.scopes.isEmpty()) {
			this.scopes.push(new LinkedHashMap<String, AbstractTypedEntityDeclaration>());
		} else {
			this.scopes.push(new LinkedHashMap<String, AbstractTypedEntityDeclaration>(this.getCurrentScope()));
		}
	}
	
	private final void popCurrentScope() {
		this.scopes.pop();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	private final Map<String, AbstractTypedEntityDeclaration> getCurrentScope() {
		return this.scopes.peek();
	}
	
	/**
	 * Returns a string similar to "(int, boolean)", or "()" if {@code parametersOrArguments} is empty.
	 * 
	 * @param parametersOrArguments
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final String getSignature(final Iterable<?> parametersOrArguments) {
		return "(" + join(",", invoke(invoke(parametersOrArguments, "getType"), "getSimpleName")) + ")";
	}
	

	@Override
	public void visit(BooleanLiteral booleanLiteral) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BreakStatement breakStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CharLiteral charLiteral) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ContinueStatement continueStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpressionCalloutArgument expressionCalloutArgument) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MethodCallStatement methodCallStatement) {
		methodCallStatement.getMethodCall().accept(this);
		
	}

	@Override
	public void visit(MethodCallout methodCallout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringCalloutArgument stringCalloutArgument) {
		// TODO Auto-generated method stub
		
	}
	
}
