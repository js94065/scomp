package scomp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-06-07)
 *
 */
public class ToolsTest {
	
	@Test
	public final void testCast() {
		final Object object = "42";
		final String that = Tools.cast(String.class, object);
		
		assertSame(object, that);
		
		final Integer badCast = Tools.cast(Integer.class, object);
		
		assertNull(badCast);
	}
	
	@Test
	public final void testEquals() {
		final Object object = "42";
		
		assertTrue(Tools.equals(null, null));
		assertFalse(Tools.equals(object, null));
		assertTrue(Tools.equals(object, object));
		assertTrue(Tools.equals(new Integer(6 * 7).toString(), object));
		assertFalse(Tools.equals(object, 42));
	}
	
	@Test
	public final void testHashcode() {
		final Object object = "42";
		
		assertEquals(0, Tools.hashCode(null));
		assertEquals(object.hashCode(), Tools.hashCode(object));
	}
	
}
