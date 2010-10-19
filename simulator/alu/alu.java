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
	
	//carry flag
	private static int cf = 0;
	
	/**
	 * Test the Equality of in1 and in2 
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void test()
	{
		OutregsINF.setCC(4, OutregsINF.getIN1() == OutregsINF.getIN2() ? -1 : 0 );
		return;
	}
	
	/**
	 * Test the Equality of in1 and in2 
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void test()
	{
		OutregsINF.setCC(4, OutregsINF.getIN1() == OutregsINF.getIN2() ? -1 : 0 );
		return;
	}
	
	/**
	 * Logical And of in1 and in2 
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void and()
	{
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
		in1.toBinary();
		in2.toBinary();
		
		OutregsINF.setOUT(Integer.valueOf(in1.getStr(), 2) & Integer.valueOf(in2.getStr(), 2));
		return;
	}
	
	/**
	 * Logical Or of in1 and in2 
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void or()
	{
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
		in1.toBinary();
		in2.toBinary();
		
		OutregsINF.setOUT(Integer.valueOf(in1.getStr(), 2) | Integer.valueOf(in2.getStr(), 2));
		return;
	}
	
	/**
	 * Logical Not of in1 
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void not()
	{
		Formatstr in1 = OutregsINF.getIN1();
		in1.toBinary();
		
		OutregsINF.setOUT(~Integer.valueOf(in1.getStr(), 2));
		return;
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
	public void add()
	{
		//ai, bi
		int ai, bi;
		Formatstr out = new Formatstr();
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
				
		in1.toBinary();
		in2.toBinary();
		
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
		out.setStr(sum);
		OutregsINF.setOUT(out);
		
		//set zero flag (equal or not)
		if (Integer.valueOf(sum, 2) == 0)
			OutregsINF.setCC(4, 1);
		else
			OutregsINF.setCC(4, 0);
		
		//set overflow flag
		if (sum.charAt(0) == 0x31 && this.cf == 0)
			OutregsINF.setCC(1, 1);
		else
			OutregsINF.setCC(1, 0);
		
		//set underflow flag
		if (sum.charAt(0) == 0x30 && this.cf == 1)
			OutregsINF.setCC(2, 1);
		else
			OutregsINF.setCC(2, 0);
		
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
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
		Formatstr out;
		in1.toBinary();
		in2.toHex();
		char cf = '0';
		
		//shift times
		int n = Integer.valueOf(in2.getStr(), 16);

		//target register
		String dst = in1.getStr();
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
			cf = dst.charAt(n-1);
		}
		else
			cf = '0';
		
		while(result.length()<24)
			result = result + "0";
		
		out = new Formatstr(result)
		OutregsINF.setOUT(out);
		
		//set overflow flag
		if (cf == '1')
			OutregsINF.setCC(1, 1);
		else
			OutregsINF.setCC(1, 0);
		
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
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
		Formatstr out;
		in1.toBinary();
		in2.toHex();
		char cf = '0';
		
		//shift times
		int n = Integer.valueOf(in2.getStr(), 16);
		
		//target register
		String dst = in1.getStr();
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
			cf = dst.charAt(dst.length()-n);
		}
		else
			cf = '0';
		
		while(result.length()<24)
			result = "0" + result;
		
		out = new Formatstr(result)
		OutregsINF.setOUT(out);
		
		//set overflow flag
		if (cf == '1')
			OutregsINF.setCC(1, 1);
		else
			OutregsINF.setCC(1, 0);
		
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
		Formatstr in2;
		//ones-complemental code
		in2 = convertOCCode(OutregsINF.getIN2());
		//set c0=1 to implement complemental code calculation
		this.cf = 1;
		
		//forward to adder
		this.adder();
	}
	
	/**
	 * Convert true code into ones-complemental code, in case of the substruction
	 * 
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
	 * Activate caculation
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void calc()
	{
		Formatstr OP = OutregsINF.getOPCODE();
		OP.toBinary();
		switch (Integer.valueOf(OP.getStr(), 2)){
		case 4:
			this.add();
			break;
		case 5:
			this.sub();
			break;
		case 17:
			this.test();
			break;
		case 18:
			this.and();
			break;
		case 19:
			this.or();
			break;
		case 20:
			this.not();
			break;
		case 21:
			Formatstr lr = OutregsINF.getLR();
			lr.toHex();
			if(lr == "000001")
				this.shifterLeft();
			else
				this.shifterRight();
			break;
		default:
			break;
		
		}
	}
	
}
