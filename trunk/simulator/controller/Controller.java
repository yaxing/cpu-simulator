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
		if(Interface.loadToRom()){
			busBuffer = Interface.loadToMem();
			Interface.setStr(busBuffer);
			Interface.setPc(Interface.getStr());
			return true;
		}
		return false;
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
			/*get current instruction's address*/
			busBuffer = Interface.getPc();
			
			busBuffer = Interface.getInstrFromMem(busBuffer);
			
			
			
			execStat = 1;
			break;
		}
		return execStat;
	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
		ISA.execInstr();
	    
	}	
}
