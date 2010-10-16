/*
 * Printer.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

import simulator.formatstr.Formatstr;

/** 
 * Class Printer
 * Printer device. Extends from Devices.
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class Printer extends Devices{
	private static String buffer = new String("");
	//OUT instruction method
	public static boolean out(Formatstr devid, Formatstr content) {
		buffer = buffer.concat(content.getStr() + System.getProperty("line.separator"));
		System.out.println("printer buffered content!");
		return true;
	}
	public static String showOnConsole() { return buffer; }
	/**
	 * Default constructor
	*/
	public Printer() {}
}
