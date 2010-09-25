package simulator.outerregs;
import simulator.interfaces.*;

public class OurRegs {
	static private int[] OPD = new int[24];
	
	public boolean setOPD(int[] opd){
		this.OPD = opd;
		return true;
	}
}
