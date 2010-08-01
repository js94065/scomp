package scomp.ir;

import static org.junit.Assert.*;

import static scomp.DecafParserTest.*;
import static scomp.Tools.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import scomp.DecafParserSymbols;
import scomp.DecafScannerTest;
import scomp.DecafToken;
import scomp.ir.AbstractExpression;
import scomp.ir.AbstractFieldDeclaration;
import scomp.ir.AbstractMethodCall;
import scomp.ir.AbstractStatement;
import scomp.ir.ArrayFieldDeclaration;
import scomp.ir.AssignmentStatement;
import scomp.ir.BinaryOperationExpression;
import scomp.ir.Block;
import scomp.ir.BlockStatement;
import scomp.ir.BreakStatement;
import scomp.ir.ContinueStatement;
import scomp.ir.FieldDeclaration;
import scomp.ir.IdentifierLocation;
import scomp.ir.IfStatement;
import scomp.ir.IntLiteral;
import scomp.ir.LiteralExpression;
import scomp.ir.LocationExpression;
import scomp.ir.MethodCall;
import scomp.ir.MethodCallExpression;
import scomp.ir.MethodDeclaration;
import scomp.ir.MinusExpression;
import scomp.ir.NegationExpression;
import scomp.ir.ParameterDeclaration;
import scomp.ir.Program;
import scomp.ir.ReturnStatement;
import scomp.ir.VariableDeclaration;
import scomp.ir.WhileStatement;

