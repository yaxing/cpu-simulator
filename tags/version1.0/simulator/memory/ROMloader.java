/*
 * ROMloader.java
 * Created on 09-29-2010
 * By Yichao Yu
 */
package simulator.memory;

import java.io.*;

import simulator.interfaces.TraceINF;

/** 
 * Class ROMloader
 * ROM loader operation. Load content of ROM(file) into Memory.
 * May change the implement of this module as a IO device later.
 * Use the card reader to read the card(file) and store in the ROM,
 * and then load into the memory.
 *                          
 * @author Yichao Yu
 * @version 09-29-2010
 * @see simulator.memory
 * @since JDK 1.6
 */
public class ROMloader {
	//used for test
	/*public static void main(String[] args) {
		try {
			Memory.setZero();
			ROMloader.LoadToMemory("input.txt");
		}
		catch (IOException e) {
			System.out.println("no such file");
		}
		System.exit(0);
	}*/
	/**
	 * After press the IPL button, read the "filename" file,
	 *  load the content(data and instruction) to Memory.
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return String	program's instruction entrance address(14 bits).
	 * @exception IOException if there is no proper file with the filename,
	 * 						it will cast an IOException
	 */
	public static String LoadToMemory(String filename) throws IOException {
		TraceINF.write("ROM loader loading program into memory...");
		File filein = new File(filename);
		FileReader fr = new FileReader(filein);			
		BufferedReader in = new BufferedReader(fr);		
		
		String line;
		String enteraddr;
		
		enteraddr = in.readLine();
		
		Memory.setEnterAddr(enteraddr);
		while((line = in.readLine()) != null) {
			Memory.initLine(line);			
		}
		TraceINF.write("loading finished.");
		return enteraddr;
	}
}
