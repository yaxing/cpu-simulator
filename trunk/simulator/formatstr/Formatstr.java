/*
 * Formatstr.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.formatstr;

/** 
 * Class Formatstr
 * handle and store instruction or data that is on process
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.formatstr
 * @since JDK 1.6
 */
public class Formatstr {
	/**
	 * instructions or data strings can be either binary or hex string
	 */
	private String str;
	
	/**
	 * constructor with String parameter
	 * 
	 * @param	s	String value.
	 * @return 
	 */
	public Formatstr(String s) {
		str = new String(s);
	}
	
	//default constructor
	public Formatstr() {}
	/**
	 * set string
	 * 
	 * @param String str  string needed to be handled
	 * @return 
	 * @exception 
	 */
	public void setStr(String str){
		this.str = str;
		return;
	}
	
	/**
	 * get string
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public String getStr(){
		return str;
	}
	
	/**
	 * transfer hex string to binary string
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public void toBinary(){
		int buffer = Integer.valueOf(str,16);
		this.str = Integer.toBinaryString(buffer);
		return;
	}
	
	/**
	 * transfer binary string to hex string
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public void toHex(){
		int buffer = Integer.valueOf(str,2);
		this.str = Integer.toHexString(buffer);
		return;
	}
	
	/**
	 * format instruction string
	 * to 24 bit binary
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public void formatInstruction(){
		String format = "000000000000000000000000";
		this.str = format.substring(0, format.length() - str.length()) + str;
	}
	
	/**
	 * format address string to 14 bit binary
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public void formatAddress(){
		String format = "0000000000000";
		this.str = format.substring(0, format.length() - str.length()) + str;
	}
}
