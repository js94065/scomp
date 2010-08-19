package scomp;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import java_cup.runtime.Symbol;

import jlex.tools.JLexTools;

/**
 * 
 * @author codistmonk (creation 2010-06-07)
 *
 */
public final class Tools {
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private Tools() {
		// Do nothing
	}
	
    public static final int DEBUG_STACK_OFFSET = JLexTools.DEBUG_STACK_OFFSET;
	
	/**
	 * 
	 * @param elements
	 * <br>Not null
	 * @param methodName
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	public static final Iterable<?> invoke(final Iterable<?> elements, final String methodName) {
		return new Iterable<Object>() {
			
			@Override
			public final Iterator<Object> iterator() {
				final Iterator<?> iterator = elements.iterator();
				
				return new Iterator<Object>() {
					
					@Override
					public boolean hasNext() {
						return iterator.hasNext();
					}
					
					@Override
					public final Object next() {
						final Object object = iterator.next();
						
						if (object == null) {
							return null;
						}
						
						try {
							final Method method = object.getClass().getMethod(methodName);
							
							return method.invoke(object);
						} catch (final Exception exception) {
							throw Tools.unchecked(exception);
						}
					}
					
					@Override
					public final void remove() {
						iterator.remove();
					}
					
				};
			}
			
		};
	}
	
	/**
	 * 
	 * @param separator
	 * <br>Maybe null
	 * @param elements
	 * <br>Maybe null
	 * @return
	 * <br>Not null
	 * <br>New
	 */
	public static final String join(final String separator, final Iterable<?> elements) {
		final StringBuilder result = new StringBuilder();
		final Iterator<?> iterator = elements.iterator();
		
		while (iterator.hasNext()) {
			result.append(iterator.next());
			
			if (iterator.hasNext()) {
				result.append(separator);
			}
		}
		
		return result.toString();
	}
    
	/**
	 *
	 * @param <T> The common type of the elements
	 * @param array
	 * <br>Maybe null
	 * @return
	 * <br>Maybe null
	 * <br>Maybe New
	 */
	public static final <T> T[] array(final T... array) {
		return array;
	}
	
	/**
	 * 
	 * @param <T> The type of the elements
	 * @param list
	 * <br>Maybe null
	 * @return {@code list} if it is not null, otherwise an empty list
	 * <br>Not null
	 * <br>Maybe new
	 */
	@SuppressWarnings("unchecked")
	public static final <T> List<T> emptyIfNull(final List<T> list) {
		return (List<T>) (list == null ? Collections.emptyList() : list);
	}
	
	/**
	 * Returns an instance of {@link RuntimeException} which is either {@code cause} itself,
	 * if it is already a runtime exception, or a new runtime exception wrapping {@code cause}.
	 * <br>It is up to the caller to decide what to do with the returned exception.
	 *
	 * @param cause
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>Maybe new
	 */
	public static final RuntimeException unchecked(final Throwable cause) {
		if (cause instanceof RuntimeException) {
			return (RuntimeException) cause;
		}
	
		return new RuntimeException(cause);
	}
	
	/**
	 * Does the same thing as {@link Class#cast(Object)}, but returns {@code null} instead of throwing an exception if the cast cannot be performed.
	 * 
	 * @param <T> the type into which {@code object} is tentatively being cast
	 * @param cls
	 * <br>Should not be null
	 * @param object
	 * <br>Can be null
	 * @return {@code null} if {@code object} is {@code null} or cannot be cast into {@code T}, otherwise {@code object}
	 * <br>A possibly null value
	 */
	public static final <T> T cast(final Class<T> cls, final Object object) {
		if (object == null || !cls.isAssignableFrom(object.getClass())) {
			return null;
		}
		
		return cls.cast(object);
	}
	
	/**
	 * 
	 * @param object1
	 * <br>Can be null
	 * @param object2
	 * <br>Can be null
	 * @return {@code true} if both objects are the same (using {@code ==}) or equal (using {@code equals()})
	 */
	public static final boolean equals(final Object object1, final Object object2) {
		return object1 == object2 || (object1 != null && object1.equals(object2));
	}
	