/**
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public class DecafIRTest {
	
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
	
	@Test
	public final void testProgramWithAMethodWithALoop() throws Exception {
		final Program expectedProgram = program(
				null,
				methods(
						method(int.class, "factorial", parameters(parameter(int.class, "n")),
								block(
										variables(
												variable(int.class, "result"),
												variable(int.class, "i")
										),
										statements(
												assign("result", expression(1)),
												assign("i", expression("n")),
												whileStatement(operation(expression("i"), ">", expression(1)),
														block(
																null,
																statements(
																		assign("result", operation(expression("result"), "*", expression("i"))),
																		assign("i", operation(expression("i"), "-", expression(1))),
																		ifStatement(not(operation(minus(expression("i")), "!=", minus(expression(1)))),
																				block(
																						null,
																						statements(
																								breakStatement()
																						)
																				),
																				block(
																						null,
																						statements(
																								continueStatement()
																						)
																				)
																		)
																)
														)
												),
												returnStatement(expression("result"))
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_METHOD_WITH_A_LOOP));
	}
	
	@Test
	public final void testProgramWithMethodWithVariousExpressions() throws Exception {
		final Program expectedProgram = program(
				null,
				methods(
						method(void.class, "f", null,
								block(
										variables(
												variable(int.class, "a"),
												variable(int.class, "b"),
												variable(boolean.class, "c"),
												variable(boolean.class, "d")), 
												statements(
														assign("a", minus(expression(42))),
														assign("b", expression(0x33)),
														assign("a", expression('a')),
														assign("a", operation(expression("a"), "+", expression("b"))),
														assign("a", operation(expression("a"), "-", expression("b"))),
														assign("a", operation(expression("a"), "*", expression("b"))),
														assign("a", operation(expression("a"), "/", expression("b"))),
														assign("a", operation(expression("a"), "%", expression("b"))),
														assign("a", operation(expression("a"), "<<", expression("b"))),
														assign("a", operation(expression("a"), ">>", expression("b"))),
														assign("a", operation(expression("a"), ">>>", expression("b"))),
														assign("c", expression(true)),
														assign("d", expression(false)),
														assign("c", not(expression("d"))),
														assign("c", operation(expression("a"), "<", expression("b"))),
														assign("c", operation(expression("a"), ">", expression("b"))),
														assign("c", operation(expression("a"), "<=", expression("b"))),
														assign("c", operation(expression("a"), ">=", expression("b"))),
														assign("c", operation(expression("c"), "==", expression("d"))),
														assign("c", operation(expression("c"), "!=", expression("d"))),
														assign("c", operation(expression("c"), "&&", expression("d"))),
														assign("c", operation(expression("c"), "||", expression("d"))),
														assign("a", operation(operation(expression("a"), "+", expression("b")), "+", expression("a"))),
														assign("a", operation(operation(expression("a"), "+", expression("b")), "-", expression("a"))),
														assign("a", operation(expression("a"), "+", operation(expression("b"), "*", expression("a")))),
														assign("a", operation(expression("a"), "+", operation(expression("b"), "/", expression("a")))),
														assign("a", operation(expression("a"), "+", operation(expression("b"), "%", expression("a")))),
														assign("a", operation(operation(expression("a"), "+", expression("b")), "<<", expression("a"))),
														assign("a", operation(operation(expression("a"), "+", expression("b")), ">>", expression("a"))),
														assign("a", operation(operation(expression("a"), "+", expression("b")), ">>>", expression("a"))),
														assign("c", operation(operation(expression("a"), "<", expression("b")), "==", expression("d"))),
														assign("c", operation(operation(expression("a"), ">", expression("b")), "!=", expression("d"))),
														assign("c", operation(operation(expression("a"), "<=", expression("b")), "&&", expression("d"))),
														assign("c", operation(operation(expression("a"), ">=", expression("b")), "||", expression("d")))
														)
												)
										)
						)
				);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_METHOD_WITH_VARIOUS_EXPRESSIONS));
	}
	
	@Test
	public final void testProgramUsingPrecedence() {
		final Program expectedProgram = program(
				fields(
						field(boolean.class, "b")
				),
				methods(
						method(void.class, "f", null, block(null,
								statements(
										assign("b", operation(
												operation(expression(43), "*", expression(42)),
												"+",
												expression(0)))
								)
							)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_USING_PRECEDENCE));
	}
	
	@Test
	public final void testProgramWithMethodWithParametersAndComplexBlock() {
		final Program expectedProgram = program(
				null,
				methods(
						method(boolean.class, "f",
								parameters(
										parameter(int.class, "a"), 
										parameter(boolean.class, "b")
								),
								block(
										variables(
												variable(int.class, "c"),
												variable(int.class, "d"),
												variable(boolean.class, "e"),
												variable(boolean.class, "f")
										),
										statements(
												assign("c", expression("a")),
												assign("d", operation(expression("a"), "+", expression("c"))),
												assign("e", operation(expression("a"), "<", expression("d"))),
												ifStatement(expression("e"),
														block(null,
																statements(
																		assign("f", (
																				operation(expression("b"), "&&",
																						operation(
																							operation(
																									operation(
																											operation(expression("a"), "/", expression("c")),
																											"-",
																											operation(expression("d"), "*", expression("d"))
																									),
																									"%",
																									expression(42)
																							),
																							"==",
																							expression(0)
																						)
																				)
																			)
																		)
																)
														),
														block(null,
																statements( assign("f",expression(false)) )
														)
												),
												calloutStatement(
														"printf",
														arguments(
																calloutArgument("Hello World!")
														)
												),
												whileStatement(
														operation(expression("c"), "!=", expression(0)),
														block(
																null,
																statements(
																	assign("c", operation(expression("c"), "/", expression(2))),
																	ifStatement(operation(expression("c"), "<", expression(0)),
																			block(
																					null,
																					statements(breakStatement())
																			),
																			block(
																					null,
																					statements(continueStatement())
																			)
																	)
															)
														)
												),
												returnStatement(expression("f"))
										)
								)
						)
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_METHOD_WITH_PARAMETERS_AND_COMPLEX_BLOCK));
	}
	
	/**
	 * {@value}.
	 */
	public static final String PROGRAM_USING_PRECEDENCE =
		"class Program {\n" +
		"\n" + 
		"	int b; void f() { b = 43 * 42 + 0; }" +
		"\n" +
		"}";
	
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
	private static final Program program(final List<AbstractFieldDeclaration> fields, final List<MethodDeclaration> methods) {
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
	private static final List<AbstractFieldDeclaration> fields(final AbstractFieldDeclaration... fields) {
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
		return new ArrayFieldDeclaration(elementType, identifier(identifier), elementCount);
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
		return new FieldDeclaration(type, identifier(identifier));
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
			final List<ParameterDeclaration> parameters, final Block block) {
		return new MethodDeclaration(returnType, identifier(identifier), parameters, block);
	}
	
	/**
	 * 
	 * @param fields
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<ParameterDeclaration> parameters(final ParameterDeclaration... fields) {
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
	private static final ParameterDeclaration parameter(final Class<?> type, final String identifier) {
		return new ParameterDeclaration(type, identifier(identifier));
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
		return new VariableDeclaration(type, identifier(identifier));
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
	* @param value
	* <br> Range: any char
	* @return
	* <br> Not null
	* <br> New
	*/
	private static final LiteralExpression expression(final char value) {
		return new LiteralExpression(new CharLiteral(value));
	}
	
	/**
	*
	* @param value
	* <br> Range: true or false
	* @return
	* <br> Not null
	* <br> New
	*/
	private static final LiteralExpression expression(final boolean value) {
		return new LiteralExpression(new BooleanLiteral(value));
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
	private static final BinaryOperationExpression operation(final AbstractExpression left, final String operator, final AbstractExpression right) {
		return new BinaryOperationExpression(left, operator, right);
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
	 * @param condition
	 * <br>Not null
	 * <br>Shared
	 * @param block
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final WhileStatement whileStatement(final AbstractExpression condition, final Block block) {
		return new WhileStatement(condition, block);
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
	 * @param methodName
	 * <br>Not null
	 * <br>Shared
	 * @param arguments 
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final MethodCallStatement calloutStatement(final String methodName, final List<AbstractCalloutArgument> arguments) {
		return new MethodCallStatement(new MethodCallout(methodName, arguments));
	}
	
	/**
	 * 
	 * @param string
	 * <br>Not null
	 * <br>Shared
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final StringCalloutArgument calloutArgument(final String string) {
		return new StringCalloutArgument(string);
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
	 * @param arguments
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final List<AbstractCalloutArgument> arguments(final AbstractCalloutArgument... arguments) {
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
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	public static final BreakStatement breakStatement() {
		return new BreakStatement();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	public static final ContinueStatement continueStatement() {
		return new ContinueStatement();
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
	public static final NegationExpression not(final AbstractExpression expression) {
		return new NegationExpression(expression);
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
	public static final MinusExpression minus(final AbstractExpression expression) {
		return new MinusExpression(expression);
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
	public static final DecafToken identifier(final String identifier) {
		return DecafScannerTest.token(DecafParserSymbols.IDENTIFIER, identifier);
	}
	
}
