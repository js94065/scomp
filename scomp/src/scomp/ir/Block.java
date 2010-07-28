package scomp.ir;

import static scomp.Tools.*;

import java.util.List;

import scomp.Tools;

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
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.beginVisit(this);
		
		for (final VariableDeclaration variableDeclaration : this.getVariableDeclarations()) {
			variableDeclaration.accept(visitor);
		}
		
		for (final AbstractStatement statement : this.getStatements()) {
			statement.accept(visitor);
		}
		
		visitor.endVisit(this);
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
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"variableDeclarations{" + this.getVariableDeclarations() + "} " +
				"statements{" + this.getStatements() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getVariableDeclarations().hashCode() + this.getStatements().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final Block that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getVariableDeclarations().equals(that.getVariableDeclarations()) &&
				this.getStatements().equals(that.getStatements());
	}
	
}
