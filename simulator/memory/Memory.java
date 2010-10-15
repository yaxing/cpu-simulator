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
	
	
	private static String memAddr = "00000000000010";		//for 
	private static String enterAddr;
	/**
	 * Default constructor
	*/
	Memory() {}
	
	/**
	 * set data of all banks to zero
	 * 
	 * @param
	 * @return 
	 * @exception 
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
	 * @param enteraddr		the first instruction's address
	 * @return 
	 * @exception 
	 */
	public static void setEnterAddr(String enteraddr) {
		enterAddr = enteraddr;
	}
	
	/**
	 * load content of ROM to mem
	 * 
	 * @param content	binary content per line.
	 * @return 
	 * @exception 
	 */
	public static boolean initLine(String content) {
		String format = "00000000000000";
		
		int address = Integer.valueOf(memAddr.substring(0, memAddr.length() - 2), 2);
		//System.out.println("memory address "+Integer.valueOf(enterAddr, 2));
		//System.out.println("bank address "+address);
		getBank(memAddr)[address] = content;
		memAddr = Integer.toBinaryString(Integer.valueOf(memAddr, 2) + 1);
		memAddr = format.substring(0, format.length() - memAddr.length()) + memAddr;
		return true;
	}
	
	/**
	 * according to address string, get the corresponding bank
	 * 
	 * @param addr	memory address
	 * @return 
	 * @exception 
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
	 * using the data/instruction write MBR
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return 
	 * @exception 
	 */
	private static void writeMBR(Formatstr mbr) {
		OutregsINF.setMBR(mbr);
	}
	
	/**
	 * using MBR write the corresponding address
	 * 
	 * @param 
	 * @return the data written into the memory
	 * @exception 
	 */
	private static Formatstr readMBR() {
		return OutregsINF.getMBR();
	}
	
	/**
	 * read address from MAR
	 * 
	 * @param 
	 * @return the content of the MAR
	 * @exception 
	 */
	private static Formatstr readMAR() {
		return OutregsINF.getMAR();
	}
	
	/**
	 * Get the content of the address ready in the MBR.
	 * May have some exception, ie: a wrong address.
	 * Address(14 bits) must be ready in MAR.
	 * 
	 * @param
	 * @return 
	 * @exception 
	 */
	public static void getContentToMBR() {
		String mar = new String(readMAR().getStr());
		Formatstr content = new Formatstr();
		content.setStr(getBank(mar)[Integer.parseInt(mar.substring(0, mar.length() - 2), 2)]);
		
		writeMBR(content);
	}
	
	/**
	 * store the content of MBR into memory.
	 * Address(14 bits) must be ready in MAR.
	 * Also MBR must ready.
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static void setContentFromMBR() {
		String mar = new String(readMAR().getStr());
		Formatstr content = new Formatstr();
		
		content = readMBR();
		getBank(mar)[Integer.parseInt(mar.substring(0, mar.length() - 2), 2)] = content.getStr();
	}
	
}
