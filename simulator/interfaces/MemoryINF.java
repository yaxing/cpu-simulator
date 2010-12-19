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
	 * set memory's(banks) all data to zero
	 */
	public static void initMem() {
		Memory.setZero();
	}
	
	/**
	 * call the method to load "filename" file into memory from ROMloader.
	 * 
	 * @param filename	the instruction file to be loaded.
	 * @return String	first instruction address(13 bits).
	 */
	public static String ROMload(String filename) {
		String enteraddr = new String();
		try {
			enteraddr = ROMloader.LoadToMemory(filename);
		}
		catch (IOException e) {
			System.out.println("no such file");
		}
		return enteraddr.substring(1, enteraddr.length());
	}
	
	/**
	 * according to MCR, do the corresponding operation
	 * 0 is load, 1 is store
	 * 
	 * @return boolean whether the operation is correct.
	 */
	public static boolean operateMemory() {
		if(OutregsINF.getMCR().getStr().equals("0"))	//0 is load, 1 is store
			Memory.load();
		else if(OutregsINF.getMCR().getStr().equals("1"))
			Memory.store();
		else return false;
		return true;
	}
}
