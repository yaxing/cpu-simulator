/*
 * Fault.java
 * Created on 12-5-2010
 * By Yaxing Chen
 */

package simulator.fault;

import simulator.interfaces.*;
import simulator.formatstr.*;
import simulator.console.*;


/** 
 * Class Fault
 * Handle machine faults
 *                          
 * @author Yaxing Chen
 * @version 12-5-2010
 * @see simulator.fault
 * @since JDK 1.6
 */
public class Fault {
	
	private static String memFault = "0001";
	
	public static void evoke(){
		Formatstr mfr = OutregsINF.getMFR();
		String fault = mfr.getStr();
		if(mfr.getStr().equals(memFault)){
			TraceINF.write("Memory fault!");
			Thread controller = SimulatorConsole.getmaincontroller();
			controller.destroy();
		}
	}
}
