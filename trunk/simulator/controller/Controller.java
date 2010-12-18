/*
 * Controller.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.controller;
import simulator.interfaces.*;
import simulator.trace.Trace;
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
	private static String insFile = "";
	
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

		TraceINF.initialTrace("Running log for " + insFile.split("/")[1]);
		TraceINF.write("Initialing...");
		
		
		/*clear register files*/
		GrINF.clear();
		
		/*initialize BHT*/
		BhtINF.initial();
		
		/*fetch instructions to rom and get the entry address of instructions*/
		busBuffer.setStr(MemoryINF.ROMload(insFile));
		/*initiate PC*/
		PcINF.setPc(busBuffer);
		
		TraceINF.write("Initialization finished.");
	}
	
	/**
	 *set instruction file name
	 * 
	 * @param String file name
	 * @exception
	 */
	public void setInstrFile(String instrFile){
		TraceINF.write("User chosed instruction file.");
		insFile = "files/" + instrFile + ".txt";
	}
	
	/**
	 * Get instruction from memory and update PC to the address of next instruction
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public void getInstr(){
		
		TraceINF.write("Fetching instruction to IR...");
		
		/*get address from PC and fetch MAR*/
		TraceINF.write("Fetch MAR with PC.");
		OutregsINF.setMAR(PcINF.getPc());
		
		/*get content from memory based on the address in MAR
		 * and fetch into MBR
		 * */
		
		/*read memory to MBR*/
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		
		/*fetch instruction from MBR to IR*/
		OutregsINF.setIR(OutregsINF.getMBR());
		
		TraceINF.write("Instruction fetched.");
		
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
			TraceINF.write("Executing LDR...");
			IsaControl.execLdr();
			TraceINF.flushTraceBuffer();
		}
		/*STR*/
		else if(opcode.equals("000010")){
			TraceINF.write("Executing STR...");
			IsaControl.execStr();
			TraceINF.flushTraceBuffer();
		}
		/*LDA*/
		else if(opcode.equals("000011")){
			TraceINF.write("Executing LDA...");
			IsaControl.execLda();
			TraceINF.flushTraceBuffer();
		}
		/*JZ*/
		else if(opcode.equals("001000")){
			TraceINF.write("Executing JZ...");
			IsaControl.execJz();
			TraceINF.flushTraceBuffer();
		}
		/*JNE*/
		else if(opcode.equals("001001")){
			TraceINF.write("Executing JNE...");
			IsaControl.execJne();
			TraceINF.flushTraceBuffer();
		}
		/*JMP*/
		else if(opcode.equals("001011")){
			TraceINF.write("Executing JMP...");
			IsaControl.execJmp();
			TraceINF.flushTraceBuffer();
		}
		/*JSR*/
		else if(opcode.equals("001100")){
			TraceINF.write("Executing JSR...");
			IsaControl.execJsr();
			TraceINF.flushTraceBuffer();
		}
		/*RFS*/
		else if(opcode.equals("001101")){
			TraceINF.write("Executing RFS...");
			IsaControl.execRfs();
			TraceINF.flushTraceBuffer();
		}
		/*SOB*/
		else if(opcode.equals("001110")){
			TraceINF.write("Executing SOB...");
			IsaControl.execSob();
			TraceINF.flushTraceBuffer();
		}
		/*ADD or SUB*/
		else if(opcode.equals("000100") || opcode.equals("000101")){
			TraceINF.write("Executing ADD or SUB...");
			IsaControl.execAddSub();
			TraceINF.flushTraceBuffer();
		}
		/*AIR or SIR*/
		else if(opcode.equals("000110") || opcode.equals("000111")){
			TraceINF.write("Executing AIR or SIR...");
			IsaControl.execAirSir();
			TraceINF.flushTraceBuffer();
		}
		/*IN*/
		else if(opcode.equals("111101")){
			TraceINF.write("Executing IN...");
			IsaControl.execIn();
			TraceINF.flushTraceBuffer();
		}
		/*OUT*/
		else if(opcode.equals("111110")){
			TraceINF.write("Executing OUT...");
			IsaControl.execOut();
			TraceINF.flushTraceBuffer();
		}
		/*HLT*/
		else if(opcode.equals("000000")){
			TraceINF.write("HLT");
			TraceINF.flushTraceBuffer();
			return false;
		}
		return true;
	}
	
	/**
	 * control instruction circles
	 * 
	 * @param
	 * @return 
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
	
	public static void main(String[] args){
		Controller test = new Controller();
		test.setInstrFile("test");
		test.initial();
		
		test.run();
	}
}
