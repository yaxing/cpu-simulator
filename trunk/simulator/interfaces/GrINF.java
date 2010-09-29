/*
 * GrINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.genregs.*;;

/** 
 * Class GrINF
 * as the interface layer of Class GrINF, 
 * other modules call Genregs through this class
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class GrINF {
	private static GenRegs gr = new GenRegs();	
	
	public static void setR0(String R0){
		gr.setR0(R0);
	}
	
	public static void setR1(String R1){
		gr.setR1(R1);
	}
	
	public static void setR2(String R2){
		gr.setR2(R2);
	}
	
	public static void setR3(String R3){
		gr.setR3(R3);
	}
	
	public static String getR0(){
		return gr.getR0();
	}
	
	public static String getR1(){
		return gr.getR1();
	}
	
	public static String getR2(){
		return gr.getR2();
	}
	
	public static String getR3(){
		return gr.getR3();
	}
}
