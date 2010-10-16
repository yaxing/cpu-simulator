/*
 * Devices.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

import simulator.formatstr.*;

/** 
 * Class Devices
 * All I/O devices' superclass.
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class Devices {
	private static final int numDevices = 2;
	private static Devices[] device = new Devices[numDevices];
	
	//IN instruction method, overridden by subclass
	public static void in() {}
	//OUT instruction method, overridden by subclass
	public static void out(Formatstr devid, Formatstr content) {}
	
	public static void init() {
		device[0] = new Printer();
		device[1] = new KeyBoard();
	}
}
