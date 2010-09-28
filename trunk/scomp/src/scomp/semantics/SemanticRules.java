package scomp.semantics;

import static scomp.Tools.*;
import static scomp.ir.BinaryOperationExpression.EQUALITY_OPERATORS;
import static scomp.ir.BinaryOperationExpression.OPERATORS_WITH_INT_OPERANDS;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import scomp.DecafParser;
import scomp.ir.AbstractExpression;
import scomp.ir.AbstractLocation;
import scomp.ir.AbstractNode;
import scomp.ir.AbstractTypedEntityDeclaration;
import scomp.ir.AbstractVisitor;
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
import scomp.ir.WhileStatement;

/**
 * This is the visitor responsible for checking the Decaf semantic rules.
 * 
 * @author codistmonk (creation 2010-07-28)
 * @author Wilson
 * @author js94065
 *
 */
public final class SemanticRules extends AbstractVisitor {
	
	private final NestingDictionary<String, AbstractNode> scope;
	
	private final Logger logger;
	
	private String currentMethodName;
	
	private Class<?> currentMethodType;
	
	private boolean methodScope;
	
	private int loopCount;
	
	@SuppressWarnings("unchecked")
	public SemanticRules() {
		this.scope = new NestingDictionary<String, AbstractNode>((Class<? extends Map<?, ?>>) LinkedHashMap.class);
		this.logger = Logger.getLogger(DecafParser.class.getName());
		this.currentMethodName = "";
		this.methodScope = false;
		this.loopCount = 0;
	}
	
	@Override 
	public final void visit(final Program program) {
		this.getScope().put("Program", program);
		
		this.visitChildren(program);
		
		this.checkRule3(program);
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
		this.currentMethodType = method.getType();
		
		this.checkRule1(method);
		
		// The method scope and the method block scope are merged
		this.pushNewScope(true);
		
		this.visitChildren(method);
		
		// No need to pop the current scope, because it is done
		// in the method block
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
		this.pushNewScope(false);
		
		this.visitChildren(block);
		
		this.popCurrentScope();
	}
	
	@Override
	public final void visit(final BlockStatement block) {
		this.visitChildren(block);
	}
	
	@Override
	public final void visit(final AssignmentStatement assignment) {
		this.visitChildren(assignment);
		
		checkRule15(assignment);
	}
	
	@Override
	public final void visit(final ReturnStatement returnStatement) {
		this.checkRule7(returnStatement);
		this.checkRule8(returnStatement);
		
		this.visitChildren(returnStatement);
	}
	
	@Override
	public void visit(final ArrayLocation location) {
		this.checkRule2(location);
		
		this.visitChildren(location);
		
		this.checkRule10(location);
	}
	
	@Override
	public final void visit(final IdentifierLocation location) {
		this.checkRule2(location);
		this.checkRule9(location);
		
		location.setDeclaration((AbstractTypedEntityDeclaration) this.getScope().get(location.getIdentifier()));
	}
	
	@Override
	public final void visit(final IntLiteral literal) {
		// Do nothing
	}
	
	@Override
	public final void visit(final IfStatement ifStatement) {
		this.visitChildren(ifStatement);
		
		this.checkRule11(ifStatement);
	}
	
	@Override
	public final void visit(final WhileStatement whileStatement) {
		++this.loopCount;
		
		this.visitChildren(whileStatement);
		
		--this.loopCount;
		
		this.checkRule11(whileStatement);
	}
	
	@Override
	public final void visit(final BinaryOperationExpression operation) {
		this.visitChildren(operation);
		
		this.checkRule12(operation);
		this.checkRule13(operation);
		this.checkRule14(operation);
	}
	
	@Override
	public final void visit(final MinusExpression minusExpression) {
		// visit child first, then we can use the child's
		// type as the expression's type.
		this.visitChildren(minusExpression);
		
		this.checkRule17(minusExpression);
	}
	
	@Override
	public final void visit(final NegationExpression negation) {
		// visit child first, then we can use the child's
		// type as the expression's type.
		this.visitChildren(negation);
	    
		this.checkRule14(negation);
	}
	
