package scomp.x86.translator;

import static scomp.x86.ir.AbstractInstruction.SIZE_SUFFIX_32;
import static scomp.x86.ir.AbstractInstruction.SIZE_SUFFIX_64;
import static scomp.x86.ir.Register.Name.*;

import java.util.ArrayList;
import java.util.List;

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
import scomp.ir.ReturnStatement;
import scomp.ir.StringCalloutArgument;
import scomp.ir.VariableDeclaration;
import scomp.ir.WhileStatement;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.AbstractProgramElement;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.Enter;
import scomp.x86.ir.Globl;
import scomp.x86.ir.IntegerValue;
import scomp.x86.ir.Label;
import scomp.x86.ir.Leave;
import scomp.x86.ir.Mov;
import scomp.x86.ir.Program;
import scomp.x86.ir.Register;
import scomp.x86.ir.Ret;
import scomp.x86.ir.Register.Name;

/**
 * This is the base class for the OS-specific translators.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslator extends AbstractVisitor {

	private final List<AbstractProgramElement> procedureSection;
	
	protected AbstractTranslator() {
		this.procedureSection = new ArrayList<AbstractProgramElement>();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public final Program getProgram() {
		final Program result = new Program();
		
		result.getElements().addAll(this.getProcedureSection());
		
		return result;
	}
	
	@Override
	public final void visit(final scomp.ir.Program program) {
		this.visitChildren(program);
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
		this.getProcedureSection().add(new Label("decaf_" + method.getIdentifier()));
		
		if ("main".equals(method.getIdentifier())) {
			this.getProcedureSection().add(new Globl("_main"));
			this.getProcedureSection().add(new Label("_main"));
		}
		
		this.getProcedureSection().add(new Enter(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), 0), new IntegerValue(0)));
		
		this.visitChildren(method);
		
		this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(0), new Register(this.getResizedName(RAX))));
		this.getProcedureSection().add(new Leave(this.getDefaultSizeSuffix()));
		this.getProcedureSection().add(new Ret(this.getDefaultSizeSuffix()));
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
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	protected final List<AbstractProgramElement> getProcedureSection() {
		return this.procedureSection;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_UNSPECIFIED}, {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 */
	protected abstract String getDefaultSizeSuffix();
	
	/**
	 * 
	 * @return
	 * <br>Range: { 4, 8 }
	 */
	protected final int getDefaultVariableByteCount() {
		if (SIZE_SUFFIX_32.equals(this.getDefaultSizeSuffix())) {
			return 4;
		}
		
		if (SIZE_SUFFIX_64.equals(this.getDefaultSizeSuffix())) {
			return 8;
		}
		
		throw new UnsupportedOperationException("Cannot determine default variable byte count from size suffix: " + this.getDefaultSizeSuffix());
	}
	
	/**
	 * 
	 * @param name
	 * <br>Not null
	 * @return
	 * <br>Not null
	 */
	protected final Name getResizedName(final Name name) {
		if (SIZE_SUFFIX_32.equals(this.getDefaultSizeSuffix())) {
			switch (name) {
			case RAX:
				return EAX;
			case RCX:
				return ECX;
			case RSP:
				return ESP;
			case RBP:
				return EBP;
			case RIP:
				return EIP;
			default:
				break;
			}
		}
		
		return name;
	}
	
}
