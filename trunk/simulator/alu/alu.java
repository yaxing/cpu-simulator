/*
 * alu.java
 * Created on 09-30-2010
 * By Tianfang Guo
 */
package simulator.alu;
import simulator.formatstr.*;

/** 
 * Class alu
 * control alu
 *                          
 * @author Tianfang Guo
 * @version 09-30-2010
 * @see simulator.alu
 * @since JDK 1.6
 */
public class alu {

	/**
	 * Arithmetic result 
	 */
	private static Formatstr out;
	
	/**
	 * Two operands
	 */
	private static Formatstr in1;
	private static Formatstr in2;	
	
	/**
	 * ALU control operation
	 */
	private static int ctl;
	
	/**
	 * MUX
	 */
	private static int mux;
	
	/**
	 * Zero flag
	 * Carry flag (unsigned int overflow) 
	 * oVerflow flag (signed int overflow)
	 * Negative flag 
	 */
	private static int zf = 0;
	private static int cf = 0;
	private static int vf = 0;
	private static int nf = 0;
	

	/**
	 * set input & get output
	 */
	public void setIn1(Formatstr myIn1)
	{
		this.in1 = myIn1;
		return;
	}
	
	public void setIn2(Formatstr myIn2)
	{
		this.in2 = myIn2;
		return;
	}
	
	public void setCtl(int myCtl)
	{
		this.ctl = myCtl;
		return;
	}
	
	public void setMux(int myMux)
	{
		this.mux = myMux;
		return;
	}
		
	public Formatstr getOutput()
	{
		return this.out;
	}
	
	public int getZf()
	{
		return this.zf;
	}
	
	public int getCf()
	{
		return this.cf;
	}
	
	public int getVf()
	{
		return this.vf;
	}
	
	public int getNf()
	{
		return this.nf;
	}
	
	
	/**
	 * Convert true code into ones-complemental code, in case of the substruction
	 * 转反码，设置初始进位c0为1即可完成补码运算
	 * @param 
	 * 		String tCode: true code, binary
	 * @return 
	 * 		String cCode: ones-complemental code, binary
	 * @exception
	 */
	private Formatstr convertOCCode(Formatstr tCode)
	{
		Formatstr cCode = new Formatstr();
		tCode.toBinary();
		String tstr = tCode.getStr();
		
		int tvalue = Integer.valueOf(tstr,2);
		
		//xor with 0xFFFFFF
		cCode.setStr(Integer.toBinaryString(tvalue ^ 0xFFFFFF));
		
		return cCode;
	}
	
	/**
	 * Add two operands
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void adder()
	{
		//ai, bi
		int ai, bi;
		
		this.in1.toBinary();
		this.in2.toBinary();
		
		String op1 = in1.getStr();
		String op2 = in2.getStr();
		String sum = new String();
		
		for (int i=0; i<24; i++)
		{
			ai = op1.charAt(i) - 0x30;
			bi = op2.charAt(i) - 0x30;
			
			//si = ai XOR bi XOR ci
			sum = Integer.toBinaryString(ai ^ bi ^ this.cf) + sum;
			
			//ci+1 = aibi + aici + bici
			this.cf = ai&bi +ai&this.cf + bi&this.cf;
						
		}
		
		this.out.setStr(sum);
		
		//set negative flag
		if (sum.charAt(0) == 0x31)
			this.nf = 1;
		
		//set zero flag
		if (Integer.valueOf(sum, 2) == 0)
			this.zf = 1;
		
		//set overflow flag
		/********************
		 * check it...
		 */
		if (sum.charAt(0) == 0x31)
			this.vf = 1;
		
		return;
	}
	
	/**
	 * Left shift (SHL dst, n)
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void shifterLeft()
	{
		this.in1.toBinary();
		this.in2.toHex();
		
		//shift times
		int n = Integer.valueOf(this.in2.getStr(), 16);

		//target register
		String dst = this.in1.getStr();
		String result = new String();
		
		if (n<24)
		{
			//cut the remaining part of register
			result = dst.substring(n, dst.length()-1);
		}
		else
			result = "000000000000000000000000";
		
		if (n<=24)
		{
			//carry flag
			this.cf = dst.charAt(n-1) - 0x30;
		}
		else
			this.cf = 0;
		
		while(result.length()<24)
			result = result + "0";
		
		this.out = new Formatstr(result);
		
		return;
	}
	
	/**
	 * right shift (SHR dst, n)
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void shifterRight()
	{
		
		this.in1.toBinary();
		this.in2.toHex();
		
		//shift times
		int n = Integer.valueOf(this.in2.getStr(), 16);
		
		//target register
		String dst = this.in1.getStr();
		String result = new String();
		
		if (n<24)
		{
			//cut the remaining part of register
			result = dst.substring(0, dst.length()-n-1);
		}
		else
			result = "000000000000000000000000";
		
		if (n<=24)
		{
			//carry flag
			this.cf = dst.charAt(dst.length()-n) - 0x30;
		}
		else
			this.cf = 0;
		
		while(result.length()<24)
			result = "0" + result;
		
		this.out = new Formatstr(result);
		
		return;
	}
	
	/**
	 * Sub two operands
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void sub()
	{
		//ones-complemental code
		this.in2 = convertOCCode(this.in2);
		//set c0=1 to implement complemental code calculation
		this.cf = 1;
		
		//forward to adder
		this.adder();
	}
}
