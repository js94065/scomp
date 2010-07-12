package scomp;

/**
 * This is the base class for all elements of the intermediate representation tree.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public abstract class AbstractNode {
	
	private final AbstractNode parent;
	
	/**
	 * 
	 * @param parent 
	 * <br>Maybe null
	 * <br>Shared
	 */
	public AbstractNode(final AbstractNode parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @return
	 * <br>Maybe null
	 * <br>Shared
	 */
	public final AbstractNode getParent() {
		return this.parent;
	}
	
}
