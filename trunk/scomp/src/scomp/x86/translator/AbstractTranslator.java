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
import java.util.NoSuchElementException;

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
import scomp.ir.ReturnStatement;
import scomp.ir.StringCalloutArgument;
import scomp.ir.VariableDeclaration;
import scomp.ir.WhileStatement;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.AbstractProgramElement;
import scomp.x86.ir.Add;
import scomp.x86.ir.And;
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
	
	private final Map<String, String> stringLabelNames;
	
	private final Deque<List<AbstractProgramElement>> procedureSectionStack;
	
	private final Map<VariableDeclaration, Integer> localVariables;
	
	private MethodDeclaration currentMethod;
	
	protected AbstractTranslator() {
		this.stringSection = new ArrayList<AbstractProgramElement>();
		this.stringLabelNames = new LinkedHashMap<String, String>();
		this.procedureSectionStack = new LinkedList<List<AbstractProgramElement>>();
		this.localVariables = new IdentityHashMap<VariableDeclaration, Integer>();
		
		this.procedureSectionStack.push(new ArrayList<AbstractProgramElement>());
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
		this.x86PUSH(literal.getValue());
	}
	
	@Override
	public final void visit(final FieldDeclaration field) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final void visit(final MethodDeclaration method) {
		this.currentMethod = method;
		this.localVariables.clear();
		
		this.x86LABEL("decaf_" + method.getIdentifier());
		
		if ("main".equals(method.getIdentifier())) {
			this.x86GLOBL("_main");
			this.x86LABEL("_main");
		}
		
		this.pushProcedureSection();
		
		this.visitChildren(method);
		
		this.pushProcedureSection();
		
		this.x86ENTER(this.localVariables.size());
		
		for (int i = 0; i < this.localVariables.size(); ++i) {
			this.x86MOV(0, i, RBP);
		}
		
		this.popAndPrependProcedureSection();
		this.popAndAppendProcedureSection();
		
		if ("main".equals(method.getIdentifier())) {
			this.x86MOV(0, RAX);
		}
		this.x86LEAVE();
		this.x86RET();
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
		
		final AbstractTypedEntityDeclaration declaration = assignment.getLocation().getDeclaration();
		
		if (declaration instanceof VariableDeclaration) {
			this.x86POP(-(this.localVariables.get(declaration) + 1), RBP);
		} else if (declaration instanceof ParameterDeclaration) {
			this.x86POP(indexOf(this.currentMethod.getParameterDeclarations(), declaration) + 2, RBP);
		} else {
			// TODO global, array
		}
	}
	
	@Override
	public final void visit(final ReturnStatement returnStatement) {
		this.visitChildren(returnStatement);
		
		if (returnStatement.getExpression() == null) {
			this.x86MOV(0, RAX);
		} else {
			this.x86POP(RAX);
		}
		this.x86LEAVE();
		this.x86RET();
	}
	
	@Override
	public final void visit(final ArrayLocation location) {
		this.visitChildren(location);
	}
	
	@Override
	public final void visit(final LocationExpression location) {
		this.visitChildren(location);
		
		final AbstractTypedEntityDeclaration declaration = location.getLocation().getDeclaration();
		
		if (declaration instanceof VariableDeclaration) {
			this.x86PUSH(-(this.localVariables.get(declaration) + 1), RBP);
		} else if (declaration instanceof ParameterDeclaration) {
			this.x86PUSH(indexOf(this.currentMethod.getParameterDeclarations(), declaration) + 2, RBP);
		} else {
			// TODO global, array
		}
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
		
		this.x86CALL("decaf_" + methodCall.getMethodName());
		this.x86ADD(methodCall.getArguments().size(), RSP);
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
		
		this.x86PUSH(stringLabelName);
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
	 * <br>Shared
	 */
	protected final void x86GLOBL(final String labelName) {
		this.getProcedureSection().add(new Globl(labelName));
	}
	
	/**
	 * 
	 * @param name
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86LABEL(final String name) {
		this.getProcedureSection().add(new Label(name));
	}
	
	/**
	 * 
	 * @param variableCount
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 */
	protected final void x86ENTER(final int variableCount) {
		this.getProcedureSection().add(new Enter(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), variableCount), new IntegerValue(0)));
	}
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 * @param variableIndex
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86MOV(final int value, final int variableIndex, final Name registerName) {
		this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(value),
				new RegisterRelativeAddress(this.getDefaultSizeSuffix(), -this.getDefaultVariableByteCount() * (variableIndex + 1), registerName)));
	}
	
	/**
	 * 
	 * @param sourceOffset
	 * <br>Range: any integer
	 * @param sourceRegisterName
	 * <br>Not null
	 * <br>Shared
	 * @param destinationRegisterName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86MOV(final int sourceOffset, final Name sourceRegisterName, final Name destinationRegisterName) {
		this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(),
				new RegisterRelativeAddress(this.getDefaultSizeSuffix(), sourceOffset, sourceRegisterName),
				new Register(destinationRegisterName)));
	}
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86MOV(final int value, final Name registerName) {
		this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(value), new Register(this.getResizedName(registerName))));
	}
	
	protected final void x86LEAVE() {
		this.getProcedureSection().add(new Leave(this.getDefaultSizeSuffix()));
	}
	
	protected final void x86RET() {
		this.getProcedureSection().add(new Ret(this.getDefaultSizeSuffix()));
	}
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 */
	protected final void x86PUSH(final int value) {
		this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(), new IntegerValue(value)));
	}
	
	/**
	 * 
	 * @param variableIndex
	 * <br>Range: any integer
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86PUSH(final int variableIndex, final Name registerName) {
		this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(),
				new RegisterRelativeAddress(this.getDefaultSizeSuffix(), this.getDefaultVariableByteCount() * variableIndex, registerName)));
	}
	
	/**
	 * 
	 * @param labelName
	 * <br>Not null
	 */
	protected abstract void x86PUSH(final String labelName);
	
	/**
	 * 
	 * @param variableIndex
	 * <br>Range: any integer
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86POP(final int variableIndex, final Name registerName) {
		this.getProcedureSection().add(new Pop(this.getDefaultSizeSuffix(),
				new RegisterRelativeAddress(this.getDefaultSizeSuffix(), this.getDefaultVariableByteCount() * variableIndex, registerName)));
	}
	
	/**
	 * 
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86POP(final Name registerName) {
		this.getProcedureSection().add(new Pop(this.getDefaultSizeSuffix(),
				new Register(this.getResizedName(registerName))));
	}
	
	/**
	 * 
	 * @param callName
	 * <br>Not null
	 */
	protected final void x86CALL(final String callName) {
		this.getProcedureSection().add(new Call(this.getDefaultSizeSuffix(), new LabelOperand(callName)));
	}
	
	/**
	 * 
	 * @param variableCount
	 * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86ADD(final int variableCount, final Name registerName) {
		this.getProcedureSection().add(new Add(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), variableCount),
				new Register(this.getResizedName(registerName))));
	}
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 * @param registerName
	 * <br>Not null
	 * <br>Shared
	 */
	protected final void x86AND(final int value, final Name registerName) {
		this.getProcedureSection().add(new And(this.getDefaultSizeSuffix(),
				new IntegerValue(value),
				new Register(this.getResizedName(registerName))));
	}
	
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
		return this.procedureSectionStack.peek();
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
	
	protected final void pushProcedureSection() {
		this.procedureSectionStack.push(new ArrayList<AbstractProgramElement>(this.getProcedureSection()));
		this.getProcedureSection().clear();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * @throws NoSuchElementException If the stack is empty
	 */
	protected final List<AbstractProgramElement> popProcedureSection() {
		return this.procedureSectionStack.pop();
	}
	
	protected final void popAndAppendProcedureSection() {
		final List<AbstractProgramElement> procedureSection = this.popProcedureSection();
		
		this.getProcedureSection().addAll(procedureSection);
	}
	
	protected final void popAndPrependProcedureSection() {
		final List<AbstractProgramElement> procedureSection = this.popProcedureSection();
		
		this.getProcedureSection().addAll(0, procedureSection);
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
	
}
