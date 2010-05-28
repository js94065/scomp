package jlex.demos.integerlistscanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 
 * @author js94065 (creation 2010-05-28)
 *
 */

public class UseIntegerListScanner {
    /**
     * @param arguments
     * <br>Unused
     */
    public static final void main(final String[] arguments) {
        try {
            System.out.println("Scanning correct input");
            ByteArrayInputStream y = new ByteArrayInputStream("1 2 3 4".getBytes());
            Object x = new Yylex(y).yylex();
            while (x != null) {
                System.out.println(x);
                ((Yylex) x).yylex();
            }
            
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        
        try {
            System.out.println("Scanning arbitrary input");
            new Yylex(new ByteArrayInputStream("hell".getBytes())).yylex();
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }
}
