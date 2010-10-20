/*
 * alu.java
 * Created on 09-30-2010
 * By Tianfang Guo
 */
package simulator.alu;
import simulator.formatstr.*;
import simulator.interfaces.*;

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
		String out;
		in1.toBinary();
		in2.toBinary();
		
		out = Integer.toBinaryString(Integer.valueOf(in1.getStr(), 2) & Integer.valueOf(in2.getStr(), 2));
		OutregsINF.setOUT(new Formatstr(out));
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
		String out;
		in1.toBinary();
		in2.toBinary();
		
		out = Integer.toBinaryString(Integer.valueOf(in1.getStr(), 2) | Integer.valueOf(in2.getStr(), 2));
		OutregsINF.setOUT(new Formatstr(out));
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
		String out;
		in1.toBinary();
		
		out = Integer.toBinaryString(~Integer.valueOf(in1.getStr(), 2));
		OutregsINF.setOUT(new Formatstr(out));
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

		in1.binFormat();
		in2.binFormat();
				
		String op1 = in1.getStr();
		String op2 = in2.getStr();
		String sum = new String();

		for (int i=23; i>=0; i--)
		{
			ai = (int)op1.charAt(i) - 0x30;
			bi = (int)op2.charAt(i) - 0x30;
			
			
			//si = ai XOR bi XOR ci
			sum = Integer.toBinaryString(ai ^ bi ^ this.cf) + sum;
			
			//ci+1 = aibi + aici + bici
			this.cf = (ai & bi) | (ai & this.cf) | (bi & this.cf);
		}
		out.setStr(sum);
		OutregsINF.setOUT(out);
		
		//set overflow flag
		if (sum.charAt(0) == 0x30 && sum.charAt(1) == 0x31)
			OutregsINF.setCC(1, 1);
		else
			OutregsINF.setCC(1, 0);
		
		//set underflow flag
		if (sum.charAt(0) == 0x31 && sum.charAt(1) == 0x30)
			OutregsINF.setCC(2, 1);
		else
			OutregsINF.setCC(2, 0);
		
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
		OutregsINF.setIN2(in2);
				
		//set c0=1 to implement complemental code calculation
		this.cf = 1;
		
		//forward to adder
		this.add();
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
	public void shift()
	{
		Formatstr in1 = OutregsINF.getIN1();
		Formatstr in2 = OutregsINF.getIN2();
		Formatstr out;
		Formatstr al = OutregsINF.getAR();
		al.toHex();
		
		//Logical shift or Arithmetical shift, duplicate new bits with 0/1
		String dup = Integer.valueOf(al.getStr(), 16) == 0 ? "1" : "0";		
		
		in1.toBinary();
		in2.toHex();
		
		//shift times
		int n = Integer.valueOf(in2.getStr(), 16);

		//target register
		String dst = in1.getStr();
		String result = new String("");
		
		if (n<24)
		{
			//cut the remaining part of register
			result = OutregsINF.getLR().getStr() == "000001" ? dst.substring(n, dst.length()-1) : dst.substring(0, dst.length()-n-1);
		}
		
		while(result.length()<24)
			result = result + dup;
		
		out = new Formatstr(result);
		OutregsINF.setOUT(out);
		
		//set overflow flag
		/*
		if (cf == '1')
			OutregsINF.setCC(1, 1);
		else
			OutregsINF.setCC(1, 0);
		*/
		
		return;
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
		cCode.toHex();
		return cCode;
	}
	
	/**
	 * The trigger of activating all ALU functions
	 * provide ALU operations in instruction execution
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  	
	 */
	public void calc()
	{
		this.cf = 0;
		Formatstr OP = OutregsINF.getOPCODE();
		OP.toBinary();
		switch (Integer.valueOf(OP.getStr(), 2)){
		//ADD r1,r2
		case 4:
			this.add();
			break;
		//SUB r1,r2
		case 5:
			this.sub();
			break;
		//JZ r, x, address[,I]
		case 8:
			this.test();
			break;
		//TST r, address[,I]
		case 17:
			this.test();
			break;
		//AND r, address[,I]
		case 18:
			this.and();
			break;
		//OR r, address[.I]
		case 19:
			this.or();
			break;
		//NOT r, address[,I]
		case 20:
			this.not();
			break;
		//SRC r, count, L/R, A/L
		case 21:
			this.shift();
			break;
		default:
			break;
		
		}
	}
	
   public static void main(String args[])
   {
	   Formatstr in1 = new Formatstr("110");
	   Formatstr in2 = new Formatstr("110");
	   Formatstr op = new Formatstr("4");
	   Formatstr out = new Formatstr();
	   Formatstr lr = new Formatstr("000001");
	   
	   
	   OutregsINF.setIN1(in1);
	   OutregsINF.setIN2(in2);
	   OutregsINF.setOPCODE(op);
	   AluINF.calc();
	   out = OutregsINF.getOUT();
	   
	   System.out.println(out.getStr());
	   
	   
	   //OutregsINF.setCC(1, 1);
	   
	   //Formatstr c = OutregsINF.getCC();
	   //System.out.println(c.getStr());
   }

	
}
