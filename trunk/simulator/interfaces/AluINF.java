/*
 * ALUINF.java
 * Created on 10-8-2010
 * By Tianfang Guo
 */

package simulator.interfaces;

import simulator.alu.*;
import simulator.formatstr.Formatstr;

/** 
 * Class ALUINF
 *                          
 * @author Tianfang Guo
 * @version 10-8-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */

public class AluINF {
	private static alu alu = new alu();
	
	public static void calc()
	{
		alu.calc();
		return;
	}
	
}