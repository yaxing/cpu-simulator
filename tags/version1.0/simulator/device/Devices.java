/*
 * Devices.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

import simulator.formatstr.*;
import simulator.interfaces.TraceINF;

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
	 * @param devid 	device id = 0 is keyboard
	 */
	public static String in(Formatstr devid) {
		String id = new String(devid.getStr().substring(20, 24));
		String result = new String();
		
		switch(Integer.parseInt(id,2)){
		case 1:
			TraceINF.write("Input a letter from keyboard.");
			result = KeyBoard.instring();
			break;
		case 0:
			TraceINF.write("Input number from keyboard.");
			result = KeyBoard.innumber();
		}
		return result;
	}
	/**
	 * OUT instruction
	 * 
	 * @param devid		the id of device(5 bits).
	 * 					from OPD(24 bits).
	 * 		  content	register content of instruction.	
	 */
	public static void out(Formatstr devid, Formatstr content) {
		String id = new String(devid.getStr().substring(20, 24));
		
		switch(Integer.parseInt(id,2)){
		case 3:
			TraceINF.write("Output a letter to printer.");
			Printer.outstring(content);
			break;
		case 2:
			TraceINF.write("Output number to printer.");
			Printer.outnumber(content);
			break;
		}

	}
	
}
