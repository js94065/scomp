package scomp.x86.translator;

import static scomp.x86.ir.Register.Name.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.AbstractProgramElement;
import scomp.x86.ir.Add;
import scomp.x86.ir.And;
import scomp.x86.ir.Call;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.Enter;
import scomp.x86.ir.IntegerValue;
import scomp.x86.ir.Label;
import scomp.x86.ir.LabelOperand;
import scomp.x86.ir.LabelRelativeAddress;
import scomp.x86.ir.Lea;
import scomp.x86.ir.Leave;
import scomp.x86.ir.Mov;
import scomp.x86.ir.Push;
import scomp.x86.ir.Register;
import scomp.x86.ir.RegisterRelativeAddress;
import scomp.x86.ir.Ret;

/**
 * This is the Mac OS X implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class MacOSXTranslator extends AbstractTranslator {
	
	private final Map<String, List<AbstractProgramElement>> callouts;
	
	public MacOSXTranslator() {
		this.callouts = new TreeMap<String, List<AbstractProgramElement>>();
	}
	
	@Override
	protected final void addPushLabel(final String labelName) {
		this.getProcedureSection().add(new Lea(this.getDefaultSizeSuffix(), new LabelRelativeAddress(this.getDefaultSizeSuffix(), labelName), new Register(RAX)));
		this.getProcedureSection().add(new Push(this.getDefaultSizeSuffix(), new Register(RAX)));
	}
	
	@Override
	protected final void addSectionsToProgram(final List<AbstractProgramElement> result) {
		result.addAll(this.getStringSection());
		
		for (final List<AbstractProgramElement> callout : this.callouts.values()) {
			result.addAll(callout);
		}
		
		result.addAll(this.getProcedureSection());
	}
	
	@Override
	protected final void afterChildren(final MethodCallout methodCallout) {
		final int argumentCount = methodCallout.getArguments().size();
		final String callName = "callout_" + methodCallout.getMethodName() + "_" + argumentCount;
		
		if (!this.callouts.containsKey(callName)) {
			final List<AbstractProgramElement> callout = new ArrayList<AbstractProgramElement>();
			
			callout.add(new Label(callName));
			callout.add(new Enter(this.getDefaultSizeSuffix(), new CompositeIntegerValue(this.getDefaultVariableByteCount(), 0), new IntegerValue(0)));
			callout.add(new And(this.getDefaultSizeSuffix(), new IntegerValue(-16), new Register(RSP)));
			if (argumentCount >= 7) {
				// TODO
			}
			if (argumentCount >= 6) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 56, RBP), new Register(R9)));
			}
			if (argumentCount >= 5) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 48, RBP), new Register(R8)));
			}
			if (argumentCount >= 4) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 40, RBP), new Register(RCX)));
			}
			if (argumentCount >= 3) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 32, RBP), new Register(RDX)));
			}
			if (argumentCount >= 2) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 24, RBP), new Register(RSI)));
			}
			if (argumentCount >= 1) {
				callout.add(new Mov(this.getDefaultSizeSuffix(), new RegisterRelativeAddress(this.getDefaultSizeSuffix(), 16, RBP), new Register(RDI)));
			}
			callout.add(new Mov(this.getDefaultSizeSuffix(), new IntegerValue(0), new Register(RAX)));
			callout.add(new Call(this.getDefaultSizeSuffix(), new LabelOperand("_" + methodCallout.getMethodName())));
			callout.add(new Leave(this.getDefaultSizeSuffix()));
			callout.add(new Ret(this.getDefaultSizeSuffix()));
			
			this.callouts.put(callName, callout);
		}
		
		this.getProcedureSection().add(new Call(this.getDefaultSizeSuffix(), new LabelOperand(callName)));
		this.getProcedureSection().add(new Add(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), argumentCount), new Register(this.getResizedName(RSP))));
	}
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_64;
	}
	
}
