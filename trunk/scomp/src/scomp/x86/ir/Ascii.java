package scomp.x86.ir;

/**
 * This class represents the x86 GAS ".ascii" directive.
 * 
 * @author codistmonk (creation 2010-09-28)
 *
 */
public class Ascii extends AbstractDirective {
	
	private final String normalizedString;
	
	/**
	 * 
	 * @param string
	 * <br>Not null
	 */
	public Ascii(final String string) {
		final StringBuilder normalizedStringBuilder = new StringBuilder("\"");
		
		for (int i = 0; i < string.length(); ++i) {
			final char c = string.charAt(i);
			if ('\n' == c) {
				normalizedStringBuilder.append("\\12");
			} else {
				normalizedStringBuilder.append(c);
			}
		}
		
		normalizedStringBuilder.append("\\0\"");
		
		this.normalizedString = normalizedStringBuilder.toString();
	}
	
	/**
	 * 
	 * @return
	 * <br>Not null
	 * <br>Shared
	 */
	public final String getNormalizedString() {
		return this.normalizedString;
	}
	
	@Override
	public final String toString() {
		return ".ascii " + this.getNormalizedString();
	}
	
}
