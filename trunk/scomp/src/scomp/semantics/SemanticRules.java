package scomp.semantics;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import scomp.DecafParser;
import scomp.Tools;
import scomp.ir.AbstractTypedEntityDeclaration;
import scomp.ir.ArrayFieldDeclaration;
import scomp.ir.ArrayLocation;
import scomp.ir.AssignmentStatement;
import scomp.ir.BinaryOperationExpression;
import scomp.ir.Block;
import scomp.ir.FieldDeclaration;
import scomp.ir.IdentifierLocation;
import scomp.ir.LocationExpression;
import scomp.ir.MethodDeclaration;
import scomp.ir.ParameterDeclaration;
import scomp.ir.Program;
import scomp.ir.ReturnStatement;
import scomp.ir.VariableDeclaration;
import scomp.ir.Visitor;

/**
 * This is the visitor responsible for checking the Decaf semantic rules.
 * 
 * @author codistmonk (creation 2010-07-28)
 *
 */
public final class SemanticRules implements Visitor {
	
	private final Deque<Map<String, AbstractTypedEntityDeclaration>> scopes;
	
	private final Logger logger;
	
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
		this.popCurrentScope();
	}
	
	@Override
	public final void visit(final ArrayFieldDeclaration field) {
		this.checkRule1(field);
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		this.checkRule1(field);
	}
	
	@Override
	public final void beginVisit(final MethodDeclaration method) {
		this.pushNewScope();
		
		this.checkRule1(method);
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
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final AssignmentStatement assignment) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final ReturnStatement returnStatement) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final ReturnStatement returnStatement) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public void beginVisit(final ArrayLocation location) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public void endVisit(final ArrayLocation location) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final BinaryOperationExpression operation) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final BinaryOperationExpression operation) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void beginVisit(final LocationExpression location) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void endVisit(final LocationExpression location) {
		// TODO
		Tools.debugPrint("TODO");
	}
	
	@Override
	public final void visit(final IdentifierLocation location) {
		// TODO
		Tools.debugPrint("TODO");
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
	 * @param entityDeclaration
	 * <br>Not null
	 */
	private final void checkRule1(final AbstractTypedEntityDeclaration entityDeclaration) {
		if (this.getCurrentScope().containsKey(entityDeclaration.getIdentifier())) {
			this.logError("(:" + entityDeclaration.getIdentifierRow() + ":" + entityDeclaration.getIdentifierColumn() +
					") Duplicate identifier " + entityDeclaration.getIdentifier());
		} else {
			this.getCurrentScope().put(entityDeclaration.getIdentifier(), entityDeclaration);
		}
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
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	private final Map<String, AbstractTypedEntityDeclaration> getCurrentScope() {
		return this.scopes.peek();
	}
	
}
