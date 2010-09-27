/*
 * Controller.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.alu.*;
import simulator.genregs.*;
import simulator.insdecode.*;
import simulator.memory.*;
import simulator.outerregs.*;
import simulator.rom.*;
import simulator.formatstr.*;
import simulator.pc.*;


/** 
 * Class Interface
 * Connect all modules. Controller manipulate other modules through interface layer.
 * Interface holds objects of all outer registers.
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.interface
 * @since JDK 1.6
 */
public class Interface {
	/**
	 * Instantiate objects for all outer registers 
	 */
	public static Alu ALU = new Alu();
	public static Rom ROM = new Rom();
	public static Genregs GR = new Genregs();
	public static Insdecode DECODE = new Insdecode();
	public static Memory MEMORY = new Memory();
	public static Outregs OUTERREGS = new Outregs();
	public static Formatstr STR = new Formatstr();
	public static Pc PC = new Pc();
	
	/**
	 * Instantiate objects for format string which is used to buffer store instruction or data
	 */
	static Formatstr formatStr = new Formatstr();
	
	/**
	 * fetch instruction or data to formatStr
	 * 
	 * @param String str  instruction or data needed to be fetched
	 * @return 
	 * @exception 
	 */
	public static void setStr(String str){
		formatStr.setStr(str);
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
		return formatStr.getStr();
	}
	
	/**
	 * transfer content in formatStr from hex to binary
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void toBinary(){
		formatStr.toBinary();
	}
	
	/**
	 * transfer content in formatStr from binary to hex
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void toHex(){
		formatStr.toHex();
	}
	
	/**
	 * fetch instructions from file to ROM
	 * 
	 * @param
	 * @return whether load success or not
	 * @exception file exception.
	 */
	public static boolean loadToRom(){
		return ROM.loadToRom();
	}
	
	public static String loadToMem(){
		return "80330d";
	}
	
	public static String getInstrFromMem(String add){
		return MEMORY.getInstr(add);
	}
	
	public static void setPc(String add){
		PC.setPc(add);
		return;
	}
	
	public static String getPc(){
		return PC.getPc();
	}
	
	public static void pcAdder(String index){
		PC.pcAdder(index);
	}
}
