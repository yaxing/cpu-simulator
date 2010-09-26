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
	/**Instantiate an object of class Interface*/
	static private Interface inface = new Interface();
	
	/**For address buffer, temporarily store address get from other modules*/
	static private String addBuffer;
	
	/**Define the end instruction*/
	static private String endInstr = "HUT";
	
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
		if(inface.loadToRom()){
			addBuffer = inface.loadToMem();
			inface.setPc(addBuffer);
			return true;
		}
		return false;
	}
	
	/**
	 * Run the instructions based on certain logic sequence
	 * 
	 * @param
	 * @return flag  indicating the state of instruction execution
	 * @exception
	 */
	private int runInstr(){
		while(true){
			/*get current instruction's address*/
			addBuffer = inface.getPc();
		}
	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
	}	
}
