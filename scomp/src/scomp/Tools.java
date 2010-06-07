package scomp;

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
	 * @return
	 * <br>A non-null value
	 */
	public static final int hashCode(final Object object) {
		return object == null ? 0 : object.hashCode();
	}
	
    /**
     * Concatenates the source location of the call and the string representations of the parameters separated by spaces.
     * <br>This is method helps to perform console debugging using System.out or System.err.
     * 
     * @param stackIndex 1 is the source of this method, 2 is the source of the call, 3 is the source of the call's caller, and so forth
     * <br>Range: {@code [O .. Integer.MAX_VALUE]}
     * @param objects
     * <br>Should not be null
     * @return
     * <br>A new value
     * <br>A non-null value
     * @throws IndexOutOfBoundsException if {@code stackIndex} is invalid
     */
    public static final String debug(final int stackIndex, final Object... objects) {
        final StringBuilder builder = new StringBuilder(Thread.currentThread().getStackTrace()[stackIndex].toString());

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
        System.out.println(debug(3, objects));
    }
	
}
