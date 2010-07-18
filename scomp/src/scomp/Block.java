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
	
	private final List<Statement> statements;
	
	/**
	 * 
	 * @param variables
	 * <br>Maybe null
	 * <br>Shared
	 * @param statements
	 * <br>Maybe null
	 * <br>Shared
	 */
	public Block(final List<VariableDeclaration> variableDeclarations, 
			final List<Statement> statements) {
		this.variableDeclarations = emptyIfNull(variableDeclarations);
		this.statements = emptyIfNull(statements);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public List<VariableDeclaration> getVariableDeclarations() {
		return variableDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public List<Statement> getStatements() {
		return statements;
	}	
	
}
