package scomp.x86.translator;

import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;
import scomp.x86.ir.Register.Name;

/**
 * This is the Linux implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class LinuxTranslator extends AbstractTranslator {
	
	@Override
	protected final void x86MOV(final String labelName, final Name registerName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected final void x86PUSH(final String labelName) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected final void afterChildren(final MethodCallout methodCallout) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_64;
	}
	
}
