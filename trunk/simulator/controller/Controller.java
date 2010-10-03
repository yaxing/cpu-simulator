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
	
	/**Define PC offset*/
	private static Formatstr offset = new Formatstr("00000000000001");
	
	
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
	private void initial(){
		/*fetch instructions to rom and get the entry address of instructions*/
		busBuffer.setStr(MemoryINF.ROMload(insFile));
		busBuffer.formatAddress();
		
		/*initiate PC*/
		PcINF.setPc(busBuffer);
	}
	
	/**
	 * Get instruction from memory and update PC to the address of next instruction
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	private void getInstr(){
		/*get address from PC and fetch MAR*/
		OutregsINF.setMAR(PcINF.getPc());
		
		/*get content from memory based on the address in MAR
		 * and fetch into MBR
		 * */
		
		/*read memory to MBR*/
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		
		/*fetch instruction from MBR to IR*/
		OutregsINF.setIR(OutregsINF.getMBR());
		
		/*decode IR instruction*/
		DecodeINF.decode();
		
		/*update PC to point at the address of next instruction*/
		PcINF.pcAdder(offset);
	}
	
	
	/**
	 * control instruction circles
	 * 
	 * @param
	 * @return execStat  indicating the state of instruction execution
	 * @exception
	 */
	private void run(){
		/*simulate the instruction circuit*/
		while(true){
			
			/*circle: get instruction*/
			getInstr();
			
			/*circle: execute instruction*/
			
			/*get opcode*/
			String opcode = OutregsINF.getOPCODE().getStr();
			
			/*execute instruction based on opcode*/
			if(opcode.equals("000001")){
				IsaControl.execLdr();
			}
			if(opcode.equals("000000")){
				break;
			}
		}
	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
		ISA.run();
	    System.out.println(GrINF.getR0().getStr());
	    System.out.println(GrINF.getR1().getStr());
	}	
}
