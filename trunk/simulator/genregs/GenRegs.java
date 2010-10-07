/*
 * Genregs.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.genregs;
import simulator.formatstr.*;

/** 
 * Class Genregs
 * control all four general registers
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.genregs
 * @since JDK 1.6
 */
public class GenRegs {
	private static String format = "000000000000000000000000";
	private static Formatstr R0 = new Formatstr(format);
	private static Formatstr R1 = new Formatstr(format);
	private static Formatstr R2 = new Formatstr(format);
	private static Formatstr R3 = new Formatstr(format);
	private static Formatstr R7 = new Formatstr(format);
	
	public void setR0(Formatstr R0){
		this.R0 = R0;
	}
	
	public void setR1(Formatstr R1){
		this.R1 = R1;
	}
	
	public void setR2(Formatstr R2){
		this.R2 = R2;
	}
	
	public void setR3(Formatstr R3){
		this.R3 = R3;
	}
	
	public void setR7(Formatstr R7){
		this.R7 = R7;
	}
	
	public Formatstr getR0(){
		return R0;
	}
	
	public Formatstr getR1(){
		return R1;
	}
	
	public Formatstr getR2(){
		return R2;
	}
	
	public Formatstr getR3(){
		return R3;
	}
	
	public Formatstr getR7(){
		return R7;
	}
}
