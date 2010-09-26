package scomp.x86.translator;

import scomp.x86.ir.Program;

/**
 * This is the base class for the OS-specific translators.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public abstract class AbstractTranslator /*implements Visitor*/ {
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 */
	public final Program getProgram() {
		// TODO
		return null;
	}
	
}
