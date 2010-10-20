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
 * @version 10-16-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class Controller {
	
	/**For address or data buffer, temporarily store address, instructions or data get from other modules*/
	private static Formatstr busBuffer = new Formatstr();
		
	/**Define the end instruction*/
	private static String insFile = "inTest.txt";
	
	/**Define PC offset*/
	private static Formatstr offset = new Formatstr("000000000000000000000001");
	
	/**Define the debug model flag
	 * if false, then it's normal running model
	 * if true, then it's in debug model
	 */
	public static boolean isDebugModel = false;
	
	/**Define the debug flag
	 * if false, then wait
	 * if true, then execute next instruction
	 */ 
	public static boolean debugNext = false;
	
	
	
	/**
	 * Default constructor
	*/
	public Controller(){
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
	public void initial(){
		
		/*initial devices*/
		//DevicesINF.initDevices();
		
		/*fetch instructions to rom and get the entry address of instructions*/
		busBuffer.setStr(MemoryINF.ROMload(insFile));
		
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
	public void getInstr(){
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
	}
	
	/**
	 * select corresponding instruction execution strategy
	 * based on opcode
	 * 
	 * @param String opcode-operation code
	 * @return boolean state-if true than continue execution
	 *                       else stop
	 * @exception
	 */
	public boolean execOpcode(String opcode){
		/*LDR*/
		if(opcode.equals("000001")){
			IsaControl.execLdr();
		}
		/*STR*/
		else if(opcode.equals("000010")){
			IsaControl.execStr();
		}
		/*LDA*/
		else if(opcode.equals("000011")){
			IsaControl.execLda();
		}
		/*JZ*/
		else if(opcode.equals("001000")){
			IsaControl.execJz();
		}
		/*JNE*/
		else if(opcode.equals("001001")){
			IsaControl.execJne();
		}
		/*JMP*/
		else if(opcode.equals("001011")){
			IsaControl.execJmp();
		}
		/*JSR*/
		else if(opcode.equals("001100")){
			IsaControl.execJsr();
		}
		/*RFS*/
		else if(opcode.equals("001101")){
			IsaControl.execRfs();
		}
		/*SOB*/
		else if(opcode.equals("001110")){
			IsaControl.execSob();
		}
		/*ADD or SUB*/
		else if(opcode.equals("000100") || opcode.equals("000101")){
			IsaControl.execAddSub();
		}
		/*AIR or SIR*/
		else if(opcode.equals("000110") || opcode.equals("000111")){
			IsaControl.execAirSir();
		}
		/*IN*/
		else if(opcode.equals("111101")){
			IsaControl.execIn();
		}
		/*OUT*/
		else if(opcode.equals("111110")){
			IsaControl.execOut();
		}
		/*HLT*/
		else if(opcode.equals("000000")){
			return false;
		}
		return true;
	}
	
	/**
	 * control instruction circles
	 * 
	 * @param
	 * @return execStat  indicating the state of instruction execution
	 * @exception
	 */
	public void run(){
		
		/*simulate the instruction circuit*/
		while(true){
			
			/*if debugging, wait until user continue*/
			if(isDebugModel){
				
				while(!debugNext){}
				debugNext = false;
			}
			/*circle: get instruction*/
			getInstr();			
			
			/*update PC to point at the address of next instruction*/
			PcINF.pcAdder(offset);
			
			/*circle: execute instruction*/
			/*get opcode*/
			String opcode = OutregsINF.getOPCODE().getStr().substring(18,24);
			
			/*execute instruction based on opcode*/
			if(!execOpcode(opcode)){
				break;
			}
		}
	}
}
