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
	static Alu ALU = new Alu();
	static Rom ROM = new Rom();
	static Genregs GR = new Genregs();
	static Insdecode DECODE = new Insdecode();
	static Memory MEMORY = new Memory();
	static Outregs OUTERREGS = new Outregs();
	static Formatstr STR = new Formatstr();
	static Pc PC = new Pc();
	
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
	public void setStr(String str){
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
	public String getStr(){
		return formatStr.getStr();
	}
	
	/**
	 * transfer content in formatStr from hex to binary
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public String toBinary(){
		return formatStr.toBinary();
	}
	
	/**
	 * transfer content in formatStr from binary to hex
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public String toHex(){
		return formatStr.toHex();
	}
	
	/**
	 * fetch instructions from file to ROM
	 * 
	 * @param
	 * @return whether load success or not
	 * @exception file exception.
	 */
	public boolean loadToRom(){
		return ROM.loadToRom();
	}
	
	public String loadToMem(){
		return "";
	}
	
	public void setPc(String add){
		PC.setPc(add);
		return;
	}
	
	public String getPc(){
		return PC.getPc();
	}
	
	public void pcAdder(String index){
		PC.pcAdder(index);
	}
}
