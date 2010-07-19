package jlex.tools;

import java.io.File;

/**
 * Utility class.
 * 
 * @author codistmonk (creation 2010-07-19)
 *
 */
public final class JLexTools {
	
	/**
	 * Private default constructor to prevent instantiation.
	 */
	private JLexTools() {
		// Do nothing
	}

    private static final int DEBUG_STACK_OFFSET = getDebugStackOffset();

    /**
     * Returns "src/package/name/" if the package of the caller class is of the form "package.name"
     * and {@code sourcesRoot} is "src".
     * 
     * @param sourcesRoot 
     * <br>Not null
     * @return
     * <br>Not null
     */
    public static final String getSourceDirectory(final String sourcesRoot) {
        return (sourcesRoot + "." + getCallerClass().getPackage().getName() + ".").replace(".", File.separator);
    }
	
	/**
	 * 
	 * @param file
	 * <br>Not null
	 * <br>Input-output
	 * @param directoryPath
	 * <br>Not null
	 * @return {@code true} if the operation succeeds
	 */
	public static final boolean moveToDirectory(final File file, final String directoryPath) {
		final File destination = new File(directoryPath, file.getName());
		
		System.out.println("Moving " + file.getAbsolutePath() + " to " + destination.getAbsolutePath() + " ...");
		
		if (destination.exists() && !destination.delete()) {
			System.err.println("Warning: The destination file (" + destination.getAbsolutePath() + ") exists and could not be deleted");
		}
		
		final boolean result = file.renameTo(destination);
		
		if (result) {
			System.out.println("Success");
		} else {
			System.err.println("Failure");
		}
		
		return result;
	}

    /**
     * If a method {@code A.a()} calls a method {@code B.b()},
     * then the result of calling this method in {@code b()} will be {@code A.class}.
     * <br>Warning: this method can only be used directly.
     * <br>If you want to refactor your code, you can re-implement the functionality
     * using {@code Thread.currentThread().getStackTrace()}.
     *
     * @return {@code null} if the caller class cannot be retrieved
     * <br>Maybe null
     */
    public static final Class<?> getCallerClass() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length > DEBUG_STACK_OFFSET + 2) {
            try {
                return Class.forName(stackTrace[DEBUG_STACK_OFFSET + 2].getClassName());
            } catch (final ClassNotFoundException exception) {
                // Do nothing
            }
        }

        return null;
    }

    /**
     *
     * @return
     * <br>Range: {@code [0 .. Integer.MAX_VALUE]}
     */
    private static final int getDebugStackOffset() {
        int result = 0;

        for (final StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().equals(JLexTools.class.getName())) {
                break;
            }

            ++ result;
        }

        return result;
    }
	
}
