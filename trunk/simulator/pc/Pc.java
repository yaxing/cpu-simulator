/*
 * Pc.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.pc;

/** 
 * Class Pc
 * control PC
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.pc
 * @since JDK 1.6
 */
public class Pc {
	private String instrAdd;//address of next instruction
	
	public void setPc(String add){
		//set instruction address
		instrAdd = add;
	}
	
	public String getPc(){
		//get instruction address
		return instrAdd;
	}
	
	public void pcAdder(String index){
		//calculator for next instruction address		
	}
}
