/*
 * IsaControl.java
 * Created on 09-30-2010
 * By Yaxing Chen
 */
package simulator.controller;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class IsaControl
 * control execution logic of all instructions 
 *                          
 * @author Yaxing Chen
 * @version 09-30-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class IsaControl {
	public static boolean execLDR(){
		Formatstr buffer = new Formatstr();
		if(OutregsINF.getIBIT().getStr() == "0"){
			buffer = OutregsINF.getROP2();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
		}
		
		else{
			buffer = OutregsINF.getROP2();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
			OutregsINF.setMAR(OutregsINF.getMBR());
			MemoryINF.loadMemory();
		}
		
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		switch(gN){
		case 0:
			GrINF.setR0(OutregsINF.getMBR());
			break;
		case 1:
			GrINF.setR1(OutregsINF.getMBR());
			break;
		case 2:
			GrINF.setR2(OutregsINF.getMBR());
			break;
		case 3:
			GrINF.setR3(OutregsINF.getMBR());
			break;
		default:
			break;
		}
		return true;
	}
}
