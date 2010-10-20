/*
 * GrINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.genregs.*;
import simulator.formatstr.*;

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
	
	public static void setR0(Formatstr R0){
		gr.setR0(R0);
	}
	
	public static void setR1(Formatstr R1){
		gr.setR1(R1);
	}
	
	public static void setR2(Formatstr R2){
		gr.setR2(R2);
	}
	
	public static void setR3(Formatstr R3){
		gr.setR3(R3);
	}
	
	public static void setR7(Formatstr R7){
		gr.setR7(R7);
	}
	
	public static Formatstr getR0(){
		return gr.getR0();
	}
	
	public static Formatstr getR1(){
		return gr.getR1();
	}
	
	public static Formatstr getR2(){
		return gr.getR2();
	}
	
	public static Formatstr getR3(){
		return gr.getR3();
	}
	
	public static Formatstr getR7(){
		return gr.getR7();
	}
	
	public static void clear(){
		gr.clear();
	}
}