	@Override
	public final void visit(final LiteralExpression literalExpression) {
		// Do nothing
	}
	
	@Override
	public final void visit(final MethodCallExpression methodCall) {
		methodCall.setType(this.getType(methodCall.getMethodCall().getMethodName()));
		
		this.checkRule6(methodCall);
		
		this.visitChildren(methodCall);
	}
	
	@Override
	public final void visit(final LocationExpression location) {
		location.setType(this.getType(location.getLocation().getIdentifier()));
		
		this.visitChildren(location);
	}
	
	@Override
	public final void visit(final MethodCall methodCall) {
		this.checkRule2(methodCall);
		this.checkRule5(methodCall);
		
		this.visitChildren(methodCall);
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
		this.visitChildren(methodCallStatement);
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
		if (this.getScope().containsLocalKey(entity.getIdentifier())) {
			this.logError(entity,
					"Duplicate identifier " + entity.getIdentifier());
		} else {
			this.getScope().put(entity.getIdentifier(), entity);
		}
	}
	
	/**
	 * Rule: No identifier is used before it is declared.
	 * 
	 * @param location
	 * <br>Not null
	 */
	private final void checkRule2(final AbstractLocation location) {
		if (!this.getScope().containsKey(location.getIdentifier())) {
			this.logError(location,
					"Undeclared identifier " + location.getIdentifier());
		}
		
		location.setDeclaration((AbstractTypedEntityDeclaration) this.getScope().get(location.getIdentifier()));
	}
	
	/**
	 * Rule: No identifier is used before it is declared.
	 * 
	 * @param methodCall
	 * <br>Not null
	 */
	private final void checkRule2(final MethodCall methodCall) {
		if (!this.getScope().containsKey(methodCall.getMethodName())) {
			this.logError(methodCall,
					"Undeclared identifier " + methodCall.getMethodName());
		}
		
		methodCall.setDeclaration((MethodDeclaration) this.getScope().get(methodCall.getMethodName()));
	}
	
	/**
	 * Rule: The program contains a definition for a method called main that has no parameters
	 * (note that since execution starts at method main, any methods defined after main will never be executed).
	 * 
	 * @param program
	 * <br>Not null
	 */
	private final void checkRule3(final Program program) {
		final MethodDeclaration mainMethod = cast(MethodDeclaration.class, this.getScope().get("main"));
		
		if (mainMethod == null || mainMethod.getParameterDeclarations().size() != 0) {
			this.logError(program,
					"Missing method main(<no argument>)");
		}
	}
	
