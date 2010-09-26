package scomp.x86.translator;

import scomp.Tools;

/**
 * This class can be used to help instantiate OS-specific translators.
 * 
 * @author codistmonk (creation 2010-09-21)
 *
 */
public final class TranslatorFactory {
	
	/**
	 * 
	 * @param osName
	 * <br>Not null
	 * <br>Range: { {@link Tools#MAC_OS_X}, {@link Tools#LINUX}, {@link Tools#WINDOWS} }
	 * @return
	 * <br>Not null
	 * <br>New
	 * @throws IllegalArgumentException If {@code osName} does not name a supported OS
	 */
	public static final AbstractTranslator newTranslator(final String osName) {
		if (Tools.MAC_OS_X.equals(osName)) {
			return new MacOSXTranslator();
		}
		
		if (Tools.LINUX.equals(osName)) {
			return new LinuxTranslator();
		}
		
		if (Tools.WINDOWS.equals(osName)) {
			return new WindowsTranslator();
		}
		
		throw new IllegalArgumentException("Unsupported OS: " + osName);
	}
	
}
