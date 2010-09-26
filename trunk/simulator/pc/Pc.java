package simulator.pc;

public class Pc {
	private String instrAdd;//address of next instruction
	
	public void setPc(String add){
		//set instruction address
		instrAdd = add;
	}
	
	public String getPc(){
		//get instruction address
		return instrAdd;
	}
	
	public void pcAdder(String index){
		//calculator for next instruction address		
	}
}
