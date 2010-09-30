/*
 * Memory.java
 * Created on 09-25-2010
 * By Yichao Yu
 */

package simulator.memory;

import java.util.*;
import simulator.interfaces.*;
import simulator.formatstr.*;

public class Memory {
	private static Hashtable<Integer, String> mem = 
		new Hashtable<Integer, String>();
	private static Integer length = new Integer(0);	//the last address of the content.
	/**
	 * Default constructor
	*/
	Memory() {}
	
	/**
	 * using the data/instruction write MBR
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return 
	 * @exception 
	 */
	public static boolean initLine(String content) {
		mem.put(length, content);
		length++;
		
		return true;
	}
	
	public static void autoPaddle() {
		while(length < 16384) {
			mem.put(length, "000000000000000000000000");
			length++;
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
	 * 
	 * @param
	 * @return 
	 * @exception 
	 */
	public static void getContentToMBR() {
		String mar = new String(Memory.readMAR().getStr());
		Formatstr content = new Formatstr(Memory.mem.get(Integer.parseInt(mar, 2)));
		
		Memory.writeMBR(content);
	}
	
	/**
	 * 
	 * 
	 * @param
	 * @return 
	 * @exception 
	 */
	public static void setContentFromMBR() {
		String mar = new String(Memory.readMAR().getStr());
		Formatstr content = new Formatstr();
		
		content = Memory.readMBR();
		Memory.mem.remove(Integer.parseInt(mar, 2));
		Memory.mem.put(Integer.parseInt(mar, 2), content.getStr());
	}
	
}
