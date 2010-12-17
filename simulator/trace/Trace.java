package simulator.trace;

import java.io.*;
import java.util.*;

/** 
 * Class Controller
 * Write trace file
 *                          
 * @author Yaxing Chen
 * @version 12-10-2010
 * @see simulator.trace
 * @since JDK 1.6
 */
public class Trace {
	
	private static String traceFile = "log/traceFile.txt";
	private static long initialTime = 0;  
	private static ArrayList<String> traceBuffer  = new ArrayList<String>();
	
	public static void initialTrace(){
		traceFile = "log/traceFile.txt";
		try{
			File trace = new File(traceFile);
			if(trace.exists()){
				trace.delete();
			}
			trace.createNewFile();
		}catch(IOException e){
			
		}
	}
	
	public static void initialTrace(String name){
		traceFile = "log/" + name;
		try{
			File trace = new File(traceFile);
			if(trace.exists()){
				trace.delete();
			}
			trace.createNewFile();
		}catch(IOException e){
			
		}
		Calendar calendar = new GregorianCalendar();
		initialTime = calendar.getTimeInMillis();
	}
	
	/**
	 *Write trace log to buffer
	 * 
	 * @param String contents
	 * @return boolean
	 * @exception
	 */
	public static void write(String trace){
		Calendar calendar = new GregorianCalendar();
		long currentTime = calendar.getTimeInMillis() - initialTime; 
		traceBuffer.add(currentTime + " ms:  " + trace);
		return;
	}

	/**
	 *write to file
	 * 
	 * @param 
	 * @return ArrayList<String>
	 * @exception
	 */
	public static boolean flushTraceBuffer(){
		try{
			File file = new File(traceFile);
			if(!file.exists()){
				file.createNewFile();
			}
			ArrayList<String> log = read();
			String contents = new String();
			for(String line : log){
				contents += line + "\r\n";
			}
			for(String line : traceBuffer){
				contents += line+"\r\n";
			}
			BufferedWriter in = new BufferedWriter(new FileWriter(file));
			in.write(contents);
			in.flush();
			//in.append(trace);
			in.close();
			traceBuffer.clear();
		}catch(IOException e){
			return false;
		}
		return true;
	}
	
	/**
	 *Read trace file
	 * 
	 * @param 
	 * @return ArrayList<String>
	 * @exception
	 */
	public static ArrayList<String> read(){
		ArrayList<String> contents = new ArrayList<String>();
		try{
			File file = new File(traceFile);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String buffer = "";
			while((buffer = in.readLine()) != null){
				contents.add(buffer);
			}
			in.close();
		}catch(IOException e){
			return null;
		}
		return contents;
	}
	
	public static void main(String[] args){
		Trace.initialTrace("running log of test");
		Trace.write("test");
		Trace.write("lalala");
		ArrayList<String> out = Trace.read();
		for(String a : out){
			System.out.println(a);
		}
	}
}
