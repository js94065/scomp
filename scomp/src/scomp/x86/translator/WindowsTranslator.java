package scomp.x86.translator;

import static scomp.x86.ir.Register.Name.*;
import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.Add;
import scomp.x86.ir.Call;
import scomp.x86.ir.CompositeIntegerValue;
import scomp.x86.ir.Label;
import scomp.x86.ir.LabelOperand;
import scomp.x86.ir.LabelRelativeAddress;
import scomp.x86.ir.Mov;
import scomp.x86.ir.Push;
import scomp.x86.ir.Register;
import scomp.x86.ir.Register.Name;

/**
 * This is the Windows implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk, js94065 (creation 2010-09-26)
 *
 */
public final class WindowsTranslator extends AbstractTranslator {
	
	@Override
	protected final void x86MOV(final String labelName, final Name registerName) {
		this.getProcedureSection().add(new Mov(this.getDefaultSizeSuffix(),
				new LabelOperand("$"+labelName),
				new Register(this.getResizedName(registerName))));
	}
	
	@Override
	protected final void x86PUSH(final String labelName) {
		this.x86MOV(labelName, EAX);
		this.x86PUSH(EAX);
	}
	
	@Override
	protected final void afterChildren(final MethodCallout methodCallout) {
		final int argumentCount = methodCallout.getArguments().size();
		final String callName = "_" + methodCallout.getMethodName();
		
		this.getProcedureSection().add(new Call(this.getDefaultSizeSuffix(), new LabelOperand(callName)));
		this.getProcedureSection().add(new Add(this.getDefaultSizeSuffix(),
				new CompositeIntegerValue(this.getDefaultVariableByteCount(), argumentCount), new Register(this.getResizedName(RSP))));
		
	}
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_32;
	}
	
}
