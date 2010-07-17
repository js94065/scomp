package scomp;

import java.util.List;

/**
 * This class defines a Decaf Variable Declaration.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */

public final class VariableDeclaration extends AbstractNode {

	private final Class<?> type;
	
	private final List<String> identifierList;
	
	/**
	 * 
	 * @param type
	 * @param identifier
	 */
	public VariableDeclaration(final Class<?> type, List<String> identifierList) {
		this.type = type;
		this.identifierList = identifierList;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Class<?> getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<String> getIdentifierList() {
		return this.identifierList;
	}

}
