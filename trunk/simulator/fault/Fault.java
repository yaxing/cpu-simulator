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
	private static String mulDivRegFault = "0010";
	private static String overflow = "0011";
	private static String underflow = "0100";
	private static String devideZero = "0101";
	private static String equalOrNot = "0110";
	
	public static void evoke(){
		Formatstr mfr = OutregsINF.getMFR();
		String fault = mfr.getStr();
		
		if(fault.equals(memFault)){
			TraceINF.write("Fault: Memory fault!");
			Thread controller = SimulatorConsole.getmaincontroller();
			controller.destroy();
			return;
		}
		
		else if(fault.equals(mulDivRegFault)){
			TraceINF.write("Fault: Register fault in MUL or DIV!");
			Thread controller = SimulatorConsole.getmaincontroller();
			controller.destroy();
			return;
		}
		
		else if(fault.equals(overflow)){
			TraceINF.write("Fault: Overflow!");
			return;
		}
		
		else if(fault.equals(underflow)){
			TraceINF.write("Fault: Underflow!");
			return;
		}
		
		else if(fault.equals(devideZero)){
			TraceINF.write("Fault: DevideZero!");
			return;
		}
		
	}
}
