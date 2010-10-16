/*
 * DevicesINF.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.interfaces;

import simulator.device.Devices;
import simulator.device.Printer;
import simulator.formatstr.*;

/** 
 * Class DevicesINF
 * All I/O devices interface
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class DevicesINF {
	/**
	 * OUT instruction
	 * 
	 * @param devid		the id of device(5 bits).
	 * 					from OPD(24 bits).
	 * 		  content	register content of instruction.	
	 * @return 
	 * @exception 
	 */
	public static void toDevice(Formatstr devid, Formatstr content) {
		Devices.out(devid, content);
	}
	
	/**
	 * IN instruction
	 * 
	 * @param devid		the id of device(5 bits).
	 * 					from OPD(24 bits).
	 * 		  content	register content of instruction.
	 * @return 
	 * @exception 
	 */
	public static void fromDevice(Formatstr devid) {
		
	}
	
	/**
	 * register all devices into Devices superclass
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static void initDevices() {
		//Devices.init();
	}
	
	/**
	 * print the content of printer buffer.
	 * 
	 * @param 
	 * @return String	the content of printer buffer.
	 * @exception 
	 */
	public static String showPrinter() {
		return Printer.showOnConsole();
	}
}
