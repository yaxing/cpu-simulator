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
 * @version 10-02-2010
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
	private static void pendIxGr(){
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
	 * get EA and fetch it into MAR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void genEaToMar(){
		/*judge whether it is direct or indirect address*/
		if(OutregsINF.getIBIT().getStr().equals("0")){
			pendIxGr();
			OutregsINF.setMAR(buffer);
		}
		else{
			pendIxGr();
			OutregsINF.setMAR(buffer);
			
			OutregsINF.setMCR(new Formatstr("0"));
			MemoryINF.operateMemory();
			
			OutregsINF.setMAR(OutregsINF.getMBR());
		}
	}
	
	/**
	 * Execute instruction LDR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static boolean execLdr(){
		
		//get EA to MAR
		genEaToMar();
		
		//set MBR with memory data from address in MAR
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		
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
	
	/**
	 * Execute instruction STR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static boolean execStr(){
		//get EA to MAR
		genEaToMar();
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		//set MBR with register content
		switch(gN){
		case 0:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		case 1:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		case 2:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		case 3:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		default:
			break;
		}
		
		//write MBR content into memory unit located in MAR address
		OutregsINF.setMCR(new Formatstr("1"));
		MemoryINF.operateMemory();
		return true;
	}
}
