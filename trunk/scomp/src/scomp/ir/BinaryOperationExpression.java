package scomp.ir;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import scomp.Tools;

/**
 * This class defines a Decaf binary operation expression.
 * 
 * @author codistmonk (creation 2010-07-23)
 *
 */
public final class BinaryOperationExpression extends AbstractExpression {
	
	private final AbstractExpression left;
	
	private final String operator;
	
	private final AbstractExpression right;
	
	/**
	 * 
	 * @param left
	 * <br>Not null
	 * <br>Shared
	 * @param operator
	 * <br>Not null
	 * <br>Shared
	 * @param right
	 * <br>Not null
	 * <br>Shared
	 */
	public BinaryOperationExpression(final AbstractExpression left,
			final String operator, final AbstractExpression right) {
		super(left.getToken());
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	@Override
	public final void accept(final Visitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getLeft() {
		return this.left;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getOperator() {
		return this.operator;
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final AbstractExpression getRight() {
		return this.right;
	}
	
	@Override
	public final Class<?> getType() {
		return OPERATORS_WITH_BOOLEAN_RESULT.contains(this.getOperator()) ? boolean.class : int.class;
	}
	
	@Override
	public final String toString() {
		return
				this.getClass().getSimpleName() + "{" +
				"left{" + this.getLeft() + "} " +
				"operator{" + this.getOperator() + "} " +
				"right{" + this.getRight() + "}" +
				"}";
	}
	
	@Override
	protected final int doHashCode() {
		return this.getLeft().hashCode() + this.getOperator().hashCode() + this.getRight().hashCode();
	}
	
	@Override
	protected final boolean doEquals(final Object other) {
		final BinaryOperationExpression that = Tools.cast(this.getClass(), other);
		
		return this == that ||
				that != null &&
				this.getLeft().equals(that.getLeft()) &&
				this.getOperator().equals(that.getOperator()) &&
				this.getRight().equals(that.getRight());
	}
	
	/**
	 * {"<", ">", "<=", ">=", "==", "!=", "&&", "||"}.
	 */
	public static final Set<String> OPERATORS_WITH_BOOLEAN_RESULT = set("<", ">", "<=", ">=", "==", "!=", "&&", "||");
	
	/**
	 * {"+", "-", "*", "/", "%", "<<", ">>", ">>>", "<", ">", "<=", ">="}.
	 */
	public static final Set<String> OPERATORS_WITH_INT_OPERANDS = set("+", "-", "*", "/", "%", "<<", ">>", ">>>", "<", ">", "<=", ">=");
	
	/**
	 * {"==", "!="}.
	 */
	public static final Set<String> EQUALITY_OPERATORS = set("==", "!=");
	
	/**
	 * 
	 * @param <T> The common type of the elements
	 * @param elements
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final <T> Set<T> set(final T... elements) {
		return Collections.unmodifiableSet(new LinkedHashSet<T>(Arrays.asList(elements)));
	}
	
}
