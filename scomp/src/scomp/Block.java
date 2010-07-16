package scomp;

/**
 * This class defines a Decaf Block.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */
import static scomp.Tools.*;

/**
 * This class defines a Decaf method declaration.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */

import java.util.List;

public final class Block extends AbstractNode {

	private final List<VariableDeclarations> variableDeclarations;
	
	private final Class<?> statementDeclarations;
	
	/**
	 * 
	 * @param variables
	 * <br>Maybe null
	 * <br>Shared
	 * @param statements
	 * <br>Maybe null
	 * <br>Shared
	 */
	public Block(final List<VariableDeclarations> variableDeclarations, 
			final Class<?> statementDeclarations) {
		this.variableDeclarations = emptyIfNull(variableDeclarations);
		this.statementDeclarations = statementDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<VariableDeclarations> getVariablesDeclarations() {
		return this.variableDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Class<?> getStatementDeclarations() {
		return this.statementDeclarations;
	}

}
