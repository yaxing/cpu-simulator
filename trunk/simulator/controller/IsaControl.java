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
	/**buffer is used to temporarily store data got from registers*/
	private static Formatstr buffer = new Formatstr();
	
	/**
	 * calculate the address
	 * when needed, this function adds IX and Address together
	 * and get the needed address
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void calEa(){
		/*get IX from ROP2*/
		String ix = OutregsINF.getROP2().getStr();
		/*get Address from OPD*/
		String address = OutregsINF.getOPD().getStr();
		/*add them together and store in buffer as needed address*/
		Integer ea = Integer.parseInt(ix,2) + Integer.parseInt(address,2);
		buffer.setStr(Integer.toBinaryString(ea));
		buffer.formatAddress();
	}
	
	/**
	 * Execute instruction LDR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static boolean execLdr(){
		
		/*judge whether it is direct or indirect address*/
		if(OutregsINF.getIBIT().getStr().equals("0")){
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
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		/*store MBR content into the target register*/
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
