package scomp;

import java.util.Collections;
import java.util.List;

/**
 * This class defines the root element of the tree.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public final class Program extends AbstractNode {
	
	private final List<AbstractFieldDeclaration> fieldDeclarations;
	
	private final List<MethodDeclaration> methodDeclarations;

	/**
	 * If {@code fieldDeclarations} or {@code methodDeclarations} is null, an empty list is used instead.
	 * 
	 * @param fieldDeclarations
	 * <br>Maybe null
	 * <br>Shared
	 * @param methodDeclarations
	 * <br>Maybe null
	 * <br>Shared
	 */
	public Program(final List<AbstractFieldDeclaration> fieldDeclarations, final List<MethodDeclaration> methodDeclarations) {
		this.fieldDeclarations = emptyIfNull(fieldDeclarations);
		this.methodDeclarations = emptyIfNull(methodDeclarations);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<AbstractFieldDeclaration> getFieldDeclarations() {
		return this.fieldDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<MethodDeclaration> getMethodDeclarations() {
		return this.methodDeclarations;
	}
	
	/**
	 * 
	 * @param <T> The type of the elements
	 * @param list
	 * <br>Maybe null
	 * @return
	 * <br>Not null
	 * <br>Maybe new
	 */
	@SuppressWarnings("unchecked")
	public static final <T> List<T> emptyIfNull(final List<T> list) {
		return (List<T>) (list == null ? Collections.emptyList() : list);
	}
	
}
