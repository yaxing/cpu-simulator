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
 * @author Yichao Yu, Yaxing Chen
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
	public static boolean out(Formatstr devid, Formatstr content) {
		String id = new String(devid.getStr().substring(20, 24));
		//return device[Integer.parseInt(id,2)].out(devid, content);
		
		boolean flag = false;
		switch(Integer.parseInt(id,2)){
		case 1:
			flag = Printer.out(devid, content);
			break;
		}
		return flag;
	}
	
	/*public static void init() {
		device[1] = new Printer();
		device[0] = new KeyBoard();
	}*/
}
