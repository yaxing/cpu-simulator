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
	 * @return 
	 * @exception 
	 */
	public static void ROMload(String filename) {
		try {
			ROMloader.LoadToMemory(filename);
		}
		catch (IOException e) {
			System.out.println("no such file");
		}
	}
	
	/**
	 * store the content of MBR into memory.
	 * Address has already been ready in MAR.
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
	 * Address has already been ready in MAR.
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static void loadMemory() {
		Memory.getContentToMBR();
	}
}
