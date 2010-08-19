package scomp.semantics;

import static scomp.Tools.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
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
 * @author Wilson
 * @author js94065
 *
 */
public final class SemanticRules implements Visitor {
	
	private final NestingDictionary<String, AbstractTypedEntityDeclaration> scopes;
	
	private final Logger logger;
	
	private String currentMethodName = "";
	
	@SuppressWarnings("unchecked")
	public SemanticRules() {
		this.scopes = new NestingDictionary<String, AbstractTypedEntityDeclaration>((Class<? extends Map<?, ?>>) LinkedHashMap.class);
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
		this.currentMethodName = method.getIdentifier();
		
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
		this.checkRule9(location);
	}
	
	@Override
	public final void visit(final IntLiteral literal) {
		// Do nothing
	}
	
	@Override
	public final void visit(final IfStatement ifStatement) {
		ifStatement.getCondition().accept(this);
		ifStatement.getThenBlock().accept(this);
		if (ifStatement.getElseBlock()!= null) {
			ifStatement.getElseBlock().accept(this);
		}
		
		this.checkRule11(ifStatement);
	}
	
	@Override
	public final void visit(final WhileStatement whileStatement) {
		for (AbstractStatement s: whileStatement.getBlock().getStatements()) {
			if (BreakStatement.class.equals(s.getClass())) {
				BreakStatement b = (BreakStatement) s;
				b.setLoop(s);
			}
			if (ContinueStatement.class.equals(s.getClass())) {
				ContinueStatement b = (ContinueStatement) s;
				b.setLoop(s);
			}
		}
		whileStatement.getCondition().accept(this);
		whileStatement.getBlock().accept(this);
		
		this.checkRule11(whileStatement);
	}
	
	@Override
	public final void visit(final BinaryOperationExpression operation) {
		// for locations and method calls we must get set the type using 
		// the scopes
		operation.getLeft().accept(this);
		operation.getRight().accept(this);
		
		this.checkRule12(operation);
		this.checkRule13(operation);
		this.checkRule14(operation);
	}
	
	@Override
	public final void visit(final MinusExpression minusExpression) {
		// visit child first, then we can use the child's
		// type as the expression's type.
		minusExpression.getExpression().accept(this);
		
		this.checkRule17(minusExpression);
	}
	
	@Override
	public final void visit(final NegationExpression operation) {
		// visit child first, then we can use the child's
		// type as the expression's type.
	    operation.getExpression().accept(this);
	    
		this.checkRule14(operation);
		
	}
	
	@Override
	public final void visit(final LiteralExpression literalExpression) {
		// Do nothing
	}
	
	@Override
	public final void visit(final MethodCallExpression methodCallExpression) {
		// Set MethodCallExpression types
		final String identifier = methodCallExpression.getMethodCall().getMethodName();
		
		if (this.getScopes().containsKey(identifier)) {
			methodCallExpression.setType(this.getScopes().get(identifier).getType());
		}
		
		this.checkRule6(methodCallExpression);
		
		methodCallExpression.getMethodCall().accept(this);
	}
	
