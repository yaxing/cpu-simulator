/*
 * OutregsINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.interfaces;
import simulator.outerregs.*;
import simulator.formatstr.*;

/** 
 * Class Outregs
 * interface layer of Outregs class
 * other modules call Outregs functions through this class
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class OutregsINF {
	private static Outregs or = new Outregs();
	
	public static void setOPD(Formatstr opd){
		or.setOPD(opd);
	}

	public static void setMAR(Formatstr mar){
		or.setMAR(mar);
	}
	
	public static void setMBR(Formatstr mbr){
		or.setMBR(mbr);
	}
	
	public static void setMCR(Formatstr mcr){
		or.setMCR(mcr);
	}
	
	public static void setIR(Formatstr ir){
		or.setIR(ir);
	}
	
	public static void setMSR(Formatstr msr){
		or.setMSR(msr);
	}
	
	public static Formatstr getOPD(){
		return or.getOPD();
	}
	
	public static Formatstr getMBR(){
		return or.getMBR();
	}
	
	public static Formatstr getMAR(){
		return or.getMAR();
	}
	
	public static Formatstr getMCR(){
		return or.getMCR();
	}
	
	public static Formatstr getIR(){
		return or.getIR();
	}
	
	public static Formatstr getMSR(){
		return or.getMSR();
	}
}
