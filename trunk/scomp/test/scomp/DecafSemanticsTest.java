package scomp;

import static org.junit.Assert.*;

import static scomp.DecafParserTest.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public class DecafSemanticsTest {
	
	@Test
	public final void testSmallestProgram() throws Exception {
		final Program expectedProgram = program(
				null,
				null
		);
		
		assertEquals(expectedProgram, parse(SMALLEST_PROGRAM));
	}
	
	@Test
	public final void testProgramWithAField() throws Exception {
		final Program expectedProgram = program(
				fields(
						field(int.class, "x")
				),
				null
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_FIELD));
	}
	
	@Test
	public final void testProgramWithAMethod() throws Exception {
		final Program expectedProgram = program(
				null,
				methods(
						method(void.class, "f", null,
								block(
										null,
										null
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_METHOD));
	}
	
	@Test
	public final void testProgramWithAFieldAndAMethod() throws Exception {
		final Program expectedProgram = program(
				fields(
						field(int.class, "x", 42)
				),
				methods(
						method(boolean.class, "f", parameters(parameter(int.class, "y")),
								block(
										null,
										statements(
												returnStatement(operation(expression("y"), "==", expression(42)))
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_FIELD_AND_A_METHOD));
	}
	
	@Test
	public final void testProgramWithFieldsAndMethods() throws Exception {
		final Program expectedProgram = program(
				fields(
						field(int.class, "x"),
						field(int.class, "y", 1),
						field(int.class, "z"),
						field(boolean.class, "a"),
						field(boolean.class, "b", 2),
						field(boolean.class, "c")
				),
				methods(
						method(void.class, "f", null,
								block(
										null,
										null
								)
						),
						method(int.class, "g", null,
								block(
										null,
										statements(
												returnStatement(expression(42))
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_FIELDS_AND_METHODS));
	}
	
	@Test
	public final void testProgramWithARecursiveMethod1() throws Exception {
		final Program expectedProgram = program(
				null,
				methods(
						method(int.class, "factorial", parameters(parameter(int.class, "n")),
								block(
										null,
										statements(
												ifStatement(operation(expression("n"), "<=", expression(0)),
														block(
																null,
																statements(
																		returnStatement(expression(1))
																)
														),
														null
												),
												returnStatement(operation(expression("n"), "*", expression(call("factorial", arguments(operation(expression("n"), "-", expression(1)))))))
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_RECURSIVE_METHOD_1));
	}
	
	@Test
	public final void testProgramWithARecursiveMethod2() throws Exception {
		final Program expectedProgram = program(
				null,
				methods(
						method(int.class, "factorial", parameters(parameter(int.class, "n")),
								block(
										null,
										statements(
												ifStatement(operation(expression("n"), "<=", expression(0)),
														block(
																null,
																statements(
																		returnStatement(expression(1))
																)
														),
														null
												),
												blockStatement(
														variables(
																variable(int.class, "result")
														),
														statements(
																assign("result", expression("n")),
																assign("result", operation(expression("result"), "*", expression(call("factorial", arguments(operation(expression("n"), "-", expression(1))))))),
																returnStatement(expression("result"))
														)
												)
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_RECURSIVE_METHOD_2));
	}
	
	/**
	 * 
	 * @param fields
	 * <br>Maybe null
	 * <br>Shared
	 * @param methods
	 * <br>Maybe null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final Program program(final List<AbstractTypedEntityDeclaration> fields, final List<MethodDeclaration> methods) {
		return new Program(fields, methods);
	}
	
	/**
	 * 
	 * @param fields
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<AbstractTypedEntityDeclaration> fields(final AbstractTypedEntityDeclaration... fields) {
		return Arrays.asList(fields);
	}
	
	/**
	 * 
	 * @param elementType
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param elementCount
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final ArrayFieldDeclaration field(final Class<?> elementType, final String identifier, final int elementCount) {
		return new ArrayFieldDeclaration(elementType, identifier, elementCount);
	}
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final FieldDeclaration field(final Class<?> type, final String identifier) {
		return new FieldDeclaration(type, identifier);
	}
	
	/**
	 * 
	 * @param methods
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<MethodDeclaration> methods(final MethodDeclaration... methods) {
		return Arrays.asList(methods);
	}
	
	/**
	 * 
	 * @param returnType
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param parameters
	 * <br>Maybe null
	 * <br>Shared
	 * @param block
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final MethodDeclaration method(final Class<?> returnType, final String identifier,
			final List<FieldDeclaration> parameters, final Block block) {
		return new MethodDeclaration(returnType, identifier, parameters, block);
	}
	
	/**
	 * 
	 * @param fields
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<FieldDeclaration> parameters(final FieldDeclaration... fields) {
		return Arrays.asList(fields);
	}
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final FieldDeclaration parameter(final Class<?> type, final String identifier) {
		return new FieldDeclaration(type, identifier);
	}
	
	/**
	 * 
	 * @param variables
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<VariableDeclaration> variables(final VariableDeclaration... variables) {
		return Arrays.asList(variables);
	}
	
	/**
	 * 
	 * @param type
	 * <br>Not null
	 * <br>Shared
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final VariableDeclaration variable(final Class<?> type, final String identifier) {
		return new VariableDeclaration(type, identifier);
	}
	
	/**
	 * 
	 * @param variables
	 * <br>Not null
	 * <br>Shared
	 * @param statements
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final Block block(final List<VariableDeclaration> variables, final List<AbstractStatement> statements) {
		return new Block(variables, statements);
	}
	
	/**
	 * 
	 * @param variables
	 * <br>Not null
	 * <br>Shared
	 * @param statements
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final BlockStatement blockStatement(final List<VariableDeclaration> variables, final List<AbstractStatement> statements) {
		return new BlockStatement(new Block(variables, statements));
	}
	
	/**
	 * 
	 * @param statements
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<AbstractStatement> statements(final AbstractStatement... statements) {
		return Arrays.asList(statements);
	}
	
	/**
	 * 
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final ReturnStatement returnStatement(final AbstractExpression expression) {
		return new ReturnStatement(expression);
	}
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final LocationExpression expression(final String identifier) {
		return new LocationExpression(new IdentifierLocation(identifier));
	}
	
	/**
	 * 
	 * @param value
	 * <br>Range: any integer
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final LiteralExpression expression(final int value) {
		return new LiteralExpression(new IntLiteral(value));
	}
	
	/**
	 * 
	 * @param methodCall
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final MethodCallExpression expression(final AbstractMethodCall<?> methodCall) {
		return new MethodCallExpression(methodCall);
	}
	
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
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final BinaryOperation operation(final AbstractExpression left, final String operator, final AbstractExpression right) {
		return new BinaryOperation(left, operator, right);
	}
	
	/**
	 * 
	 * @param condition
	 * <br>Not null
	 * <br>Shared
	 * @param thenBlock
	 * <br>Not null
	 * <br>Shared
	 * @param elseBlock
	 * <br>Maybe null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final IfStatement ifStatement(final AbstractExpression condition, final Block thenBlock, final Block elseBlock) {
		return new IfStatement(condition, thenBlock, elseBlock);
	}
	
	/**
	 * 
	 * @param methodName
	 * <br>Not null
	 * <br>Shared
	 * @param arguments
	 * <br>Maybe null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final MethodCall call(final String methodName, final List<AbstractExpression> arguments) {
		return new MethodCall(methodName, arguments);
	}
	
	/**
	 * 
	 * @param arguments
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<AbstractExpression> arguments(final AbstractExpression... arguments) {
		return Arrays.asList(arguments);
	}
	
	/**
	 * 
	 * @param identifier
	 * <br>Not null
	 * <br>Shared
	 * @param expression
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final AssignmentStatement assign(final String identifier, final AbstractExpression expression) {
		return new AssignmentStatement(new IdentifierLocation(identifier), expression);
	}
	
}
