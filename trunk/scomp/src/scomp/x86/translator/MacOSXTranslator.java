package scomp.x86.translator;

import static scomp.x86.ir.Register.Name.RSP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.AbstractProgramElement;
import scomp.x86.ir.Add;
import scomp.x86.ir.Call;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.Label;
import scomp.x86.ir.LabelOperand;
import scomp.x86.ir.Leave;
import scomp.x86.ir.Register;
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
