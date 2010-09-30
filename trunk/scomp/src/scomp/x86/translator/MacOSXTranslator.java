package scomp.x86.translator;

import static scomp.Tools.array;
import static scomp.x86.ir.Register.Name.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.AbstractProgramElement;
import scomp.x86.ir.Add;
import scomp.x86.ir.Call;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.LabelOperand;
import scomp.x86.ir.Register;
import scomp.x86.ir.Register.Name;

/**
 * This is the Mac OS X implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class MacOSXTranslator extends AbstractTranslator {
	
	private final Map<String, List<AbstractProgramElement>> callouts;
	
	public MacOSXTranslator() {
		this.callouts = new TreeMap<String, List<AbstractProgramElement>>(new Comparator<String>() {
			
			@Override
			public final int compare(final String s1, final String s2) {
				final Object[] split1 = split(s1);
				final Object[] split2 = split(s2);
				
				final int result = split1[0].toString().compareTo(split2[0].toString());
				
				return result != 0 ? result : ((Integer) split1[1]).compareTo((Integer) split2[1]);
			}
			
		});
	}
	
	@Override
	protected final void x86MOV(final String labelName, final Name registerName) {
		this.x86LEA(labelName, registerName);
	}
	
	@Override
	protected final void x86PUSH(final String labelName) {
		this.x86LEA(labelName, RAX);
		this.x86PUSH(RAX);
	}
	
	@Override
	protected final void addSectionsToProgram(final List<AbstractProgramElement> result) {
		result.addAll(this.getStringSection());
		
		for (final List<AbstractProgramElement> callout : this.callouts.values()) {
			result.addAll(callout);
		}
		
		result.addAll(this.getProcedureSection());
		result.addAll(this.getGlobalSection());
	}
	
	@Override
	protected final void afterChildren(final MethodCallout methodCallout) {
		final int argumentCount = methodCallout.getArguments().size();
		final String callName = "callout_" + methodCallout.getMethodName() + "_" + argumentCount;
		
		if (!this.callouts.containsKey(callName)) {
			this.pushProcedureSection();

			this.x86LABEL(callName);

			if (argumentCount >= 7) {
				final int stackArgumentCount = argumentCount - 6;
				
				this.x86ENTER(stackArgumentCount);
				this.x86AND(-16, RSP);
				
				this.x86ADD(stackArgumentCount, RSP);
				
				for (int argumentOffset = argumentCount + 1; argumentOffset >= 8; --argumentOffset) {
					this.x86PUSH(argumentOffset, RBP);
				}
			} else {
				this.x86ENTER(0);
				this.x86AND(-16, RSP);
			}
			if (argumentCount >= 6) {
				this.x86MOV(56, RBP, R9);
			}
			if (argumentCount >= 5) {
				this.x86MOV(48, RBP, R8);
			}
			if (argumentCount >= 4) {
				this.x86MOV(40, RBP, RCX);
			}
			if (argumentCount >= 3) {
				this.x86MOV(32, RBP, RDX);
			}
			if (argumentCount >= 2) {
				this.x86MOV(24, RBP, RSI);
			}
			if (argumentCount >= 1) {
				this.x86MOV(16, RBP, RDI);
			}
			this.x86MOV(0, RAX);
			this.x86CALL("_" + methodCallout.getMethodName());
			
			this.x86LEAVE();
			this.x86RET();
			
			this.callouts.put(callName, this.popProcedureSection());
		}
		
		this.getProcedureSection().add(new Call(this.getDefaultSizeSuffix(), new LabelOperand(callName)));
		this.getProcedureSection().add(new Add(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), argumentCount), new Register(this.getResizedName(RSP))));
	}
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_64;
	}
	
	/**
	 * Splits a string of the form "callout_&lt;C function name&gt;_&lt;argument count&lt;" into 2 parts:<ul>
	 * 	<li>the beginning of the string (everything up to the last "_");
	 * 	<li>the parsed argument count (Integer).
	 * </ul>
	 * 
	 * @param callName
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	static final Object[] split(final String callName) {
		final int argumentCountIndex = callName.lastIndexOf("_") + 1;
		
		return array((Object) callName.substring(0, argumentCountIndex), Integer.parseInt(callName.substring(argumentCountIndex)));
	}
	
}
