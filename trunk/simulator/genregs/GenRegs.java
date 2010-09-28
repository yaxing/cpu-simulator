/*
 * Genregs.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.genregs;

/** 
 * Class Genregs
 * control all four general registers
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.genregs
 * @since JDK 1.6
 */
public class Genregs {
	private String R0;
	private String R1;
	private String R2;
	private String R3;
	
	public void setR0(String R0){
		this.R0 = R0;
	}
	
	public void setR1(String R1){
		this.R1 = R1;
	}
	
	public void setR2(String R2){
		this.R2 = R2;
	}
	
	public void setR3(String R3){
		this.R3 = R3;
	}
	
	public String getR0(){
		return R0;
	}
	
	public String getR1(){
		return R1;
	}
	
	public String getR2(){
		return R2;
	}
	
	public String getR3(){
		return R3;
	}
}
