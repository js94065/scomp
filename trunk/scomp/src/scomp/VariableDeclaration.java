package scomp;

import java.util.List;

/**
 * This class defines a Decaf Variable Declaration.
 * 
 * @author js94065 (creation 2010-07-15)
 *
 */

public final class VariableDeclaration extends AbstractTypedEntityDeclaration {

	/**
	 * 
	 * @param type
	 * @param identifier
	 */
	public VariableDeclaration(final Class<?> type, String identifier) {
		super(type, identifier);
	}
	
}