	/**
	 * Rule: The &lt;int_literal&gt; in an array declaration must be greater than 0.
	 * 
	 * @param field
	 * <br>Not null
	 */
	private final void checkRule4(final ArrayFieldDeclaration field) {
		if (field.getElementCount().getValue() <= 0) {
			this.logError(field.getElementCount(),
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
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getScope().get(methodName));
		
		if (method != null) {
			final String expectedSignature = getSignature(method.getParameterDeclarations());
			final String actualSignature = getSignature(methodCall.getArguments());
			
			if (!expectedSignature.equals(actualSignature)) {
				this.logError(methodCall,
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
		final MethodDeclaration method = cast(MethodDeclaration.class, this.getScope().get(methodName));
		
		if (method != null && method.getType().equals(void.class)) {
			this.logError(methodCall,
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
		Class<?> methodType = this.getScope().get(this.currentMethodName) instanceof MethodDeclaration ? 
				((MethodDeclaration)this.getScope().get(this.currentMethodName)).getType() : this.currentMethodType;
		
		if ( (methodType == void.class && returnStatement.getExpression() != null) ||
				(methodType != void.class && returnStatement.getExpression() == null) ) {
					this.logError(this.getScope().get(this.currentMethodName),
							"The method " + this.currentMethodName + " cannot have a return value");
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
		final AbstractTypedEntityDeclaration method = (AbstractTypedEntityDeclaration) this.getScope().get(this.currentMethodName);
		
		if (method.getType() != void.class && returnStatement.getExpression() != null) {
			if (method.getType() != returnStatement.getExpression().getType()) {
				this.logError(method,
						"The method " + this.currentMethodName + " return type does not match the return value");
			}
		}
	}
	
	/**
	 * Rule: An &lt;id&gt; used as a &lt;location&gt; must name a declared local/global variable or formal parameter.
	 * 
	 * @param identifierLocation
	 * <br>Not null
	 */
	private final void checkRule9(final IdentifierLocation identifierLocation) {
		final String identifier = identifierLocation.getIdentifier();
		
		if (identifier.equals("Program")) {
			this.logError(identifierLocation,
					"Cannot used the reserved identifier Program as a variable");
		}
		
		if (this.getScope().containsKey(identifier)) {
			if (this.getScope().get(identifier).getClass().equals(MethodDeclaration.class)) {
				this.logError(identifierLocation,
						"The method " + identifier + " cannot be used as a variable");
			}
		}
	}
	
	/**
	 * Rule: For all locations of the form &lt;id&gt;[&lt;expr&gt;]
	 * <br>(a) &lt;id&gt; must be an array variable, and
	 * <br>(b) the type of &lt;expr&gt; must be int.
	 * 
	 * @param location
	 * <br>Not null
	 */
	private final void checkRule10(final ArrayLocation location) {
		final String identifier = location.getIdentifier();
		
		if (this.getScope().containsKey(identifier)) {
			if (!(this.getScope().get(identifier) instanceof ArrayFieldDeclaration)) {
				this.logError(location,
						"The variable " + identifier + " is not an array type");
			}
		}
		
		this.checkType(location.getOffset(), int.class, location,
				"The array offset is not an int type");
	}
	
	/*
	 * General comment about type checking.
	 * if type == null, then undeclared identifier (rule 2)
	 * if we see a violation of rule 2, then rule 2 takes precedence
	 * and we do not indicate error for current rule
	 */
	
	/**
	 * Rule: The &lt;expr&gt; in if and while statements must have type boolean.
	 * 
	 * @param ifStatement
	 * <br>Not null
	 */
	private final void checkRule11(final IfStatement ifStatement) {
		this.checkType(ifStatement.getCondition(), boolean.class, ifStatement,
				"If condition should have type boolean.");
	}
	
	/**
	 * Rule: The &lt;expr&gt; in if and while statements must have type boolean.
	 * 
	 * @param whileStatement
	 * <br>Not null
	 */
	private final void checkRule11(final WhileStatement whileStatement) {
		this.checkType(whileStatement.getCondition(), boolean.class, whileStatement,
				"While condition should have type boolean.");
	}
	
	/**
	 * Rule: The operands of &lt;arith_op&gt;s and &lt;rel_op&gt;s must have type int.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule12(final BinaryOperationExpression operation) {
		if (OPERATORS_WITH_INT_OPERANDS.contains(operation.getOperator())) {
			this.checkType(operation.getLeft(), int.class,
					"Operand of arithmetic and relational operations must have type int.");
			
			this.checkType(operation.getRight(), int.class,
					"Operand of arithmetic and relational operations must have type int.");
		}
	}
	
	/**
	 * Rule: The operands of &lt;eq_op&gt;s must have the same type, either int or boolean.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule13(final BinaryOperationExpression operation) {
		if (operation.getLeft().getType() == null || operation.getRight().getType() == null) {
			return;
		}
		
		if (EQUALITY_OPERATORS.contains(operation.getOperator())) {
			if (int.class.equals(operation.getLeft().getType()) && 
					int.class.equals(operation.getRight().getType())) {
				// do nothing
			} else if (boolean.class.equals(operation.getLeft().getType()) && 
					boolean.class.equals(operation.getRight().getType())) {
				// do nothing
			} else {
				this.logError(operation,
						"Operand of equality operations must have same type, " +
						"either int or boolean.");
			}
		}
	}
	
	/**
	 * Rule: The operands of &lt;cond_op&gt;s and the operand of logical not (!) must have type boolean.
	 * 
	 * @param operation
	 * <br>Not null
	 */
	private final void checkRule14(final BinaryOperationExpression operation) {
		if (operation.getOperator().equals("&&") ||
				operation.getOperator().equals("||")) {
			this.checkType(operation.getLeft(), boolean.class,
					"Operand of conditional operations must have type boolean.");
			
			this.checkType(operation.getRight(), boolean.class,
					"Operand of conditional operations must have type boolean.");
		}
	}
	
	/**
	 * Rule: The operands of &lt;cond_op&gt;s and the operand of logical not (!) must have type boolean.
	 * 
	 * @param negation
	 * <br>Not null
	 */
	private final void checkRule14(final NegationExpression negation) {
		this.checkType(negation.getOperand(), boolean.class, negation,
				"Operand of negation operations must have type boolean.");
	}
	
	/**
	 * Rule: The &lt;location> and the &lt;expr&gt; in an assignment, &lt;location&gt; = &lt;expr&gt;, must have the same type.
	 * 
	 * @param assignment
	 * <br>Not null
	 */
	private final void checkRule15(final AssignmentStatement assignment) {
		final Class<?> locationType = this.getType(assignment.getLocation().getIdentifier());
		final Class<?> expressionType = assignment.getExpression().getType();
		
		if (locationType == null || expressionType == null) {
			// if null -> rule 2 violation 
			return;
		}
		
		if (!locationType.equals(expressionType)) {
			this.logError(assignment,
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
		if (this.loopCount == 0) {
			this.logError(breakStatement,
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
		if (this.loopCount == 0) {
			this.logError(continueStatement,
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
		this.checkType(minusExpression.getOperand(), int.class, minusExpression,
				"Unary minus expression is not an int type");
	}
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * @param expectedType
	 * <br>Not null
	 * @param errorNode 
	 * <br>Not null
	 * @param errorMessage
	 * <br>Not null
	 */
	private final void checkType(final AbstractExpression expression, final Class<?> expectedType, final AbstractNode errorNode, final String errorMessage) {
		if (notEquals(expression.getType(), expectedType)) {
			this.logError(errorNode, errorMessage);
		}
	}
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * @param expectedType
	 * <br>Not null
	 * @param errorMessage
	 * <br>Not null
	 */
	private final void checkType(final AbstractExpression expression, final Class<?> expectedType, final String errorMessage) {
		this.checkType(expression, expectedType, expression, errorMessage);
	}
	
	/**
	 * 
	 * @param node
	 * <br>Not null
	 * @param message
	 * <br>Maybe null
	 */
	private final void logError(final AbstractNode node, final String message) {
		this.logError(node.getTokenRow(), node.getTokenColumn(), message);
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
	
	/**
	 * 
	 * @param methodScope
	 * <br>Range: any boolean
	 */
	private final void pushNewScope(final boolean methodScope) {
		if (!this.methodScope) {
			this.getScope().push();
		}
		
		this.methodScope = methodScope;
	}
	
	private final void popCurrentScope() {
		this.getScope().pop();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	private final NestingDictionary<String, AbstractNode> getScope() {
		return this.scope;
	}
	
	/**
	 * 
	 * @param identifier
	 * <br>Maybe null
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	private final Class<?> getType(final String identifier) {
		final AbstractNode entity = this.getScope().get(identifier);
		
		if (entity instanceof AbstractTypedEntityDeclaration) {
			return ((AbstractTypedEntityDeclaration) this.getScope().get(identifier)).getType();
		}
		
		return null;
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
	 * @param object1
	 * <br>Maybe null
	 * @param object2
	 * <br>Maybe null
	 * @return {@code object1 != null && !object1.equals(object2)}
	 * <br>Range: any boolean
	 */
	private static final boolean notEquals(final Object object1, final Object object2) {
		return object1 != null && !object1.equals(object2);
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
