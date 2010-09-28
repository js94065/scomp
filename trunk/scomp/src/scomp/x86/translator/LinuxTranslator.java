package scomp.x86.translator;

import scomp.x86.ir.AbstractInstruction;

/**
 * This is the Linux implementation of the Decaf -&gt; x86 translator.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class LinuxTranslator extends AbstractTranslator {
	
	@Override
	protected final String getDefaultSizeSuffix() {
		return AbstractInstruction.SIZE_SUFFIX_64;
	}
	
}
