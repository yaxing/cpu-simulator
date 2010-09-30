/*
 * Controller.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.controller;
import simulator.interfaces.*;

/** 
 * Class Controller
 * Control ISA instructions execution
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class Controller {
	
	/**For address or data buffer, temporarily store address, instructions or data get from other modules*/
	private static String busBuffer;
	
	/**Define the end instruction*/
	private static String endInstr = "HLT";
	
	/**Define the end instruction*/
	private static String insFile = "./instruction.txt";
	
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
		//MemoryINF.storeMemory();
		MemoryINF.loadMemory();
		busBuffer = "0";
		StrINF.setStr(busBuffer);
		PcINF.setPc(StrINF.getStr());
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
		while(true){
			
		}

	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
		ISA.execInstr();
	    
	}	
}
