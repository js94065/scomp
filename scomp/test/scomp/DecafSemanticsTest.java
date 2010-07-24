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
		final Program expectedProgram = new Program(
				null,
				null
		);
		
		assertEquals(expectedProgram, parse(SMALLEST_PROGRAM));
	}
	
	@Test
	public final void testProgramWithAField() throws Exception {
		final Program expectedProgram = new Program(
				fields(
						field(int.class, "x")
				),
				null
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_FIELD));
	}
	
	@Test
	public final void testProgramWithAMethod() throws Exception {
		final Program expectedProgram = new Program(
				null,
				methods(
						method(void.class, "f")
				)
		);
		
		assertEquals(expectedProgram, parse(PROGRAM_WITH_A_METHOD));
	}
	
	@Test
	public final void testProgramWithAFieldAndAMethod() throws Exception {
		final Program program = parse(PROGRAM_WITH_A_FIELD_AND_A_METHOD);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		{
			final ArrayFieldDeclaration fieldDeclaration = (ArrayFieldDeclaration) program.getFieldDeclarations().get(0);
			
			assertEquals(int.class, fieldDeclaration.getType());
			assertEquals("x", fieldDeclaration.getIdentifier());
			assertEquals(42, fieldDeclaration.getElementCount());
		}
		assertEquals(1, program.getFieldDeclarations().size());
		assertNotNull(program.getMethodDeclarations());
		{
			final MethodDeclaration methodDeclaration = program.getMethodDeclarations().get(0);
			
			assertEquals(boolean.class, methodDeclaration.getType());
			assertEquals("f", methodDeclaration.getIdentifier());
			{
				final FieldDeclaration parameterDeclaration = methodDeclaration.getParameterDeclarations().get(0);
				
				assertEquals(int.class, parameterDeclaration.getType());
				assertEquals("y", parameterDeclaration.getIdentifier());
			}
			assertEquals(1, methodDeclaration.getParameterDeclarations().size());
			
			final Block methodBlock = methodDeclaration.getBlock();
			
			assertNotNull(methodBlock);
			assertNotNull(methodBlock.getVariableDeclarations());
			assertTrue(methodBlock.getVariableDeclarations().isEmpty());
			assertNotNull(methodBlock.getStatements());
			{
				final ReturnStatement statement = (ReturnStatement) methodBlock.getStatements().get(0);
				
				assertNotNull(statement);
			}
			assertEquals(1, methodBlock.getStatements().size());
		}
		assertEquals(1, program.getMethodDeclarations().size());
	}
	
	@Test
	public final void testProgramWithFieldsAndMethods() throws Exception {
		final Program program = parse(PROGRAM_WITH_FIELDS_AND_METHODS);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		{
			final FieldDeclaration fieldDeclaration = (FieldDeclaration) program.getFieldDeclarations().get(0);
			
			assertEquals(int.class, fieldDeclaration.getType());
			assertEquals("x", fieldDeclaration.getIdentifier());
		}
		{
			final ArrayFieldDeclaration fieldDeclaration = (ArrayFieldDeclaration) program.getFieldDeclarations().get(1);
			
			assertEquals(int.class, fieldDeclaration.getType());
			assertEquals("y", fieldDeclaration.getIdentifier());
			assertEquals(1, fieldDeclaration.getElementCount());
		}
		{
			final FieldDeclaration fieldDeclaration = (FieldDeclaration) program.getFieldDeclarations().get(2);
			
			assertEquals(int.class, fieldDeclaration.getType());
			assertEquals("z", fieldDeclaration.getIdentifier());
		}
		{
			final FieldDeclaration fieldDeclaration = (FieldDeclaration) program.getFieldDeclarations().get(3);
			
			assertEquals(boolean.class, fieldDeclaration.getType());
			assertEquals("a", fieldDeclaration.getIdentifier());
		}
		{
			final ArrayFieldDeclaration fieldDeclaration = (ArrayFieldDeclaration) program.getFieldDeclarations().get(4);
			
			assertEquals(boolean.class, fieldDeclaration.getType());
			assertEquals("b", fieldDeclaration.getIdentifier());
			assertEquals(2, fieldDeclaration.getElementCount());
		}
		{
			final FieldDeclaration fieldDeclaration = (FieldDeclaration) program.getFieldDeclarations().get(5);
			
			assertEquals(boolean.class, fieldDeclaration.getType());
			assertEquals("c", fieldDeclaration.getIdentifier());
		}
		assertEquals(6, program.getFieldDeclarations().size());
		assertNotNull(program.getMethodDeclarations());
		{
			final MethodDeclaration methodDeclaration = program.getMethodDeclarations().get(0);
			
			assertEquals(void.class, methodDeclaration.getType());
			assertEquals("f", methodDeclaration.getIdentifier());
			assertNotNull(methodDeclaration.getParameterDeclarations());
			assertTrue(methodDeclaration.getParameterDeclarations().isEmpty());
			
			final Block methodBlock = methodDeclaration.getBlock();
			
			assertNotNull(methodBlock);
			assertNotNull(methodBlock.getVariableDeclarations());
			assertTrue(methodBlock.getVariableDeclarations().isEmpty());
			assertNotNull(methodBlock.getStatements());
			assertTrue(methodBlock.getStatements().isEmpty());
		}
		{
			final MethodDeclaration methodDeclaration = program.getMethodDeclarations().get(1);
			
			assertEquals(int.class, methodDeclaration.getType());
			assertEquals("g", methodDeclaration.getIdentifier());
			assertNotNull(methodDeclaration.getParameterDeclarations());
			assertTrue(methodDeclaration.getParameterDeclarations().isEmpty());
			
			final Block methodBlock = methodDeclaration.getBlock();
			
			assertNotNull(methodBlock);
			assertNotNull(methodBlock.getVariableDeclarations());
			assertTrue(methodBlock.getVariableDeclarations().isEmpty());
			assertNotNull(methodBlock.getStatements());
			{
				final ReturnStatement statement = (ReturnStatement) methodBlock.getStatements().get(0);
				
				assertNotNull(statement);
			}
			assertEquals(1, methodBlock.getStatements().size());
		}
		assertEquals(2, program.getMethodDeclarations().size());
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
	private static final ArrayFieldDeclaration array(final Class<?> elementType, final String identifier, final int elementCount) {
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
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final MethodDeclaration method(final Class<?> returnType, final String identifier) {
		return new MethodDeclaration(returnType, identifier, null, null);
	}
	
}
