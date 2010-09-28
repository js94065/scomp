package scomp.x86.ir;

import static scomp.x86.ir.Register.Name.EAX;
import static scomp.x86.ir.Register.Name.EBP;
import static scomp.x86.ir.Register.Name.ECX;
import static scomp.x86.ir.Register.Name.EIP;
import static scomp.x86.ir.Register.Name.ESP;

import scomp.x86.ir.Register.Name;

/**
 * This is the base class for x86 instructions.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public abstract class AbstractInstruction extends AbstractProgramElement {
	
	private final String sizeSuffix;
	
	/**
	 * 
	 * @param sizeSuffix
	 * <br>Not null
	 * <br>Shared
	 */
	protected AbstractInstruction(final String sizeSuffix) {
		this.sizeSuffix = sizeSuffix;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getSizeSuffix() {
		return this.sizeSuffix;
	}
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_UNSPECIFIED = "";
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_32 = "l";
	
	/**
	 * {@value}.
	 */
	public static final String SIZE_SUFFIX_64 = "q";
	
	/**
	 * 
	 * @param name
	 * <br>Not null
	 * @param size
	 * <br>Not null
	 * <br>Range: { {@link AbstractInstruction#SIZE_SUFFIX_32}, {@link AbstractInstruction#SIZE_SUFFIX_64} }
	 * @return
	 * <br>Not null
	 */
	public static final Name getResizedName(final Name name, final String size) {
		if (SIZE_SUFFIX_32.equals(size)) {
			switch (name) {
			case RAX:
				return EAX;
			case RCX:
				return ECX;
			case RSP:
				return ESP;
			case RBP:
				return EBP;
			case RIP:
				return EIP;
			default:
				break;
			}
		}
		
		return name;
	}
	
}
