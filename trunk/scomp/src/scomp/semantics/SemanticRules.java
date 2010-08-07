package scomp.semantics;

import static scomp.Tools.*;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import scomp.DecafParser;
import scomp.ir.AbstractLocation;
import scomp.ir.AbstractTypedEntityDeclaration;
import scomp.ir.ArrayFieldDeclaration;
import scomp.ir.ArrayLocation;
import scomp.ir.AssignmentStatement;
import scomp.ir.BinaryOperationExpression;
import scomp.ir.Block;
import scomp.ir.FieldDeclaration;
import scomp.ir.IdentifierLocation;
import scomp.ir.IfStatement;
import scomp.ir.IntLiteral;
import scomp.ir.LocationExpression;
import scomp.ir.MethodCall;
import scomp.ir.MethodCallExpression;
import scomp.ir.MethodDeclaration;
import scomp.ir.ParameterDeclaration;
import scomp.ir.Program;
import scomp.ir.ReturnStatement;
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
	public final void beginVisit(final Program program) {
		this.pushNewScope();
	}
	
	@Override
	public final void endVisit(final Program program) {
		this.checkRule3(program);
		
		this.popCurrentScope();
	}
	
	@Override
	public final void beginVisit(final ArrayFieldDeclaration field) {
		this.checkRule1(field);
		this.checkRule4(field);
	}
	
	@Override
	public final void endVisit(final ArrayFieldDeclaration field) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		this.checkRule1(field);
	}
	
	@Override
	public final void beginVisit(final MethodDeclaration method) {
		this.nameOfMethod = method.getIdentifier();
		this.checkRule1(method);
		
		this.pushNewScope();
	}
	
	@Override
	public final void endVisit(final MethodDeclaration method) {
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
	public final void beginVisit(final Block block) {
		this.pushNewScope();
	}
	
	@Override
	public final void endVisit(final Block block) {
		this.popCurrentScope();
	}
	
	@Override
	public final void beginVisit(final AssignmentStatement assignment) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final AssignmentStatement assignment) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final ReturnStatement returnStatement) {
		this.checkRule7(returnStatement);
		this.checkRule8(returnStatement);
	}
	
	@Override
	public final void endVisit(final ReturnStatement returnStatement) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public void beginVisit(final ArrayLocation location) {
		this.checkRule2(location);
	}
	
	@Override
	public void endVisit(final ArrayLocation location) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final BinaryOperationExpression operation) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final BinaryOperationExpression operation) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final LocationExpression location) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final LocationExpression location) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void visit(final IdentifierLocation location) {
		this.checkRule2(location);
	}
	
	@Override
	public final void beginVisit(final MethodCallExpression methodCall) {
		this.checkRule6(methodCall);
	}
	
	@Override
	public final void endVisit(final MethodCallExpression methodCall) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final MethodCall methodCall) {
		this.checkRule2(methodCall);
		this.checkRule5(methodCall);
	}
	
	@Override
	public final void endVisit(final MethodCall methodCall) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void visit(final IntLiteral literal) {
		// TODO
		debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final IfStatement ifStatement) {
		this.checkRule11(ifStatement);
	}
	
	@Override
	public final void beginVisit(final WhileStatement whileStatement) {
		this.checkRule11(whileStatement);
	}
	
	/**
	 * 
	 * @param entity
	 * <br>Not null
	 */
	private final void checkRule1(final AbstractTypedEntityDeclaration entity) {
		if (this.getCurrentScope().containsKey(entity.getIdentifier())) {
			this.logError(entity.getIdentifierRow(), entity.getIdentifierColumn(),
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
			this.logError(location.getIdentifierRow(), location.getIdentifierColumn(),
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
			this.logError(program.getLastTokenRow(), program.getLastTokenColumn(),
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
			this.logError(field.getElementCount().getRow(), field.getElementCount().getColumn(),
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
			this.logError(method.getIdentifierRow(), method.getIdentifierColumn(),
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
				this.logError(method.getIdentifierRow(), method.getIdentifierColumn(),
						"The method " + this.nameOfMethod + " return type does not match the return value");
			}
		}
	}
	
	/**
	 * 
	 * @param booleanExpression
	 * <br> not null
	 */
	private final void checkRule11(final IfStatement ifStatement) {
		if(ifStatement.getCondition().getType() != boolean.class) {
			this.logError("If condition does not have a boolean type");
		}
	}
	
	/**
	 * 
	 * @param whileStatement
	 * <br> not null
	 */
	private final void checkRule11(final WhileStatement whileStatement) {
		if(whileStatement.getCondition().getType() != boolean.class) {
			this.logError("While condition does not have a boolean type");
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
	
}
