package simulator.outerregs;
import simulator.interfaces.*;

public class OutRegs {
	static private int[] OPD = new int[24];
	
	public boolean setOPD(int[] opd){
		this.OPD = opd;
		return true;
	}
}
