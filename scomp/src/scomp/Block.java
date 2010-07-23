package scomp;

import static scomp.Tools.*;

import java.util.List;

/**
 * This class defines a Decaf Block.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */
public final class Block extends AbstractNode {

	private final List<VariableDeclaration> variableDeclarations;
	
	private final List<AbstractStatement> abstractStatements;
	
	/**
	 * 
	 * @param variableDeclarations
	 * <br>Maybe null
	 * <br>Shared
	 * @param abstractStatements
	 * <br>Maybe null
	 * <br>Shared
	 */
	public Block(final List<VariableDeclaration> variableDeclarations, 
			final List<AbstractStatement> abstractStatements) {
		this.variableDeclarations = emptyIfNull(variableDeclarations);
		this.abstractStatements = emptyIfNull(abstractStatements);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<VariableDeclaration> getVariableDeclarations() {
		return this.variableDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<AbstractStatement> getStatements() {
		return this.abstractStatements;
	}	
	
}
