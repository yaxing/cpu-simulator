package simulator.reginterface;
import simulator.memorysystem.*;
import simulator.pc.*;

public class RegInterface {
	public RegInterface(){}
	
	//fetch add from pc to mar
	public boolean fetchMarAdd(){
		Mar mar = new Mar();
		return mar.fetchAdd();
	}
	
	//get next address of instruction
	public int[] nextAdd(int[] ea, int[] va){
		Pc pc = new Pc();
		return pc.nextAdd(ea, va);
	}
}
