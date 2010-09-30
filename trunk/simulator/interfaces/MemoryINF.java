/*
 * MemoryINF.java
 * Created on 09-30-2010
 * By Yichao Yu
 */
package simulator.interfaces;

import java.io.IOException;
import simulator.memory.*;

/** 
 * Class MemoryINF
 * interface layer of Memory and ROMloader
 *                          
 * @author Yichao Yu
 * @version 09-30-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class MemoryINF {
	/**
	 * load "filename" file into memory from ROMloader.
	 * 
	 * @param filename	the "binary" file to be loaded.
	 * @return String	first instruction address.
	 * @exception 
	 */
	public static String ROMload(String filename) {
		String enteraddr = new String();
		try {
			enteraddr = ROMloader.LoadToMemory(filename);
		}
		catch (IOException e) {
			System.out.println("no such file");
		}
		return enteraddr;
	}
	
	/**
	 * store the content of MBR into memory.
	 * Address must be ready in MAR.
	 * Also MBR must ready.
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static void storeMemory() {
		Memory.setContentFromMBR();
	}
	
	/**
	 * load the content into MBR.
	 * Address must be ready in MAR.
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static void loadMemory() {
		Memory.getContentToMBR();
	}
}
