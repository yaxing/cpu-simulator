package simulator.rom;
import simulator.interfaces.*;

public class Rom {
	static private String instructions;
	
	public boolean loadToRom(){
		return true;
	}
	
	public int[] loadToMem(){
		return new int[24];
	}
}
