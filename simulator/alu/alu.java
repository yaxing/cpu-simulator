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
		//in1.toBinary();
		//in2.toBinary();
		
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
		//in1.toBinary();
		//in2.toBinary();
		
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
		//in1.toBinary();
		
		String tmpStr = "";
		for(int i=0; i<in1.getStr().length(); i++)
			tmpStr = tmpStr + (in1.getStr().charAt(i) == 0x30 ? "1" : "0");
		
		//out = Integer.toBinaryString(~Integer.valueOf(in1.getStr(), 2));
		OutregsINF.setOUT(new Formatstr(tmpStr));
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
	 * Multiply two operands
	 * 
	 *  @param
	 *  
	 *  @return
	 *  @exception
	 *  
	 */
	public void mul()
	{
		String mulIn1 = OutregsINF.getIN1().getStr().substring(1);
		String product = "00000000000000000000000" + OutregsINF.getIN2().getStr().substring(1);
		//get signals
		int sig1 = OutregsINF.getIN1().getStr().charAt(0) - 0x30;
		int sig2 = OutregsINF.getIN2().getStr().charAt(0) - 0x30;
		//final signal = sig1 XOR sig2
		int finalSig = sig1 ^ sig2;
		
		for(int i=0; i<23; i++)
		{
			//right shift multiplication, add IN1 to the top 23 bits of the product 
			if (product.charAt(45) == 0x31)
			{
				OutregsINF.setIN1(new Formatstr(mulIn1));
				OutregsINF.setIN2(new Formatstr(product.substring(0, 23)));
				OutregsINF.setOPCODE(new Formatstr("4"));
				AluINF.calc();
				product = OutregsINF.getOUT().getStr().substring(1) + product.substring(23);
			}
			
			//right shift
			product = "0" + product.substring(0, 45);
		}
		
		OutregsINF.setOUT1("0" + Integer.toString(finalSig) + product.substring(0, 22));
		OutregsINF.setOUT2(product.substring(22));
		
		//System.out.println(Integer.toString(finalSig) + product);
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
		Formatstr al = OutregsINF.getAL();
		Formatstr lr = OutregsINF.getLR();
		in1.binFormat();
		
		//Logical shift or Arithmetical shift, duplicate new bits with 0/1
		String dup = ( Integer.valueOf(lr.getStr()) == 0 && Integer.valueOf(al.getStr()) == 0 ) ? in1.getStr().substring(0, 1) : "0";
		
		
		//in1.toBinary();
		//in2.toHex();
		
		//shift times
		int n = Integer.valueOf(in2.getStr(), 2);

		//target register
		String dst = in1.getStr();
		String result = new String("");
		
		//cut the remaining part of register
		if (n<24)
			result = Integer.valueOf(lr.getStr()) == 1 ? dst.substring(n, dst.length()) : dst.substring(0, dst.length()-n);
		
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
		//tCode.toBinary();
		String tstr = tCode.getStr();
		
		int tvalue = Integer.valueOf(tstr,2);
		//System.out.println(tCode.getStr() + '\n');
		//xor with 0xFFFFFF
		cCode.setStr(Integer.toBinaryString(tvalue ^ 0xFFFFFF));
		//cCode.toHex();
		//System.out.println(cCode.toString());
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
		//OP.toBinary();
		switch (Integer.valueOf(OP.getStr())){
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
		//MUL r1,r2
		//input: In1, In2; output in In1.In2
		//the highest digit indicates the signal, not involved in calculation
		case 15:
			this.mul();
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
	
	/*
   public static void main(String args[])
   {
	   Formatstr in1 = new Formatstr("100000000000000000000011");
	   Formatstr in2 = new Formatstr("000000000000000000000111");
	   Formatstr op = new Formatstr("15");
	   Formatstr out = new Formatstr();
	   Formatstr lr = new Formatstr("1");
	   Formatstr al = new Formatstr("0");
	   
	   //double signal flag works on sub?
	   OutregsINF.setIN1(in1);
	   OutregsINF.setIN2(in2);
	   OutregsINF.setLR(lr);
	   OutregsINF.setAL(al);
	   
	   OutregsINF.setOPCODE(op);
	   AluINF.calc();
	   out = OutregsINF.getOUT();
	   
	   System.out.println(out.getStr() + '\n');
	   System.out.println(OutregsINF.getCC().getStr());
	   
	   
	   //OutregsINF.setCC(1, 1);
	   
	   //Formatstr c = OutregsINF.getCC();
	   //System.out.println(c.getStr());
   }
*/
	
}
