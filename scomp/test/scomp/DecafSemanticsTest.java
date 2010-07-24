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
		final Program program = parse(PROGRAM_WITH_A_RECURSIVE_METHOD_1);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		assertTrue(program.getFieldDeclarations().isEmpty());
		assertNotNull(program.getMethodDeclarations());
		{
			final MethodDeclaration methodDeclaration = program.getMethodDeclarations().get(0);
			
			assertEquals(int.class, methodDeclaration.getType());
			assertEquals("factorial", methodDeclaration.getIdentifier());
			{
				final FieldDeclaration parameterDeclaration = methodDeclaration.getParameterDeclarations().get(0);
				
				assertEquals(int.class, parameterDeclaration.getType());
				assertEquals("n", parameterDeclaration.getIdentifier());
			}
			assertEquals(1, methodDeclaration.getParameterDeclarations().size());
			
			final Block methodBlock = methodDeclaration.getBlock();
			
			assertNotNull(methodBlock);
			assertNotNull(methodBlock.getVariableDeclarations());
			assertTrue(methodBlock.getVariableDeclarations().isEmpty());
			assertNotNull(methodBlock.getStatements());
			{
				final IfStatement statement = (IfStatement) methodBlock.getStatements().get(0);
				
				assertNotNull(statement);
				{
					final BinaryOperation condition = (BinaryOperation) statement.getCondition();
					
					assertNotNull(condition);
					{
						final LocationExpression left = (LocationExpression) condition.getLeft();
						
						assertNotNull(left);
						
						final IdentifierLocation location = (IdentifierLocation) left.getLocation();
						
						assertNotNull(location);
						assertEquals("n", location.getIdentifier());
					}
					assertEquals("<=", condition.getOperator());
					{
						final LiteralExpression right = (LiteralExpression) condition.getRight();
						
						assertNotNull(right);
						
						final IntLiteral literal = (IntLiteral) right.getLiteral();
						
						assertNotNull(literal);
						assertEquals(0, literal.getValue());
					}
				}
				{
					final Block block = statement.getThenBlock();
					
					assertNotNull(block);
					assertNotNull(block.getVariableDeclarations());
					assertTrue(block.getVariableDeclarations().isEmpty());
					assertNotNull(block.getStatements());
					
					final ReturnStatement returnStatement = (ReturnStatement) block.getStatements().get(0);
					
					assertNotNull(returnStatement);
					
					final LiteralExpression returnExpression = (LiteralExpression) returnStatement.getExpression();
					
					assertNotNull(returnExpression);
					
					final IntLiteral literal = (IntLiteral) returnExpression.getLiteral();
					
					assertNotNull(literal);
					assertEquals(1, literal.getValue());
				}
				assertNull(statement.getElseBlock());
			}
			{
				final ReturnStatement statement = (ReturnStatement) methodBlock.getStatements().get(1);
				
				assertNotNull(statement);
			}
			assertEquals(2, methodBlock.getStatements().size());
		}
		assertEquals(1, program.getMethodDeclarations().size());
	}
	
	@Test
	public final void testProgramWithARecursiveMethod2() throws Exception {
		final Program program = parse(PROGRAM_WITH_A_RECURSIVE_METHOD_2);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		assertTrue(program.getFieldDeclarations().isEmpty());
		assertNotNull(program.getMethodDeclarations());
		{
			final MethodDeclaration methodDeclaration = program.getMethodDeclarations().get(0);
			
			assertEquals(int.class, methodDeclaration.getType());
			assertEquals("factorial", methodDeclaration.getIdentifier());
			{
				final FieldDeclaration parameterDeclaration = methodDeclaration.getParameterDeclarations().get(0);
				
				assertEquals(int.class, parameterDeclaration.getType());
				assertEquals("n", parameterDeclaration.getIdentifier());
			}
			assertEquals(1, methodDeclaration.getParameterDeclarations().size());
			
			final Block methodBlock = methodDeclaration.getBlock();
			
			assertNotNull(methodBlock);
			assertNotNull(methodBlock.getVariableDeclarations());
			assertTrue(methodBlock.getVariableDeclarations().isEmpty());
			assertNotNull(methodBlock.getStatements());
			{
				final IfStatement statement = (IfStatement) methodBlock.getStatements().get(0);
				
				assertNotNull(statement);
				{
					final BinaryOperation condition = (BinaryOperation) statement.getCondition();
					
					assertNotNull(condition);
					{
						final LocationExpression left = (LocationExpression) condition.getLeft();
						
						assertNotNull(left);
						
						final IdentifierLocation location = (IdentifierLocation) left.getLocation();
						
						assertNotNull(location);
						assertEquals("n", location.getIdentifier());
					}
					assertEquals("<=", condition.getOperator());
					{
						final LiteralExpression right = (LiteralExpression) condition.getRight();
						
						assertNotNull(right);
						
						final IntLiteral literal = (IntLiteral) right.getLiteral();
						
						assertNotNull(literal);
						assertEquals(0, literal.getValue());
					}
				}
				{
					final Block block = statement.getThenBlock();
					
					assertNotNull(block);
					assertNotNull(block.getVariableDeclarations());
					assertTrue(block.getVariableDeclarations().isEmpty());
					assertNotNull(block.getStatements());
					
					final ReturnStatement returnStatement = (ReturnStatement) block.getStatements().get(0);
					
					assertNotNull(returnStatement);
					
					final LiteralExpression returnExpression = (LiteralExpression) returnStatement.getExpression();
					
					assertNotNull(returnExpression);
					
					final IntLiteral literal = (IntLiteral) returnExpression.getLiteral();
					
					assertNotNull(literal);
					assertEquals(1, literal.getValue());
				}
				assertNull(statement.getElseBlock());
			}
			{
				final BlockStatement statement = (BlockStatement) methodBlock.getStatements().get(1);
				
				assertNotNull(statement);
			}
			assertEquals(2, methodBlock.getStatements().size());
		}
		assertEquals(1, program.getMethodDeclarations().size());
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
	
}
