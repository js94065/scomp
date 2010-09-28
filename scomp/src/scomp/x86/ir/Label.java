package scomp.x86.ir;

/**
 * This class represents a x86 label.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public final class Label extends AbstractProgramElement {
	
	private final String name;
	
	/**
	 * 
	 * @param name
	 * <br>Not null
	 * <br>Shared
	 */
	public Label(final String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getName() {
		return this.name;
	}
	
	@Override
	public final String toString() {
		return this.getName() + ":";
	}
	
}
