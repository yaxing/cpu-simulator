/*
 * Memory.java
 * Created on 09-25-2010
 * By Yichao Yu
 */

package simulator.memory;

import simulator.interfaces.*;
import simulator.outerregs.*;
import simulator.formatstr.*;

public class Memory {
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
	
	
	public static void GetContent(Formatstr addr) {
		
	}
	
	
	
}
