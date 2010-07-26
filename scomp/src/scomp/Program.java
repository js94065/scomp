package scomp;

import static scomp.Tools.*;

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
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"fieldDeclarations{" + this.getFieldDeclarations() + "} " +
				"methodDeclarations{" + this.getMethodDeclarations() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getFieldDeclarations().hashCode() + this.getMethodDeclarations().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final Program that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getFieldDeclarations().equals(that.getFieldDeclarations()) &&
				this.getMethodDeclarations().equals(that.getMethodDeclarations());
	}
	
}
