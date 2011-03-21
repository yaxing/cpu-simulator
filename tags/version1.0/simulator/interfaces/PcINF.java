/*
 * PcINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.pc.*;
import simulator.bht.Bht;
import simulator.formatstr.*;

/** 
 * Class PcINF
 * as the interface layer of Class PC, 
 * other modules call pc through this class
 *                          
 * @author Yaxing Chen
 * @version 09-30-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class PcINF {
	/**
	 * Instantiate objects for pc
	 */
	//public static Pc pc = new Pc();
	
	/**
	 * set pc
	 * 
	 * @param String add  next instruction's address
	 * @return 
	 * @exception 
	 */
	public static void setPc(Formatstr add){
		Pc.setPc(add);
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
		return Pc.getPc();
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
		return Pc.getFormerPc();
	}
	
	/**
	 * add pc
	 * 
	 * @param
	 * @return
	 * @exception 
	 */
	public static void pcAdder(Formatstr offset){
		Pc.pcAdder(offset);
		return;
	}
}
