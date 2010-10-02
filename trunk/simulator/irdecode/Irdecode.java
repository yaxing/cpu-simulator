/*
 * Decode.java
 * Created on 09-27-2010
 * By Lei Li
 */

package simulator.irdecode;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class Decode
 * Decode the instruction in the IS register, 
 * and put each part into different register.
 *                          
 * @author Lei Li
 * @version 10-2-2010
 * @see simulator.isdecode
 * @since JDK 1.6
 */

public class Irdecode {
	
	/**Store the instruction*/
	private Formatstr is;
	
	/**Store the opcode of a instruction*/
	private Formatstr opcode;
	
	/**Store the I-bit of a instruction, if there exists*/
	private Formatstr iBit;
	
	/**Store the general register # of a instruction, if there exists*/
	private Formatstr rop1;
	private Formatstr rop2;
	
	/**Store the immediate number of a instruction, if there exists*/
	private Formatstr imNum;
	
	/**Store the flags for Shift/Rotate instructions*/
	private Formatstr lr;
	private Formatstr ar;
	
	/**
	 * Decode instructions
	 * 
	 * @param 
	 * @return 
	 * @exception
	 */
	public void decode() {
		is = OutregsINF.getIR(); //read the instruction from IR
		opcode = new Formatstr();
		opcode.setStr(is.getStr().substring(0, 6)); //get opcode from instruction
		
		rop1 = new Formatstr();
		rop1.setStr(is.getStr().substring(7, 9)); //get the general register number from instruction
		
		OutregsINF.setOPCODE(opcode); //send opcode to register
		OutregsINF.setROP1(rop1); //send general register number to register
		
		int intcode = Integer.valueOf(opcode.getStr(), 2);
		
		/**
		 * the formats of instruction differ from opcode
		 * so decode according to different opcode
		 */	
		//basic instruction format
		if (intcode >= 1 && intcode <=20){
			iBit = new Formatstr();
			rop2 = new Formatstr();
			imNum = new Formatstr();
			iBit.setStr(is.getStr().substring(6, 7)); //get i-bit
			rop2.setStr(is.getStr().substring(9, 11)); //get the other general register
			imNum.setStr(is.getStr().substring(11, 24)); //get the address or immediate operand
			
			OutregsINF.setIBIT(iBit); //send i-bit to register
			OutregsINF.setROP2(rop2); //send the other general number to register
			
			//operation with immediate number
			if (intcode == 6 || intcode == 7){
				OutregsINF.setIN2(imNum);
			}
			//operation with memory address
			else{
				OutregsINF.setOPD(imNum);
			}
		}
		
		//Shift and Rotate instructions format
		if (intcode == 21 || intcode == 22){
			lr = new Formatstr();
			ar = new Formatstr();
			imNum = new Formatstr();
			lr.setStr(is.getStr().substring(6, 7)); //get L/R flag
			ar.setStr(is.getStr().substring(9, 10)); //get A/R flag
			imNum.setStr(is.getStr().substring(19, 24)); //get the count number
			
			OutregsINF.setLR(lr); //send L/R flag to register
			OutregsINF.setAR(ar); //send A/R flag to register
			OutregsINF.setIN2(imNum); //send count number to SRFU
		}
		
		//I/O Operation instruction format
		if (intcode >= 61 && intcode <= 63){
			imNum = new Formatstr();
			imNum.setStr(is.getStr().substring(19, 24)); //get device ID
			
			OutregsINF.setDEVID(imNum); //send device ID to register
		}
		
	}

}
