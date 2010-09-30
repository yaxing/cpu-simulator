/*
 * PcINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.pc.*;
import simulator.formatstr.*;

/** 
 * Class PcINF
 * as the interface layer of Class PC, 
 * other modules call pc through this class
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class PcINF {
	/**
	 * Instantiate objects for pc
	 */
	public static Pc pc = new Pc();
	
	/**
	 * set pc
	 * 
	 * @param String add  next instruction's address
	 * @return 
	 * @exception 
	 */
	public static void setPc(Formatstr add){
		pc.setPc(add);
		return;
	}
	
	/**
	 * get pc
	 * 
	 * @param
	 * @return String the content of PC
	 * @exception 
	 */
	public static Formatstr getPc(){
		return pc.getPc();
	}
	
	/**
	 * add pc
	 * 
	 * @param
	 * @return
	 * @exception 
	 */
	public static void pcAdder(Formatstr offset){
		pc.pcAdder(offset);
		return;
	}
}
