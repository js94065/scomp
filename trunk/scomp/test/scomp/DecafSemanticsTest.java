package scomp;

import static org.junit.Assert.*;
import static scomp.DecafParserTest.*;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-07-12)
 *
 */
public class DecafSemanticsTest {
	
	@Test
	public final void testSmallestProgram() throws Exception {
		final Program program = parse(SMALLEST_PROGRAM);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		assertTrue(program.getFieldDeclarations().isEmpty());
		assertNotNull(program.getMethodDeclarations());
		assertTrue(program.getMethodDeclarations().isEmpty());
	}
	
	@Test
	public final void testProgramWithAField() throws Exception {
		final Program program = parse(PROGRAM_WITH_A_FIELD);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		{
			final FieldDeclaration fieldDeclaration = (FieldDeclaration) program.getFieldDeclarations().get(0);
			
			assertEquals(int.class, fieldDeclaration.getType());
			assertEquals("x", fieldDeclaration.getIdentifier());
		}
		assertEquals(1, program.getFieldDeclarations().size());
		assertNotNull(program.getMethodDeclarations());
		assertTrue(program.getMethodDeclarations().isEmpty());
	}
	
	@Test
	public final void testProgramWithAMethod() throws Exception {
		final Program program = parse(PROGRAM_WITH_A_METHOD);
		
		assertNotNull(program);
		assertNotNull(program.getFieldDeclarations());
		assertTrue(program.getFieldDeclarations().isEmpty());
		assertNotNull(program.getMethodDeclarations());
		assertEquals(1, program.getMethodDeclarations().size());
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
		assertEquals(1, program.getMethodDeclarations().size());
	}
	
}
