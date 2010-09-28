/*
 * StrINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.formatstr.*;

/** 
 * Class StrINF
 * interface layer of class Formatstr
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class StrINF {
	/**
	 * Instantiate objects for string that need to be used 
	 */
	public static Formatstr fstr = new Formatstr();
	
	/**
	 * fetch instruction or data to formatStr
	 * 
	 * @param String str  instruction or data needed to be fetched
	 * @return 
	 * @exception 
	 */
	public static void setStr(String str){
		fstr.setStr(str);
		return;
	}
	
	/**
	 * get content in formatStr
	 * 
	 * @param
	 * @return String content in formatStr
	 * @exception
	 */
	public static String getStr(){
		return fstr.getStr();
	}
	
	/**
	 * transfer content in formatStr from hex to binary
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void toBinary(){
		fstr.toBinary();
	}
	
	/**
	 * transfer content in formatStr from binary to hex
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void toHex(){
		fstr.toHex();
		return;
	}
}
