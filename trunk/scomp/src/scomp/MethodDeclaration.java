package scomp;

import static scomp.Tools.*;

import java.util.List;

/**
 * This class defines a Decaf method declaration.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public final class MethodDeclaration extends AbstractTypedEntityDeclaration {
	
	private final List<FieldDeclaration> parameterDeclarations;
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param parameterDeclarations
	 * <br>Not null
	 * <br>Shared
	 */
	public MethodDeclaration(final Class<?> type, final String identifier,
			final List<FieldDeclaration> parameterDeclarations) {
		super(type, identifier);
		this.parameterDeclarations = emptyIfNull(parameterDeclarations);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<FieldDeclaration> getParameterDeclarations() {
		return this.parameterDeclarations;
	}
	
}
