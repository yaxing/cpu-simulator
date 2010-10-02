/*
 * Controller.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.controller;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class Controller
 * Control ISA instructions execution
 *                          
 * @author Yaxing Chen
 * @version 09-30-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class Controller {
	
	/**For address or data buffer, temporarily store address, instructions or data get from other modules*/
	private static Formatstr busBuffer = new Formatstr();
	
	/**Define the end instruction*/
	private static String endInstr = "HLT";
	
	/**Define the end instruction*/
	private static String insFile = "instruction.txt";
	
	/**Define pc offset*/
	private static Formatstr offset = new Formatstr();
	
	
	/**
	 * Default constructor
	*/
	Controller(){

	}
	
	/**
	 * Initiate the ISA controller
	 * call ROM to load instructions from file to memory and
	 * get the address of the first instruction
	 * 
	 * @param
	 * @return whether the initiation success or not
	 * @exception
	 */
	private boolean initial(){
		MemoryINF.ROMload(insFile);
		busBuffer.setStr("00000000000000");
		PcINF.setPc(busBuffer);
		return true;
	}
	
	/**
	 * Execute the instructions based on certain logic sequence
	 * 
	 * @param
	 * @return execStat  indicating the state of instruction execution
	 * @exception
	 */
	private int execInstr(){
		int execStat = 0;
		offset.setStr("00000000000001");
		while(execStat == 0){
			OutregsINF.setMAR(PcINF.getPc());
			MemoryINF.loadMemory();
			OutregsINF.setIR(OutregsINF.getMBR());
			DecodeINF.decode();
			String opcode = OutregsINF.getOPCODE().getStr();
			if(opcode == "000001"){
				IsaControl.execLdr();
			}
			
			PcINF.pcAdder(offset);
			execStat = 1;
		}
		return execStat;
	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
		ISA.execInstr();
	    System.out.println(GrINF.getR0().getStr());
	}	
}
