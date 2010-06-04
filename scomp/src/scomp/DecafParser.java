
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Fri Jun 04 22:50:34 CEST 2010
//----------------------------------------------------

package scomp;

import java_cup.runtime.*;

/** CUP v0.10k generated parser.
  * @version Fri Jun 04 22:50:34 CEST 2010
  */
public class DecafParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public DecafParser() {super();}

  /** Constructor which sets the default scanner. */
  public DecafParser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\002\000\002\003\003\000\002\002\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\004\000\004\007\005\001\002\000\004\002\006\001" +
    "\002\000\004\002\001\001\002\000\004\002\000\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\004\000\004\003\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$DecafParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$DecafParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$DecafParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$DecafParser$actions {
  private final DecafParser parser;

  /** Constructor */
  CUP$DecafParser$actions(DecafParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$DecafParser$do_action(
    int                        CUP$DecafParser$act_num,
    java_cup.runtime.lr_parser CUP$DecafParser$parser,
    java.util.Stack            CUP$DecafParser$stack,
    int                        CUP$DecafParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$DecafParser$result;

      /* select the action based on the action number */
      switch (CUP$DecafParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= Program EOF 
            {
              Object RESULT = null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).value;
		RESULT = start_val;
              CUP$DecafParser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-1)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          /* ACCEPT */
          CUP$DecafParser$parser.done_parsing();
          return CUP$DecafParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // Program ::= CLASS 
            {
              Object RESULT = null;

              CUP$DecafParser$result = new java_cup.runtime.Symbol(1/*Program*/, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).left, ((java_cup.runtime.Symbol)CUP$DecafParser$stack.elementAt(CUP$DecafParser$top-0)).right, RESULT);
            }
          return CUP$DecafParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

