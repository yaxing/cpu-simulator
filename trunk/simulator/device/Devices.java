/*
 * Devices.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

import simulator.formatstr.*;

/** 
 * Class Devices
 * Control all I/O devices, including the IN and OUT instructions.
 *                          
 * @author Yichao Yu, Yaxing Chen
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class Devices {
//	private static final int numDevices = 2;
//	private static Devices[] device = new Devices[numDevices];
	
	/**
	 * IN instruction
	 * 
	 * @param 
	 */
	public static void in() {}
	/**
	 * OUT instruction
	 * 
	 * @param devid		the id of device(5 bits).
	 * 					from OPD(24 bits).
	 * 		  content	register content of instruction.	
	 */
	public static boolean out(Formatstr devid, Formatstr content) {
		String id = new String(devid.getStr().substring(20, 24));
		
		boolean flag = false;
		switch(Integer.parseInt(id,2)){
		case 1:
			flag = Printer.out(content);
			break;
		}
		return flag;
	}
	
}