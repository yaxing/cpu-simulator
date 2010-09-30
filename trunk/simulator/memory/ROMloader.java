/*
 * ROMloader.java
 * Created on 09-29-2010
 * By Yichao Yu
 */
package simulator.memory;

import java.io.*;

public class ROMloader {
	
	//used for test
	/*public static void main(String[] args) {
		try {
			ROMloader.LoadToMemory("input.txt");
		}
		catch (IOException e) {
			System.out.println("no such file");
		}
		System.exit(0);
	}*/
	/**
	 * Read the "filename" file, load the content(data and instruction)
	 * to Memory 
	 * 
	 * @param mbr	the data/instruction need to write into MBR.
	 * @return String	program's instruction first address.
	 * @exception 
	 */
	public static String LoadToMemory(String filename) throws IOException {
		File filein = new File(filename);
		FileReader fr = new FileReader(filein);			
		BufferedReader in = new BufferedReader(fr);		
		
		String line;
		String enteraddr;
		
		enteraddr = in.readLine();
		
		while((line = in.readLine()) != null) {
			Memory.initLine(line);			
		}
		Memory.autoPaddle();
		
		return enteraddr;
		//System.out.println("test");
	}
}
