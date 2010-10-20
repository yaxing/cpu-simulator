/*
 * Printer.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

import simulator.formatstr.Formatstr;

/** 
 * Class Printer
 * Printer device. OUT instruction uses this device.
 * The printer has its own buffer for future concerns. 
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class Printer {
	private static String printBuffer = new String("");
	
	/**
	 * OUT instruction. 
	 * Set the printer buffer with the content and line separator.
	 * 
	 * @param content	content to be set.	
	 */
	public static boolean out(Formatstr content) {
		printBuffer = printBuffer.concat(content.getStr() + System.getProperty("line.separator"));
		System.out.println("printer buffered content!");
		return true;
	}
	
	/**
	 * Called by Controller if it's ready to "print" things on screen.
	 * 
	 * @return String	the printer buffer
	 */
	public static String showOnConsole() { return printBuffer; }
	/**
	 * Default constructor
	*/
	public Printer() {}
}
