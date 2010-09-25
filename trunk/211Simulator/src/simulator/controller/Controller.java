package simulator.controller;
import simulator.interfaces.*;

public class Controller {
	static private Interface inface = new Interface();
	static private String addBuffer;
	static private String endInstr = "HUT";
	
	Controller(){}
	
	private boolean initial(){
		//initial ISA
		inface.loadToRom();
		addBuffer = inface.loadToMem();
		inface.setPc(addBuffer);
		return true;
	}
	
	private boolean run(){
		while(true){
			addBuffer = inface.getPc();
			//get current instruction's address
			
			
		}
	}
	
	static public void main(String[] args){
		Controller ISA = new Controller();
		ISA.initial();
	}	
}
