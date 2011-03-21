/*
 * DevicesINF.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.interfaces;

import simulator.device.Devices;
import simulator.device.KeyBoard;
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
	 */
	public static void toDevice(Formatstr devid, Formatstr content) {
		Devices.out(devid, content);
	}
	
	/**
	 * IN instruction
	 * 
	 * @param devid		the id of device(5 bits).
	 * 					from OPD(24 bits).
	 * @return String 	the content the register want.
	 */
	public static String fromDevice(Formatstr devid) {
		return Devices.in(devid);
	}
	
	/**
	 * print the content of printer buffer.
	 * 
	 * @return String	the content of printer buffer.
	 */
	public static String showPrinter() {
		return Printer.showOnConsole();
	}
	
	/**
	 * encode the charactor input
	 */
	public static void characterEnc(char c) {
		KeyBoard.encode(c);
	}
	
	/**
	 * check the device status.
	 */
	public static int checkStatus() {
		return KeyBoard.getStatus();
	}
	
	/**
	 * change the device status.
	 * 
	 * @param s		1: idle; 2: done; 3:check input whether is a number or a char;
	 * 				
	 */
	public static void changeStatus(int s) {
		KeyBoard.setStatus(s);
	}
}
