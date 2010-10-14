/*
 * ROMloader.java
 * Created on 09-29-2010
 * By Yichao Yu
 */
package simulator.memory;

import java.io.*;

/** 
 * Class ROMloader
 * ROM loader operation. Load content of ROM into Memory
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
	 *  load the content(data and instruction) to Memory 
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return String	program's instruction first address(14 bits).
	 * @exception 
	 */
	public static String LoadToMemory(String filename) throws IOException {
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
		
		//System.out.println("test");
		return enteraddr;
	}
}
