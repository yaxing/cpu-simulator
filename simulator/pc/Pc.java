/*
 * Pc.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.pc;
import simulator.formatstr.*;

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
		String format = "000000000000000000000000";
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
		Integer PcAdd = Integer.parseInt(instrAdd.getStr(),2);
		Integer off = Integer.parseInt(offset.getStr(),2);
		PcAdd = PcAdd + off;
		Formatstr temp = new Formatstr();
		temp.setStr(Integer.toBinaryString(PcAdd));
		setPc(temp);
	}
}
