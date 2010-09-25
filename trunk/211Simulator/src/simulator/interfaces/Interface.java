package simulator.interfaces;
import simulator.alu.*;
import simulator.genregs.*;
import simulator.insdecode.*;
import simulator.memory.*;
import simulator.outerregs.*;
import simulator.rom.*;
import simulator.instruction.*;
import simulator.pc.*;

public class Interface {
	//Create objects for all module classes
	static Alu ALU = new Alu();
	static Rom ROM = new Rom();
	static GenRegs GR = new GenRegs();
	static InsDecode DECODE = new InsDecode();
	static Memory MEMORY = new Memory();
	static OutRegs OUTERREGS = new OutRegs();
	static Instruction INSTR = new Instruction();
	static Pc PC = new Pc();
	
	public boolean loadToRom(){
		//fetch instructions from file to ROM
		return ROM.loadToRom();
	}
	
	public String loadToMem(){
		//fetch instructions from ROM to MEMORY and return the first address
		return ROM.loadToMem();
	}
	
	public boolean setPc(String add){
		//set PC
		return PC.setPc(add);
	}
	
	public String getPc(){
		//get address in PC
		return PC.getPc();
	}
	
	public void pcAdder(String index){
		//calculate next instruction's address
		PC.pcAdder(index);
	}
}
