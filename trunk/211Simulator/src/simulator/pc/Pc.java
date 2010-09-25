package simulator.pc;
import simulator.interfaces.*;

public class Pc {
	private String instrAdd;//address of next instruction
	
	public boolean setPc(String add){
		//set instruction address
		instrAdd = add;
		return true;
	}
	
	public String getPc(){
		//get instruction address
		return instrAdd;
	}
	
	public void pcAdder(String index){
		//calculator for next instruction address		
	}
}
