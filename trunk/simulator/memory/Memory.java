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
	private static Hashtable<String, String> Mem = 
		new Hashtable<String, String>();
	
	/**
	 * Default constructor
	*/
	Memory() {}
	
	public static boolean init() {
		
		
		
		return true;
	}
	/**
	 * using the data/instruction write MBR
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return 
	 * @exception 
	 */
	private void WriteMBR(Formatstr mbr) {
		OutregsINF.setMBR(mbr);
	}
	
	/**
	 * using MBR write the corresponding address
	 * 
	 * @param 
	 * @return the data written into the memory
	 * @exception 
	 */
	private Formatstr ReadMBR() {
		return OutregsINF.getMBR();
	}
	
	/**
	 * read address from MAR
	 * 
	 * @param 
	 * @return the content of the MAR
	 * @exception 
	 */
	private Formatstr ReadMAR() {
		return OutregsINF.getMAR();
	}
	
	/**
	 * Get the content of the address ready in the MBR.
	 * May have some exception, ie: a wrong address.
	 * 
	 * @param addr	memory address
	 * @return 
	 * @exception 
	 */
	public static void GetContentToMBR(Formatstr addr) {
		
	}
	
	
	
}
