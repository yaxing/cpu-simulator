package simulator.controller;
import simulator.reginterface.*;

public class Controller {
	static private RegInterface regInterface = new RegInterface();
	static private int[] pcAdd = new int[24]; //current instruction add
	static private int[] va = new  int[24]; //virtual address for next instruction
	public Controller(){}
	public boolean initial(){
		//ROM give the address of first instruction to PC
		pcAdd[23] = 1;
		
		//fetch add from pc to MAR;
		regInterface.fetchMarAdd();
		
		//get next address and set pc add
		va[23] = 1;
		regInterface.nextAdd(pcAdd, va);
		return true;
	}
	
	//while(true){}
}
