package scomp.x86.ir;

import static scomp.Tools.join;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the root element of the tree.
 * 
 * @author codistmonk (creation 2010-09-26)
 *
 */
public final class Program extends AbstractNode {
	
	private final List<AbstractProgramElement> elements;
	
	public Program() {
		this.elements = new ArrayList<AbstractProgramElement>();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<AbstractProgramElement> getElements() {
		return this.elements;
	}
	
	@Override
	public final String toString() {
		return join("\n", this.getElements()) + "\n";
	}
	
}
