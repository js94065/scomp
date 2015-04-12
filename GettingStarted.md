

# Intro #

Here are some preliminary steps for getting started.

  1. The project will be hosted on Google Code since the interface looks cleaner than sourceforge.  Google Code requires all developers to have a google account.  The link to google accounts is below.  Ask js94065 to add your google account screen name to the project.  <br><br> Google Account: <a href='https://www.google.com/accounts/'>https://www.google.com/accounts/</a> <br> <br>
<ol><li>We will use gtalk for instant messaging.  We will hold meetings using partychat.  The link to partychat is below.  To use partychat, ask js94065 to add you to the room.  It might be best to ask right before a meeting starts, since that's a guaranteed time js94065 will be online.  You will then see an invitation from: "scompiler@partychapp.appspotchat.com" in your gtalk buddy list, accept this invitation, then open a conversation window with this buddy to join the chatroom.  <br><br> Partychat: <a href='http://partychapp.appspot.com/'>http://partychapp.appspot.com/</a>.  <br> <br>
</li><li>For this project, we will be following the assignments from the MIT compilers class from opencourseware.  The link to the MIT OCW course is below.  We can also follow a book or other online resources. <br><br> MIT opencourseware: <a href='http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-035-computer-language-engineering-sma-5502-fall-2005/'>http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-035-computer-language-engineering-sma-5502-fall-2005/</a> <br> <br>
</li><li>One of the original communications plans was to use IRC, but we later decided to use partychat.  Here's the old IRC info for reference.  IRC meeting place: server: chat.freenode.net (port 6667), channel: scompiler</li></ol>

<h1>Work Tools</h1>

<ul><li>Project Files: <a href='http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-035-computer-language-engineering-sma-5502-fall-2005/projects/'>http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-035-computer-language-engineering-sma-5502-fall-2005/projects/</a>
</li><li>Language our compiler compiles: Decaf (described in project files above)<br>
</li><li>Development Language: Java<br>
</li><li>Tasks: See Issues tab from above<br>
</li><li>Forum: We will use the wiki tab from above<br>
</li><li>Source: See Source tab above, we are using SVN: <a href='https://scomp.googlecode.com/svn/trunk/scomp/'>https://scomp.googlecode.com/svn/trunk/scomp/</a>
</li><li>IDE: Eclipse<br>
</li><li>Scanner generator: JLex<br>
</li><li>Parser generator: CUP<br>
</li><li>Messaging System: <a href='http://groups.google.com/group/scompiler'>http://groups.google.com/group/scompiler</a>.</li></ul>

<h1>General Conventions</h1>

<ul><li>All file and folder names should be in lowercase, except Java class files which should be in <code>UpperCamelCase</code>
</li><li>All text files should be encoded in UTF-8 (check your Eclipse preferences)</li></ul>

<h1>Coding Conventions</h1>

<ul><li>Java naming conventions.<br>
</li><li>Indentation with tabs.<br>
</li><li>Instance attributes should be private.<br>
</li><li>Braces should start on the same line as function name.<br>
</li><li>Use imports and static imports (do not use java.io.Scanner).</li></ul>

<h2>Example of Coding Conventions</h2>

<pre><code><br>
package sandbox;<br>
<br>
import static java.lang.Math.random;<br>
<br>
import java.io.ByteArrayInputStream;<br>
import java.io.InputStream;<br>
import java.util.Scanner;<br>
<br>
/**<br>
 * This class illustrates some coding conventions.<br>
 * &lt;br&gt;It has a {@link #main(String[])} so you can run it to see what it does.<br>
 * <br>
 * @author codistmonk (creation 2010-06-01)<br>
 */<br>
