/*
 * Memory.java
 * Created on 09-25-2010
 * By Yichao Yu
 */

package simulator.memory;

import java.util.*;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class Memory
 * Memory operation:load, store and device.
 * Memory uses 4 memory banks. When there is a visit memory instruction,
 * it will check the MAR, find the bank number and get or set the memory content. 
 *                          
 * @author Yichao Yu
 * @version 09-25-2010
 * @see simulator.memory
 * @since JDK 1.6
 */
public class Memory {
	private static Integer length = new Integer(0);	//the last address of the content.
	private static final int numBank = 4;
	private static final int memSize = 16384;
	private static final int bankSize = 4096;
	
	private static String[] bank0 = new String[bankSize];
	private static String[] bank1 = new String[bankSize];
	private static String[] bank2 = new String[bankSize];
	private static String[] bank3 = new String[bankSize];
	
	
	private static String memAddr = "00000000000010";		//load data and instruction from 2
															//because the first two addresses are invalid 

	/**
	 * Default constructor
	*/
	Memory() {}
	
	/**
	 * initialize the memory, set data of all banks to 0. 
	 */
	public static void setZero() {
		int i = 0;
		while(i < memSize / 4) {
			bank0[i] = "000000000000000000000000";
			bank1[i] = "000000000000000000000000";
			bank2[i] = "000000000000000000000000";
			bank3[i] = "000000000000000000000000";
			i++;
		}
	}
	
	/**
	 * set the first instruction's address
	 * 
	 * @param enteraddr	the first instruction's address
	 */
	public static void setEnterAddr(String enteraddr) {
		memAddr = enteraddr;
	}
	
	/**
	 * load content of ROM to memory
	 * 
	 * @param content	binary content per line.
	 */
	public static boolean initLine(String content) {
		String format = "00000000000000";
		
		int address = Integer.valueOf(memAddr.substring(0, memAddr.length() - 2), 2);
		getBank(memAddr)[address] = content;
		memAddr = Integer.toBinaryString(Integer.valueOf(memAddr, 2) + 1);
		memAddr = format.substring(0, format.length() - memAddr.length()) + memAddr;
		return true;
	}
	
	/**
	 * according to address string, get the corresponding bank
	 * 
	 * @param addr	memory address
	 * @return String[] the corresponding banks
	 */
	private static String[] getBank(String addr) {
		int tail = Integer.valueOf(addr.substring(addr.length()-2, addr.length()), 2);
		
		switch (tail) {
			case 0:
				return bank0;
			case 1: 
				return bank1;
			case 2:
				return bank2;
			default:
				return bank3;
		}
	}
	
	/**
	 * write MBR with the content of corresponding memory.
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 */
	private static void writeMBR(Formatstr mbr) {
		OutregsINF.setMBR(mbr);
	}
	
	/**
	 * get the content of MBR to write the corresponding address
	 * 
	 * @return the data written into the memory
	 */
	private static Formatstr readMBR() {
		return OutregsINF.getMBR();
	}
	
	/**
	 * read address from MAR
	 * 
	 * @return the content of the MAR
	 */
	private static Formatstr readMAR() {
		return OutregsINF.getMAR();
	}
	
	/**
	 * Get the content of the address ready in the MBR.
	 * May have some exception, ie: a wrong address.
	 * Address(14 bits) must be ready in MAR.
	 */
	public static void getContentOfMBR() {
		String mar = new String(readMAR().getStr());
		TraceINF.write("Read memory "+mar);
		Formatstr content = new Formatstr();
//		System.out.println("index"+Integer.parseInt(mar.substring(0, mar.length() - 2), 2));
		content.setStr(getBank(mar)[Integer.parseInt(mar.substring(0, mar.length() - 2), 2)]);
		
		writeMBR(content);
		TraceINF.write("Read finished.");
	}
	
	/**
	 * store the content of MBR into memory.
	 * Address(14 bits) must be ready in MAR.
	 * Also MBR must ready.
	 */
	public static void setContentOfMBR() {
//		System.out.println("mar"+readMAR().getStr());
		String mar = new String(readMAR().getStr());
		TraceINF.write("Write memory "+mar);
		Formatstr content = new Formatstr();
		
		content = readMBR();
		getBank(mar)[Integer.parseInt(mar.substring(0, mar.length() - 2), 2)] = content.getStr();
		TraceINF.write("Write finished.");
	}
	
}
