/*
 * OutregsINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.interfaces;
import simulator.outerregs.*;

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
	
	public static void setOPD(String opd){
		or.setOPD(opd);
	}

	public static void setMAR(String mar){
		or.setMAR(mar);
	}
	
	public static void setMBR(String mbr){
		or.setMBR(mbr);
	}
	
	public static void setMCR(String mcr){
		or.setMCR(mcr);
	}
	
	public static void setIR(String ir){
		or.setIR(ir);
	}
	
	public static String getOPD(){
		return or.getOPD();
	}
	
	public static String getMBR(){
		return or.getMBR();
	}
	
	public static String getMAR(){
		return or.getMAR();
	}
	
	public static String getMCR(){
		return or.getMCR();
	}
	
	public static String getIR(){
		return or.getIR();
	}
}
