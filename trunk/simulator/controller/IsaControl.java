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
		/*Formatstr buffer = new Formatstr();
		if(OutregsINF.getI().getStr() == "0"){
			buffer = OutregsINF.getIX();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
		}
		
		else{
			buffer = OutregsINF.getIX();
			OutregsINF.setMAR(buffer);
			MemoryINF.loadMemory();
			OutregsINF.setMAR(OutregsINF.getMBR());
			MemoryINF.loadMemory();
		}
		
		String grNo = OutregsINF.getAC().getStr();
		switch(grNo){
		case "00":
			GrINF.setR0(OutregsINF.getMBR());
			break;
		case "01":
			GrINF.setR1(OutregsINF.getMBR());
			break;
		case "10":
			GrINF.setR2(OutregsINF.getMBR());
			break;
		case "11":
			GrINF.setR3(OutregsINF.getMBR());
			break;
		default:
			break;
		}
		*/	
		
		return true;
	}
}