public final class Guess {<br>
	<br>
	private final String answer;<br>
	<br>
	private Scanner scanner;<br>
	<br>
	public Guess() {<br>
		this.answer = Coin.flip().toString();<br>
	}<br>
	<br>
	public final void go() {<br>
		try {<br>
			System.out.println(QUESTION);<br>
			<br>
			Thread.sleep(SUSPENSE_DURATION);<br>
			<br>
			System.out.println(this.getAnswer());<br>
			<br>
			this.setScanner(new Scanner(toInputStream(this.getAnswer())));<br>
			<br>
			analyseAnswerAndPrintResult(this.getScanner());<br>
		} catch (final Exception exception) {<br>
			// Synchronize console outputs when there is only one thread<br>
			System.out.flush();<br>
			<br>
			exception.printStackTrace();<br>
		} finally {<br>
			if (this.getScanner() != null) {<br>
				this.getScanner().close();<br>
			}<br>
		}<br>
	}<br>
	<br>
	/**<br>
	 * <br>
	 * @return<br>
	 * &lt;br&gt;Not null<br>
	 * &lt;br&gt;Shared<br>
	 */<br>
	public final String getAnswer() {<br>
		return this.answer;<br>
	}<br>
	<br>
	/**<br>
	 * <br>
	 * @return<br>
	 * &lt;br&gt;Maybe null<br>
	 * &lt;br&gt;Shared<br>
	 */<br>
	public final Scanner getScanner() {<br>
		return this.scanner;<br>
	}<br>
	<br>
	/**<br>
	 * <br>
	 * @param scanner<br>
	 * &lt;br&gt;Maybe null<br>
	 * &lt;br&gt;Shared<br>
	 */<br>
	public final void setScanner(final Scanner scanner) {<br>
		this.scanner = scanner;<br>
	}<br>
	<br>
	public static final String QUESTION = "Heads or tails?";<br>
	<br>
	/**<br>
	 * {@value} milliseconds.<br>
	 */<br>
	public static final long SUSPENSE_DURATION = 1000L;<br>
	<br>
	/**<br>
	 * <br>
	 * @param scanner<br>
	 * &lt;br&gt;Not null<br>
	 * &lt;br&gt;Input-output<br>
	 */<br>
	private static final void analyseAnswerAndPrintResult(final Scanner scanner) {<br>
		switch (Coin.valueOf(scanner.next())) {<br>
		case HEADS:<br>
			System.out.println("Game over!");<br>
			break;<br>
		case TAILS:<br>
			System.out.println("You win!");<br>
			break;<br>
		case EDGE:<br>
			System.out.println("OMG!");<br>
			break;<br>
		}<br>
	}<br>
	<br>
	/**<br>
	 * Creates an input stream from {@code string}.<br>
	 * <br>
	 * @param string<br>
	 * &lt;br&gt;Not null<br>
	 * @return<br>
	 * &lt;br&gt;Not null<br>
	 * &lt;br&gt;New<br>
	 */<br>
	public static final InputStream toInputStream(final String string) {<br>
		return new ByteArrayInputStream(string.getBytes());<br>
	}<br>
	<br>
	/**<br>
	 * @param arguments<br>
	 * &lt;br&gt;Unused<br>
	 */<br>
	public static final void main(final String[] arguments) {<br>
		new Guess().go();<br>
	}<br>
	<br>
	/**<br>
	 * This enum defines the possible outcomes of a coin flip.<br>
	 * &lt;br&gt;A static {@link #flip()} method is provided to generate random values.<br>
	 * <br>
	 * @author codistmonk (creation 2010-06-01)<br>
	 */<br>
	public static enum Coin {<br>
		<br>
		HEADS, TAILS, EDGE;<br>
		<br>
		/**<br>
		 * Generates a random value.<br>
		 * &lt;br&gt;Note that {@link #EDGE} has a very low probability.<br>
		 * <br>
		 * @return<br>
		 * &lt;br&gt;Not null<br>
		 * &lt;br&gt;Shared<br>
		 * &lt;br&gt;Range: { {@link #HEADS}, {@link #TAILS}, {@link #EDGE} }<br>
		 */<br>
		public static final Coin flip() {<br>
			final double random = random();<br>
			<br>
			if (random &lt; HEADS_PROBABILITY) {<br>
				return HEADS;<br>
			} else if (random &gt; HEADS_PROBABILITY) {<br>
				return TAILS;<br>
			} else {<br>
				return EDGE;<br>
			}<br>
		}<br>
		<br>
		/*<br>
		 * {@value}.<br>
		 */<br>
		private static final double HEADS_PROBABILITY = 0.5;<br>
		<br>
	}<br>
	<br>
}<br>
<br>
</code></pre>

<h1>Plan</h1>

<h2>Scanner/Parser</h2>

<h2>Semantics</h2>

<h2>Code Generation</h2>

<h2>Dataflow Optimization</h2>

<h2>Low-level Optimization</h2>