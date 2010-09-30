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
	private Formatstr instrAdd;
	
	/**
	 * Execute push instruction address into PC
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public void setPc(Formatstr add){
		instrAdd = add;
	}
	
	/**
	 * Get PC content
	 * 
	 * @param
	 * @return Formatstr PC content
	 * @exception
	 */
	public Formatstr getPc(){
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
	public void pcAdder(Formatstr offset){
		Integer PcAdd = Integer.parseInt(instrAdd.getStr(),2);
		Integer off = Integer.parseInt(offset.getStr(),2);
		PcAdd = PcAdd + off;
		Formatstr temp = new Formatstr();
		temp.setStr(Integer.toBinaryString(PcAdd));
		temp.formatAddress();
		this.setPc(temp);
	}
}
