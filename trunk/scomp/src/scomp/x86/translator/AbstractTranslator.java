package scomp.x86.translator;

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
import scomp.ir.ReturnStatement;
import scomp.ir.StringCalloutArgument;
import scomp.ir.VariableDeclaration;
import scomp.ir.Visitor;
import scomp.ir.WhileStatement;
import scomp.x86.ir.Program;

/**
 * This is the base class for the OS-specific translators.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslator implements Visitor {
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public final Program getProgram() {
		// TODO
		return null;
	}
	
	@Override
	public final void visit(final scomp.ir.Program program) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ArrayFieldDeclaration field) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final IntLiteral literal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodDeclaration method) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ParameterDeclaration parameter) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final VariableDeclaration variable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final Block block) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final BlockStatement block) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final AssignmentStatement assignment) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ReturnStatement returnStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ArrayLocation location) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final LocationExpression location) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final IdentifierLocation location) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCallExpression methodCall) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCall methodCall) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final WhileStatement whileStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final BinaryOperationExpression operation) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final NegationExpression negation) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final BooleanLiteral booleanLiteral) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final BreakStatement breakStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final CharLiteral charLiteral) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ContinueStatement continueStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ExpressionCalloutArgument expressionCalloutArgument) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final LiteralExpression literalExpression) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCallStatement methodCallStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCallout methodCallout) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MinusExpression minusExpression) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final StringCalloutArgument stringCalloutArgument) {
		// TODO Auto-generated method stub
		
	}
	
}