	/**
	 * 
	 * @param object
	 * <br>Can be null
	 * @return {@code 0} if {@code object is null}, otherwise {@code object.hashcode()}
	 * <br>Range: any integer
	 */
	public static final int hashCode(final Object object) {
		return object == null ? 0 : object.hashCode();
	}

	/**
	 * Concatenates the source location of the call and
	 * the string representations of the parameters separated by spaces.
	 * <br>This is method helps to perform console debugging using System.out or System.err.
	 *
	 * @param stackOffset {@link #DEBUG_STACK_OFFSET} is the source of the call,
	 * {@code DEBUG_STACK_OFFSET + 1} is the source of the call's caller, and so forth
	 * <br>Range: {@code [O .. Integer.MAX_VALUE]}
	 * @param objects
	 * <br>Not null
	 * @return
	 * <br>Not null
	 * <br>New
	 * @throws IndexOutOfBoundsException if {@code stackIndex} is invalid
	 */
	public static final String debug(final int stackOffset, final Object... objects) {
		final StringBuilder builder = new StringBuilder(
				Thread.currentThread().getStackTrace()[stackOffset + 1].toString());
		
		for (final Object object : objects) {
			builder.append(" ").append(object);
		}
		
		return builder.toString();
	}
    
    /**
     * Prints on the standard output the concatenation of the source location of the call
     * and the string representations of the parameters separated by spaces.
     * 
     * @param objects
     * <br>Should not be null
     */
    public static final void debugPrint(final Object... objects) {
        System.out.println(debug(DEBUG_STACK_OFFSET + 1, objects));
    }
	
	/**
	 * 
	 * @param <T> The expected return type
	 * @param input
	 * <br>Not null
	 * @return
	 * <br>Maybe null
	 * @throws RuntimeException If an error occurs
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T parse(final String input) {
		final DecafParser parser = new DecafParser(createScanner(input));
		try {
			final Symbol parseResult = parser.parse();
			
			return (T) (parseResult == null ? null : parseResult.value);
		} catch (final Exception exception) {
			throw unchecked(exception);
		}
	}
	
	/**
	 * 
	 * @param input
	 * <br>Should not be null
	 * @return
	 * <br>A non-null value
	 * <br>A new value
	 */
	public static final Yylex createScanner(final String input) {
		return new Yylex(new ByteArrayInputStream(input.getBytes()));
	}
	
	/**
	 * 
	 * @param logger
	 * <br>Not null
	 * <br>Input-output
	 */
	public static final void clearHandlersAndDontUseParentHandlers(final Logger logger) {
		for (final Handler handler : logger.getHandlers()) {
			logger.removeHandler(handler);
		}
		
		logger.setUseParentHandlers(false);
	}
	
	/**
	 * 
	 * @param logger
	 * <br>Not null
	 * <br>Input-output
	 * @param handler
	 * <br>Not null
	 * <br>Shared
	 */
	public static final void setHandler(final Logger logger, final Handler handler) {
		clearHandlersAndDontUseParentHandlers(logger);
		
		logger.addHandler(handler);
	}
	
	/**
	 * 
	 * @author codistmonk (creation 2010-07-27)
	 */
	public static final class MessageRecorder extends Handler {
		
		private final List<String> messages;
		
		public MessageRecorder() {
			this.messages = new ArrayList<String>();
		}
		
		/**
		 * 
		 * @return
		 * <br>Not null
		 * <br>Shared
		 */
		public final List<String> getMessages() {
			return this.messages;
		}
		
		@Override
		public final void publish(final LogRecord record) {
			this.messages.add(record.getMessage());
		}
		
		@Override
		public final void flush() {
			// Do nothing
		}

		@Override
		public final void close() throws SecurityException {
			// Do nothing
		}
		
	}
	
}
