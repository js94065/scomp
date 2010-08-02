package scomp;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author codistmonk (creation 2010-06-07)
 *
 */
public class ToolsTest {
	
	@Test
	public final void testInvoke() {
		assertEquals(asList("42", "33"), toList(Tools.invoke(asList(42, 33), "toString")));
	}
	
	@Test
	public final void testJoin() {
		assertEquals("42,33", Tools.join(",", asList(42, 33)));
	}
	
	@Test
	public final void testArray() {
		final Object[] array = new Object[] { 42, 33 };
		
		assertSame(array, Tools.array(array));
		assertArrayEquals(array, Tools.array(42, 33));
    }
	
	@Test
	public final void testEmptyIfNull() {
		final List<?> empty = Collections.emptyList();
		
		assertEquals(empty, Tools.emptyIfNull(null));
		assertSame(empty, Tools.emptyIfNull(empty));
		
		final List<?> nonempty = asList(42);
		
		assertSame(nonempty, Tools.emptyIfNull(nonempty));
	}
	
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
	
	/**
	 * 
	 * @param <T> The common type of the elements
	 * @param elements
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	private static final <T> List<T> toList(final Iterable<T> elements) {
		final List<T> result = new ArrayList<T>();
		
		for (final T element : elements) {
			result.add(element);
		}
		
		return result;
	}
	
}
