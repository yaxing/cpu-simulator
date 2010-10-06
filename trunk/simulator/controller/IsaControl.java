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
 * @version 10-05-2010
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
		int gr = Integer.parseInt(ix);
		String offset = new String();
		switch(gr){
		case 0:
			offset = GrINF.getR0().getStr();
			break;
		case 1:
			offset = GrINF.getR1().getStr();
			break;
		case 2:
			offset = GrINF.getR2().getStr();
			break;
		case 3:
			offset = GrINF.getR3().getStr();
			break;
		default:
			break;
			
		}
		/*get Address from OPD*/
		String address = OutregsINF.getOPD().getStr();
		
		/*add them together and store in buffer as needed address*/
		//Integer ea = Integer.parseInt(ix,2) + Integer.parseInt(address,2);
		
		offset = offset.substring(0,offset.length()-address.length());
		address = offset + address;
		buffer.setStr(address);
		buffer.formatAddress();
	}
	
	/**
	 * Direct address
	 * get EA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void dirGetEa(){
		pendIxGr();
	}
	
	/**
	 * Indirect address
	 * get EA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void inDirGetEa(){
		pendIxGr();
		OutregsINF.setMAR(buffer);
		
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		buffer = OutregsINF.getMBR();
	}
	
	/**
	 * Generate EA based on direct or indirect address
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void genEa(){
		/*
		 * judge whether it is direct or indirect address
		 * and then execute corresponding process 
		 */
		if(OutregsINF.getIBIT().getStr().equals("0")){
			dirGetEa();
		}
		else{
			inDirGetEa();
		}
	}
	
	/**
	 * Execute instruction LDR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLdr(){
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		OutregsINF.setMAR(buffer);
		
		/*set MBR with memory data from address in MAR*/
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
	}
	
	/**
	 * Execute instruction STR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execStr(){
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		OutregsINF.setMAR(buffer);
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		//set MBR with register content
		switch(gN){
		case 0:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		case 1:
			OutregsINF.setMBR(GrINF.getR1());
			break;
		case 2:
			OutregsINF.setMBR(GrINF.getR2());
			break;
		case 3:
			OutregsINF.setMBR(GrINF.getR3());
			break;
		default:
			break;
		}
		
		//write MBR content into memory unit located in MAR address
		OutregsINF.setMCR(new Formatstr("1"));
		MemoryINF.operateMemory();
	}
	
	/**
	 * Execute instruction LDA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLda(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		//set general register with address
		switch(gN){
		case 0:
			GrINF.setR0(buffer);
			break;
		case 1:
			GrINF.setR1(buffer);
			break;
		case 2:
			GrINF.setR2(buffer);
			break;
		case 3:
			GrINF.setR3(buffer);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Execute instruction JZ
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static Formatstr execJz(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		//set MBR with register content
		String condition = new String();
		switch(gN){
		case 0:
			condition = GrINF.getR0().getStr();
			break;
		case 1:
			condition = GrINF.getR1().getStr();
			break;
		case 2:
			condition = GrINF.getR2().getStr();
			break;
		case 3:
			condition = GrINF.getR3().getStr();
			break;
		default:
			break;
		}
		if(Integer.parseInt(condition) == 0){
			return buffer;
		}
		else
			return null;
	}
	
	/**
	 * Execute instruction JNE
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static Formatstr execJne(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get the target register AC from ROP1*/
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		
		//set MBR with register content
		String condition = new String();
		switch(gN){
		case 0:
			condition = GrINF.getR0().getStr();
			break;
		case 1:
			condition = GrINF.getR1().getStr();
			break;
		case 2:
			condition = GrINF.getR2().getStr();
			break;
		case 3:
			condition = GrINF.getR3().getStr();
			break;
		default:
			break;
		}
		if(Integer.parseInt(condition) != 0){
			return buffer;
		}
		else
			return null;
	}
}
