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
	
	/**
	 * set input & get output
	 */
	public static void setIn1(Formatstr myIn1)
	{
		alu.setIn1(myIn1);
		return;
	}
	
	public static void setIn2(Formatstr myIn2)
	{
		alu.setIn2(myIn2);
		return;
	}
	
	public static void setCtl(int myCtl)
	{
		alu.setCtl(myCtl);
		return;
	}
	
	public static void setMux(int myMux)
	{
		alu.setMux(myMux);
		return;
	}
	
	public static Formatstr getOutput()
	{
		return alu.getOutput();
	}
	
	public static int getZf()
	{
		return alu.getZf();
	}
	
	public static int getCf()
	{
		return alu.getCf();
	}
	
	public static int getVf()
	{
		return alu.getVf();
	}
	
	public static int getNf()
	{
		return alu.getNf();
	}
	
	

}
