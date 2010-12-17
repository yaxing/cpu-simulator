/*
 * Pc.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.pc;
import simulator.formatstr.*;
import simulator.interfaces.TraceINF;

/** 
 * Class Pc
 * control PC
 *                          
 * @author Yaxing Chen
 * @version 09-30-2010
 * @see simulator.pc
 * @since JDK 1.6
 */
public class Pc {
	/**address of next instruction*/
	private static Formatstr instrAdd = new Formatstr();
	
	/**
	 * Guarantee that PC is 24 bits
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void formatPc(Formatstr pc){
		String format = "00000000000000000000000";
		String temp = pc.getStr();
		if(temp == null){
			return;
		}
		if(temp.length() < 24){
			pc.setStr(format.substring(0,24 - pc.getStr().length()) + temp);
		}
		else if (temp.length() > 24){
			
		}
		return;
	}
	
	/**
	 * Execute push instruction address into PC
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void setPc(Formatstr add){
		TraceINF.write("Fetch PC.");
		formatPc(add);
		instrAdd.setStr(add.getStr());
	}
	
	/**
	 * Get PC content
	 * 
	 * @param
	 * @return Formatstr PC content
	 * @exception
	 */
	public static Formatstr getPc(){
		TraceINF.write("Get PC.");
		return instrAdd;
	}
	
	/**
	 * Add offset to PC content
	 * count next instruction address
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void pcAdder(Formatstr offset){
		TraceINF.write("PC adder working...");
		Integer PcAdd = Integer.parseInt(instrAdd.getStr(),2);
		Integer off = Integer.parseInt(offset.getStr(),2);
		PcAdd = PcAdd + off;
		Formatstr temp = new Formatstr();
		temp.setStr(Integer.toBinaryString(PcAdd));
		setPc(temp);
		TraceINF.write("PC adder finished.");
	}
	
	/**
	 * Get executing PC address
	 * Get the address of the executing instruction
	 * 
	 * @param 
	 * @return 
	 * @exception
	 */
	public static Formatstr getFormerPc(){
		Integer curPc = Integer.parseInt(getPc().getStr(),2);
		curPc --;
		Formatstr tmp = new Formatstr();
		tmp.setStr(Integer.toBinaryString(curPc));
		formatPc(tmp);
		return tmp;
	}
}
