package scomp.x86.ir;

/**
 * This class represents x86 registers.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Register extends AbstractOperand {
	
	private final Name name;
	
	/**
	 * 
	 * @param name
	 * <br>Not null
	 * <br>Shared
	 */
	public Register(final Name name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Name getName() {
		return this.name;
	}
	
	@Override
	public final String toString() {
		return "%" + this.getName().toString().toLowerCase();
	}
	
	/**
	 * 
	 * @author codistmonk (creation 2010-09-28)
	 *
	 */
	public static enum Name {
		
		RAX, EAX,
		RDI,
		RSI,
		RDX, EDX,
		RCX, ECX, CL,
		R8,
		R9,
		RSP, ESP,
		RBP, EBP,
		RIP, EIP;
		
	}
	
}
