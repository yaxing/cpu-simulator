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
	 * @return 
	 * @exception 
	 */
	public static void LoadToMemory(String filename) throws IOException {
		File filein = new File(filename);
		FileReader fr = new FileReader(filein);			
		BufferedReader in = new BufferedReader(fr);		
		
		String line;
		while((line = in.readLine()) != null) {
			Memory.initLine(line);			
		}
		Memory.autoPaddle();
		
		//System.out.println("test");
	}
}
