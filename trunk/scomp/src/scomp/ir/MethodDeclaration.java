package scomp.ir;

import static scomp.Tools.*;

import java.util.List;

import scomp.Tools;

/**
 * This class defines a Decaf method declaration.
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public final class MethodDeclaration extends AbstractTypedEntityDeclaration {
	
	private final List<ParameterDeclaration> parameterDeclarations;
	
	private final Block block;
	
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
	 * @param block 
	 * <br>Not null
	 * <br>Shared
	 */
	public MethodDeclaration(final Class<?> type, final String identifier,
			final List<ParameterDeclaration> parameterDeclarations,
			final Block block) {
		super(type, identifier);
		this.parameterDeclarations = emptyIfNull(parameterDeclarations);
		this.block = block;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final List<ParameterDeclaration> getParameterDeclarations() {
		return this.parameterDeclarations;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final Block getBlock() {
		return this.block;
	}
	
	@Override
	protected final int doHashCode() {
		return super.doHashCode() + this.getParameterDeclarations().hashCode() + this.getBlock().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final MethodDeclaration that = Tools.cast(this.getClass(), other);
		
		return super.doEquals(other) &&
				this.getParameterDeclarations().equals(that.getParameterDeclarations()) &&
				this.getBlock().equals(that.getBlock());
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"returnType{" + this.getType().getName() + "} " +
				"identifier{" + this.getIdentifier() + "} " +
				"block{" + this.getBlock() + "}" +
				"}";
	}
	
}
