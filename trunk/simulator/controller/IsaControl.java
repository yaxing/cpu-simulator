/*
 * IsaControl.java
 * Created on 09-30-2010
 * By Yaxing Chen
 */
package simulator.controller;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class IsaControl
 * control execution logic of all instructions 
 *                          
 * @author Yaxing Chen
 * @version 09-30-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class IsaControl {
	private static Formatstr buffer = new Formatstr();
	
	private static void calEa(){
		String ix = OutregsINF.getROP2().getStr();
		String address = OutregsINF.getOPD().getStr();
		Integer ea = Integer.parseInt(ix,2) + Integer.parseInt(address,2);
		buffer.setStr(Integer.toBinaryString(ea));
		buffer.formatAddress();
	}
	
	public static boolean execLdr(){
		if(OutregsINF.getIBIT().getStr() == "0"){
			calEa();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
		}
		else{
			calEa();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
			OutregsINF.setMAR(OutregsINF.getMBR());
			MemoryINF.loadMemory();
		}
		
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		switch(gN){
		case 0:
			GrINF.setR0(OutregsINF.getMBR());
			break;
		case 1:
			GrINF.setR1(OutregsINF.getMBR());
			break;
		case 2:
			GrINF.setR2(OutregsINF.getMBR());
			break;
		case 3:
			GrINF.setR3(OutregsINF.getMBR());
			break;
		default:
			break;
		}
		return true;
	}
}