	@Override
	public final void visit(final LocationExpression location) {
		// Set LocationExpression types
		final String identifier = location.getLocation().getIdentifier();
		
		if (this.getScopes().containsKey(identifier)) {
			location.setType(this.getScopes().get(identifier).getType());
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
	
	@Override
	public final void visit(final BreakStatement breakStatement) {
		checkRule16(breakStatement);
	}
	
	@Override
	public final void visit(final ContinueStatement continueStatement) {
		checkRule16(continueStatement);
	}
	
	@Override
	public final void visit(final BooleanLiteral booleanLiteral) {
		// Do nothing
	}
	
	@Override
	public final void visit(final CharLiteral charLiteral) {
		// Do nothing
	}
	
	@Override
	public final void visit(final ExpressionCalloutArgument expressionCalloutArgument) {
		// Do nothing
	}
	
	@Override
	public final void visit(final MethodCallStatement methodCallStatement) {
		methodCallStatement.getMethodCall().accept(this);
	}
	
	@Override
	public final void visit(final MethodCallout methodCallout) {
		// Do nothing
	}
	
	@Override
	public final void visit(final StringCalloutArgument stringCalloutArgument) {
		// Do nothing
	}
	
	/**
	 * Rule: No identifier is declared twice in the same scope.
	 * 
	 * @param entity
	 * <br>Not null
	 */
	private final void checkRule1(final AbstractTypedEntityDeclaration entity) {
		if (this.getScopes().containsLocalKey(entity.getIdentifier())) {
			this.logError(entity.getTokenRow(), entity.getTokenColumn(),
					"Duplicate identifier " + entity.getIdentifier());
		} else {
			this.getScopes().put(entity.getIdentifier(), entity);
		}
	}
	
	/**
	 * Rule: No identifier is used before it is declared.
	 * 
	 * @param location
	 * <br>Not null
	 */
	private final void checkRule2(final AbstractLocation location) {
		if (!this.getScopes().containsKey(location.getIdentifier())) {
			this.logError(location.getTokenRow(), location.getTokenColumn(),
					"Undeclared identifier " + location.getIdentifier());
		}
	}
	
	/**
	 * Rule: No identifier is used before it is declared.
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	private final void checkRule2(final MethodCall methodCall) {
		if (!this.getScopes().containsKey(methodCall.getMethodName())) {
			this.logError(methodCall.getMethodNameIdentifierRow(), methodCall.getMethodNameIdentifierColumn(),
					"Undeclared identifier " + methodCall.getMethodName());
		}
	}
	
	/**
	 * Rule: The program contains a definition for a method called main that has no parameters
	 * (note that since execution starts at method main, any methods defined after main will never be executed).
	 * 
	 * @param program
	 * <br>Not null
	 */
	private final void checkRule3(final Program program) {
		final MethodDeclaration mainMethod = cast(MethodDeclaration.class, this.getScopes().get("main"));
		
		if (mainMethod == null || mainMethod.getParameterDeclarations().size() != 0) {
			this.logError(program.getTokenRow(), program.getTokenColumn(),
					"Missing method main(<no argument>)");
		}
	}
	
	/**
	 * Rule: The &lt;int_literal> in an array declaration must be greater than 0.
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
	 * Rule: The number and types of arguments in a method call must be the same as the number and types of the formals
	 * i.e., the signatures must be identical.
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	private final void checkRule5(final MethodCall methodCall) {
		final String methodName = methodCall.getMethodName();
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getScopes().get(methodName));
		
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
	 * Rule: If a method call is used as an expression, the method must return a result.
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
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getScopes().get(methodName));
		
		if (method != null && method.getType().equals(void.class)) {
			this.logError(methodCall.getMethodNameIdentifierRow(), methodCall.getMethodNameIdentifierColumn(),
					"The method " + methodName + " has type void and cannot be used as an expression");
		}
	}
	
	/**
	 * Rule: A return statement must not have a return value unless it appears in the body of a method
	 * that is declared to return a value.
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	private final void checkRule7(final ReturnStatement returnStatement) {
		final AbstractTypedEntityDeclaration method = this.getScopes().get(this.currentMethodName);
		
		if (method.getType() == void.class && returnStatement.getExpression() != null) {
			this.logError(method.getTokenRow(), method.getTokenColumn(),
					"The method " + this.currentMethodName + " cannot have a return value");
		}
		
		if (method.getType() != void.class && returnStatement.getExpression() == null) {
			this.logError(method.getTokenRow(), method.getTokenColumn(),
					"The method " + this.currentMethodName + " needs to have a return value");
		}
	}
	
	/**
	 * Rule: The expression in a return statement must have the same type as the declared result type
	 * of the enclosing method definition.
	 * 
	 * @param returnStatement
	 * <br>Not null
	 */
	private final void checkRule8(final ReturnStatement returnStatement) {
		final AbstractTypedEntityDeclaration method = this.getScopes().get(this.currentMethodName);
		
		if (method.getType() != void.class && returnStatement.getExpression() != null) {
			if (method.getType() != returnStatement.getExpression().getType() ) {
				this.logError(method.getTokenRow(), method.getTokenColumn(),
						"The method " + this.currentMethodName + " return type does not match the return value");
			}
		}
	}
	
	/**
	 * Rule: An &lt;id> used as a &lt;location> must name a declared local/global variable or formal parameter.
	 * 
	 * @param identifierLocation
	 * <br>Not null
	 */
	private final void checkRule9(final IdentifierLocation identifierLocation) {
		final String identifier = identifierLocation.getIdentifier();
		
		if (identifier.equals("Program")) {
			this.logError(identifierLocation.getTokenRow(), identifierLocation.getTokenColumn(),
					"Cannot used the reserved identifier Program as a variable");
		}
		
		if (this.getScopes().containsKey(identifier)) {
			if (this.getScopes().get(identifier).getClass().equals(MethodDeclaration.class)) {
				this.logError(identifierLocation.getTokenRow(), identifierLocation.getTokenColumn(),
						"The method " + identifier + " cannot be used as a variable");
			}
		}
	}
	
	/**
	 * Rule: For all locations of the form &lt;id>[&lt;expr>]
	 * <br>(a) &lt;id> must be an array variable, and
	 * <br>(b) the type of &lt;expr> must be int.
	 * 
	 * @param location
	 * <br>Not null
	 */
	private final void checkRule10(final ArrayLocation location) {
		if (this.getScopes().containsKey(location.getIdentifier())) {
			if (!this.getScopes().get(location.getIdentifier()).getClass().equals(ArrayFieldDeclaration.class)) {
				this.logError(location.getTokenRow(), location.getTokenColumn(),
						"The variable " + location.getIdentifier() + " is not an array type");
			}
			
			/* TODO temporary solution when getOffset() returns a MethodCallExpression.getType() == null 
			 */ 
			if (location.getOffset().getClass().equals(MethodCallExpression.class)) {
				final String methodName = ((MethodCallExpression) location.getOffset()).getMethodCall().getMethodName();
				
				if (!this.getScopes().get(methodName).getType().equals(int.class)) {
					this.logError(location.getTokenRow(), location.getTokenColumn(),
							"The array offset is not an int type");
				}
			} else {
				if (!location.getOffset().getType().equals(int.class)) {
					this.logError(location.getTokenRow(), location.getTokenColumn(),
							"The array offset is not an int type");
				}
			}
		}
	}
	
	/*
	 * General comment about type checking.
	 * if type == null, then undeclared identifier (rule 2)
	 * if we see a violation of rule 2, then rule 2 takes precedence
	 * and we do not indicate error for current rule
	 */
	
	/**
	 * Rule: The &lt;expr> in if and while statements must have type boolean.
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	private final void checkRule11(final IfStatement ifStatement) {
		if (ifStatement.getCondition().getType() == null) {
			return;
		}
		
		if (!boolean.class.equals(ifStatement.getCondition().getType())) {
			this.logError(ifStatement.getTokenRow(), ifStatement.getTokenColumn(),
					"If condition should have type boolean.");
		}
	}
	
	
	/**
	 * Rule: The &lt;expr> in if and while statements must have type boolean.
	 * 
	 * @param whileStatement
	 * <br>Not null
	 */
	private final void checkRule11(final WhileStatement whileStatement) {
		if (whileStatement.getCondition().getType() == null) {
			return;
		}
		
		if (!boolean.class.equals(whileStatement.getCondition().getType())) {
			this.logError(whileStatement.getTokenRow(), whileStatement.getTokenColumn(),
					"While condition should have type boolean.");
		}
	}
	
	/**
	 * Rule: The operands of &lt;arith_op>s and &lt;rel_op>s must have type int.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule12(final BinaryOperationExpression operation) {
		if (operation.getOperator().equals("+") ||
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
			// first if is for rule 2 compatibility
			// second if is for the actual type check 
			if (operation.getLeft().getType() != null) {
				if (!operation.getLeft().getType().equals(int.class)) {
					this.logError(operation.getLeft().getTokenRow(), operation.getLeft().getTokenColumn(),
							"Operand of arithmetic and relational operations must have type int.");
				}
			}
			
			if (operation.getRight().getType() != null) {
				if (!operation.getRight().getType().equals(int.class)) {
					this.logError(operation.getRight().getTokenRow(), operation.getRight().getTokenColumn(),
							"Operand of arithmetic and relational operations must have type int.");
				}
			}
		}
	}
	
	
	/**
	 * Rule: The operands of &lt;eq_op>s must have the same type, either int or boolean.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule13(final BinaryOperationExpression operation) {
		if (operation.getLeft().getType() == null || operation.getRight().getType() == null) {
			return;
		}
		
		if (operation.getOperator().equals("==") ||
				operation.getOperator().equals("!=")) {
			if (int.class.equals(operation.getLeft().getType()) && 
					int.class.equals(operation.getRight().getType())) {
				// do nothing
			} else if (boolean.class.equals(operation.getLeft().getType()) && 
					boolean.class.equals(operation.getRight().getType())) {
				// do nothing
			} else {
				this.logError(operation.getTokenRow(), operation.getTokenColumn(),
						"Operand of equality operations must have same type, " +
						"either int or boolean.");
			}
		}
	}
	
	/**
	 * Rule: The operands of &lt;cond_op>s and the operand of logical not (!) must have type boolean.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule14(final BinaryOperationExpression operation) {
		if (operation.getOperator().equals("&&") ||
				operation.getOperator().equals("||")) {
			// first if is for rule 2 compatibility
			// second if is for the actual type check 
			if (operation.getLeft().getType() != null) {
				if (!boolean.class.equals(operation.getLeft().getType()) ) {
					this.logError(operation.getLeft().getTokenRow(), operation.getLeft().getTokenColumn(),
							"Operand of conditional operations must have type boolean.");
				} 
			}
			
			if (operation.getRight().getType() != null) {
				if (!boolean.class.equals(operation.getRight().getType()) ) { 
					this.logError(operation.getRight().getTokenRow(), operation.getRight().getTokenColumn(),
							"Operand of conditional operations must have type boolean.");
				}
			}
		}
	}
	
	/**
	 * Rule: The operands of &lt;cond_op>s and the operand of logical not (!) must have type boolean.
	 * 
	 * @param negation
	 * <br>Not null
	 */
	private final void checkRule14(final NegationExpression negation) {
		if (negation.getExpression().getType() == null) {
			return;
		}
		
		if (!boolean.class.equals(negation.getExpression().getType())) {
			this.logError(negation.getTokenRow(), negation.getTokenColumn(),
					"Operand of negation operations must have type boolean.");
		}
	}
	
	/**
	 * Rule: The &lt;location> and the &lt;expr> in an assignment, &lt;location> = &lt;expr>, must have the same type.
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	private final void checkRule15(final AssignmentStatement assignment) {
		Class<?> locationType = null;
		final Class<?> expressionType;
		
		if (this.getScopes().containsKey(assignment.getLocation().getIdentifier())) {
			locationType = this.getScopes().get(assignment.getLocation().getIdentifier()).getType();
		}
		
		expressionType = assignment.getExpression().getType();
		
		if (locationType == null || expressionType == null) {
			// if null -> rule 2 violation 
			return;
		}
		
		if (!locationType.equals(expressionType)) {
			this.logError(assignment.getTokenRow(), assignment.getTokenColumn(),
					"Assignment location and expression have different types.");
		}
	}
	
	/**
	 * Rule: All break and continue statements must be contained within the body of a loop.
	 * 
	 * @param breakStatement
	 * <br>Not null
	 */
	private final void checkRule16(final BreakStatement breakStatement) {
		if (breakStatement.getLoop() == null) {
			this.logError(breakStatement.getTokenRow(), breakStatement.getTokenColumn(),
					"Break statement is not contained within the body of a loop.");
		}
	}
	
	/**
	 * Rule: All break and continue statements must be contained within the body of a loop.
	 * 
	 * @param continueStatement
	 * <br>Not null
	 */
	private final void checkRule16(final ContinueStatement continueStatement) {
		if (continueStatement.getLoop() == null) {
			this.logError(continueStatement.getTokenRow(), continueStatement.getTokenColumn(),
					"Continue statement is not contained within the body of a loop.");
		}
	}
	
	/**
	 * Rule: The operand of the unary minus (-) must have type int.
	 * 
	 * @param minusExpression
	 * <br>Not null
	 */
	private final void checkRule17(final MinusExpression minusExpression) {
		if (!minusExpression.getExpression().getType().equals(int.class)) {
			this.logError(minusExpression.getTokenRow(), minusExpression.getTokenColumn(),
					"Unary minus expression is not an int type");
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
		this.getScopes().push();
	}
	
	private final void popCurrentScope() {
		this.getScopes().pop();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	private final NestingDictionary<String, AbstractTypedEntityDeclaration> getScopes() {
		return this.scopes;
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
	
	/**
	 * 
	 * @author codistmonk (creation 2010-08-20)
	 *
	 * @param <K> The type of the keys
	 * @param <V> The type of the values
	 */
	public static final class NestingDictionary<K, V> {
		
		private final LinkedList<Map<K, V>> maps;
		
		private final Class<? extends Map<K, V>> mapClass;
		
		/**
		 * 
		 * @param mapClass
		 * <br>Not null
		 * <br>Shared
		 * @throws RuntimeException If a new instance cannot be created using {@code this.mapClass}
		 */
		@SuppressWarnings("unchecked")
		public NestingDictionary(final Class<? extends Map<?, ?>> mapClass) {
			this.maps = new LinkedList<Map<K,V>>();
			this.mapClass = (Class<? extends Map<K, V>>) mapClass;
			
			this.push();
		}
		
		/**
		 * 
		 * @throws RuntimeException If a new instance cannot be created using {@code this.mapClass}
		 */
		public final void push() {
			this.maps.push(newInstance(this.mapClass));
		}
		
		public final void pop() {
			this.maps.pop();
		}
		
		/**
		 * 
		 * @param key
		 * <br>Maybe null
		 * @return
		 * <br>Range: any boolean
		 */
		public final boolean containsLocalKey(final K key) {
			return this.maps.get(0).containsKey(key);
		}
		
		/**
		 * 
		 * @param key
		 * <br>Maybe null
		 * @return
		 * <br>Range: any boolean
		 */
		public final boolean containsKey(final K key) {
			for (final Map<K, V> map : this.maps) {
				if (map.containsKey(key)) {
					return true;
				}
			}
			
			return false;
		}
		
		/**
		 * 
		 * @param key
		 * <br>Maybe null
		 * @return The value to which {@code key} is mapped, or {@code null} if the the local map contains no mapping for the key
		 * <br>Maybe null
		 * <br>Shared
		 */
		public final V getLocal(final K key) {
			return this.maps.get(0).get(key);
		}
		
		/**
		 * 
		 * @param key
		 * <br>Maybe null
		 * @return The value to which {@code key} is mapped, or {@code null} if there is no mapping for the key
		 * <br>Maybe null
		 * <br>Shared
		 */
		public final V get(final K key) {
			for (final Map<K, V> map : this.maps) {
				if (map.containsKey(key)) {
					return map.get(key);
				}
			}
			
			return null;
		}
		
		/**
		 * 
		 * @param key
		 * <br>Maybe null
		 * @param value
		 * <br>Maybe null
		 * <br>Shared
		 */
		public final void put(final K key, final V value) {
			this.maps.peek().put(key, value);
		}
		
		/**
		 * 
		 * @param <T> The result type
		 * @param cls
		 * <br>Not null
		 * @return
		 * <br>Not null
		 * <br>New
		 * @throws RuntimeException If a new instance cannot be created
		 */
		private static final <T> T newInstance(final Class<T> cls) {
			try {
				return cls.newInstance();
			} catch (final Exception exception) {
				throw unchecked(exception);
			}
		}
		
	}
	
}
