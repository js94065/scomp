package scomp.x86.translator;

import static scomp.x86.ir.AbstractInstruction.SIZE_SUFFIX_32;
import static scomp.x86.ir.AbstractInstruction.SIZE_SUFFIX_64;
import static scomp.x86.ir.Register.Name.*;

import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import scomp.x86.ir.Add;
import scomp.x86.ir.Ascii;
import scomp.x86.ir.Call;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.Enter;
import scomp.x86.ir.Globl;
import scomp.x86.ir.IntegerValue;
import scomp.x86.ir.Label;
import scomp.x86.ir.LabelOperand;
import scomp.x86.ir.Leave;
import scomp.x86.ir.Mov;
import scomp.x86.ir.Pop;
import scomp.x86.ir.Program;
import scomp.x86.ir.Push;
import scomp.x86.ir.Register;
import scomp.x86.ir.RegisterRelativeAddress;
import scomp.x86.ir.Ret;
import scomp.x86.ir.Register.Name;

/**
 * This is the base class for the OS-specific translators.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslator extends AbstractVisitor {
	
	private final List<AbstractProgramElement> stringSection;
	
	private final List<AbstractProgramElement> procedureSection;
	
	private final Map<String, String> stringLabelNames;
	
	private final Deque<List<AbstractProgramElement>> procedureSectionStack;
	
	private final Map<VariableDeclaration, Integer> localVariables;
	
	private MethodDeclaration currentMethod;
	
	protected AbstractTranslator() {
		this.stringSection = new ArrayList<AbstractProgramElement>();
		this.procedureSection = new ArrayList<AbstractProgramElement>();
		this.stringLabelNames = new LinkedHashMap<String, String>();
		this.procedureSectionStack = new LinkedList<List<AbstractProgramElement>>();
		this.localVariables = new IdentityHashMap<VariableDeclaration, Integer>();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public final Program getProgram() {
		final Program result = new Program();
		
		this.addSectionsToProgram(result.getElements());
		
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
		this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(), new IntegerValue(literal.getValue())));
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodDeclaration method) {
		this.currentMethod = method;
		this.localVariables.clear();
		this.getProcedureSection().add(new Label("decaf_" + method.getIdentifier()));
		
		if ("main".equals(method.getIdentifier())) {
			this.getProcedureSection().add(new Globl("_main"));
			this.getProcedureSection().add(new Label("_main"));
		}
		
		this.pushProcedureSection();
		this.visitChildren(method);
		this.peekProcedureSection().add(new Enter(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), this.localVariables.size()), new IntegerValue(0)));
		for (int i = 0; i < this.localVariables.size(); ++i) {
			this.peekProcedureSection().add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(0),
					new RegisterRelativeAddress(this.getDefaultSizeSuffix(), -this.getDefaultVariableByteCount() * (i + 1), RBP)));
		}
		this.updateAndPopProcedureSection();
		
		if ("main".equals(method.getIdentifier())) {
			this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(0), new Register(this.getResizedName(RAX))));
		}
		this.getProcedureSection().add(new Leave(this.getDefaultSizeSuffix()));
		this.getProcedureSection().add(new Ret(this.getDefaultSizeSuffix()));
	}
	
	@Override
	public final void visit(final ParameterDeclaration parameter) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final VariableDeclaration variable) {
		this.localVariables.put(variable, this.localVariables.size());
	}
	
	@Override
	public final void visit(final Block block) {
		this.visitChildren(block);
		
	}
	
	@Override
	public final void visit(final BlockStatement block) {
		this.visitChildren(block);
	}
	
	@Override
	public final void visit(final AssignmentStatement assignment) {
		this.visitChildren(assignment);
		
		if (assignment.getLocation().getDeclaration() instanceof VariableDeclaration) {
			this.getProcedureSection().add(new Pop(this.getDefaultSizeSuffix(),
					new RegisterRelativeAddress(this.getDefaultSizeSuffix(),
							-this.getDefaultVariableByteCount() * (this.localVariables.get(assignment.getLocation().getDeclaration()) + 1), RBP)));
		} else if (assignment.getLocation().getDeclaration() instanceof ParameterDeclaration) {
			this.getProcedureSection().add(new Pop(this.getDefaultSizeSuffix(),
					new RegisterRelativeAddress(this.getDefaultSizeSuffix(),
							this.getDefaultVariableByteCount() * (indexOf(this.currentMethod.getParameterDeclarations(), assignment.getLocation().getDeclaration()) + 2), RBP)));
		} else {
			// TODO global, array
		}
	}
	
	@Override
	public final void visit(final ReturnStatement returnStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final ArrayLocation location) {
		this.visitChildren(location);
	}
	
	@Override
	public final void visit(final LocationExpression location) {
		this.visitChildren(location);
		
		if (location.getLocation().getDeclaration() instanceof VariableDeclaration) {
			this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(),
					new RegisterRelativeAddress(this.getDefaultSizeSuffix(),
							-this.getDefaultVariableByteCount() * (this.localVariables.get(location.getLocation().getDeclaration()) + 1), RBP)));
		} else if (location.getLocation().getDeclaration() instanceof ParameterDeclaration) {
			this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(),
					new RegisterRelativeAddress(this.getDefaultSizeSuffix(),
							this.getDefaultVariableByteCount() * (indexOf(this.currentMethod.getParameterDeclarations(), location.getLocation().getDeclaration()) + 2), RBP)));
		} else {
			// TODO global, array
		}
	}
	
	/**
	 * 
	 * @param list
	 * <br>Not null
	 * @param element
	 * <br>Maybe null
	 * @return
	 * <br>Range: {@code [-1 .. list.size() - 1]}
	 */
	private static final int indexOf(final List<?> list, final Object element) {
		for (int result = 0; result < list.size(); ++result) {
			if (list.get(result) == element) {
				return result;
			}
		}
		
		return -1;
	}
	
	@Override
	public final void visit(final IdentifierLocation location) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodCallExpression methodCall) {
		this.visitChildren(methodCall);
		
		this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(), new Register(this.getResizedName(RAX))));
	}
	
	@Override
	public final void visit(final MethodCall methodCall) {
		this.visitChildren(methodCall);
		
		this.getProcedureSection().add(new Call(this.getDefaultSizeSuffix(), new LabelOperand("decaf_" + methodCall.getMethodName())));
		this.getProcedureSection().add(new Add(this.getDefaultSizeSuffix(), new CompositeIntegerValue(this.getDefaultVariableByteCount(), methodCall.getArguments().size()), new Register(this.getResizedName(RSP))));
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
		this.visitChildren(expressionCalloutArgument);
	}
	
	@Override
	public final void visit(final LiteralExpression literalExpression) {
		this.visitChildren(literalExpression);
	}
	
	@Override
	public final void visit(final MethodCallStatement methodCallStatement) {
		this.visitChildren(methodCallStatement);
	}
	
	@Override
	public final void visit(final MethodCallout methodCallout) {
		this.visitChildren(methodCallout);
		
		this.afterChildren(methodCallout);
	}
	
	@Override
	public final void visit(final MinusExpression minusExpression) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final StringCalloutArgument stringCalloutArgument) {
		final String string = stringCalloutArgument.getString();
		String stringLabelName = this.stringLabelNames.get(string);
		
		if (stringLabelName == null) {
			this.stringLabelNames.put(string, stringLabelName = "STRING_" + this.stringLabelNames.size());
			this.getStringSection().add(new Label(stringLabelName));
			this.getStringSection().add(new Ascii(string));
		}
		
		this.addPushLabel(stringLabelName);
	}
	
	/**
	 * 
	 * @param literalExpression
	 * <br>Not null
	 */
	protected final void visitChildren(final LiteralExpression literalExpression) {
		literalExpression.getLiteral().accept(this);
	}
	
	/**
	 * 
	 * @param labelName
	 * <br>Not null
	 */
	protected abstract void addPushLabel(final String labelName);
	
	/**
	 * 
	 * @param result
	 * <br>Not null
	 * <br>Shared
	 * <br>Input-output
	 */
	protected void addSectionsToProgram(final List<AbstractProgramElement> result) {
		result.addAll(this.getStringSection());
		result.addAll(this.getProcedureSection());
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	protected final List<AbstractProgramElement> getStringSection() {
		return this.stringSection;
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
	 * @param methodCallout
	 * <br>Not null
	 */
	protected abstract void afterChildren(final MethodCallout methodCallout);
	
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
		return AbstractInstruction.getResizedName(name, this.getDefaultSizeSuffix());
	}
	
	private final void pushProcedureSection() {
		this.procedureSectionStack.push(new ArrayList<AbstractProgramElement>(this.getProcedureSection()));
		this.getProcedureSection().clear();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	private final List<AbstractProgramElement> peekProcedureSection() {
		return this.procedureSectionStack.peek();
	}
	
	private final void updateAndPopProcedureSection() {
		this.peekProcedureSection().addAll(this.getProcedureSection());
		this.getProcedureSection().clear();
		this.getProcedureSection().addAll(this.procedureSectionStack.pop());
	}
	
}
