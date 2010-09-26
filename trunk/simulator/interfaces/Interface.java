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
	
	public boolean loadToRom(){
		/*fetch instructions from file to ROM*/
		return ROM.loadToRom();
	}
	
	public String loadToMem(){
		/*fetch instructions from ROM to MEMORY and return the first address*/
		//return ROM.loadToMem();
		return "";
	}
	
	public void setPc(String add){
		/*set PC*/
		PC.setPc(add);
		return;
	}
	
	public String getPc(){
		/*get address in PC*/
		return PC.getPc();
	}
	
	public void pcAdder(String index){
		/*calculate next instruction's address*/
		PC.pcAdder(index);
	}
}
