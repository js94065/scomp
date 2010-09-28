package scomp.x86.translator;

import scomp.ir.MethodCallout;
import scomp.x86.ir.AbstractInstruction;

/**
 * This is the Windows implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class WindowsTranslator extends AbstractTranslator {
	
	@Override
	protected final void afterChildren(final MethodCallout methodCallout) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_32;
	}
	
}
