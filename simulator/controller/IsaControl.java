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
 * @version 10-05-2010
 * @see simulator.controller
 * @since JDK 1.6
 */
public class IsaControl {
	/**buffer is used to temporarily store data got from registers*/
	private static Formatstr buffer = new Formatstr();
	
	
	/**
	 * return the number of register designated by AC bits
	 */
	private static int getAc(){
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		return gN;
	}
	
	/**
	 * return the number of register designated by IX bits
	 */
	private static int getIx(){
		String grNo = OutregsINF.getROP2().getStr();
		int gN = Integer.parseInt(grNo,2);
		return gN;
	}
	
	/**
	 * calculate the address
	 * when needed, this function adds IX and Address together
	 * and get the needed address
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void pendIxGr(){
		
		/*get IX from ROP2*/
		String ix = OutregsINF.getROP2().getStr();
		int gr = Integer.parseInt(ix);
		String index = new String("000000000000000000000000");
		/*if IX != 0 then index the address
		 *else no index 
		 */
		if (gr != 0){	
			switch(gr){
			case 1:
				index = GrINF.getR1().getStr();
				break;
			case 2:
				index = GrINF.getR2().getStr();
				break;
			case 3:
				index = GrINF.getR3().getStr();
				break;
			default:
				break;
				
			}
		}
		
		/*get Address from OPD*/
		String address = OutregsINF.getOPD().getStr();
		
		/*add them together and store in buffer as needed address*/
		//Integer ea = Integer.parseInt(ix,2) + Integer.parseInt(address,2);
		
		index = index.substring(23,24);
		address = index + address.substring(12,24);
		
		/*
		 * store the EA into buffer
		 * and format it as a 14 bit address
		 */
		buffer.setStr(address);
	}
	
	/**
	 * Direct address
	 * get EA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void dirGetEa(){
		/*get EA depending on IX and designated general register content*/
		pendIxGr();
	}
	
	/**
	 * Indirect address
	 * get EA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void inDirGetEa(){
		/*get indirect address depending on IX and designated general register content*/
		pendIxGr();
		
		OutregsINF.setMAR(buffer);
		
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		
		/*get EA from MBR*/
		buffer = OutregsINF.getMBR();
	}
	
	/**
	 * Generate EA based on direct or indirect address
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	private static void genEa(){
		/*
		 * judge whether it is direct or indirect address
		 * and then execute corresponding process 
		 * EA will be stored into buffer
		 */
		if(OutregsINF.getIBIT().getStr().equals("0")){
			dirGetEa();
		}
		else{
			inDirGetEa();
		}
	}
	
	/**
	 * Execute instruction LDR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLdr(){
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		OutregsINF.setMAR(buffer);
		
		/*set MBR with memory data from address in MAR*/
		OutregsINF.setMCR(new Formatstr("0"));
		MemoryINF.operateMemory();
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		/*store MBR content into the target register*/
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
	}
	
	/**
	 * Execute instruction STR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execStr(){
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		OutregsINF.setMAR(buffer);
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		
		/*test code*/
		/*GrINF.setR2(new Formatstr(Integer.toBinaryString(Integer.parseInt(GrINF.getR2().getStr(),2)+1)));
		*/
		
		
		/*set MBR with register content*/
		switch(gN){
		case 0:
			OutregsINF.setMBR(GrINF.getR0());
			break;
		case 1:
			OutregsINF.setMBR(GrINF.getR1());
			break;
		case 2:
			OutregsINF.setMBR(GrINF.getR2());
			break;
		case 3:
			OutregsINF.setMBR(GrINF.getR3());
			break;
		default:
			break;
		}
		
		//write MBR content into memory unit located in MAR address
		OutregsINF.setMCR(new Formatstr("1"));
		MemoryINF.operateMemory();
	}
	
	/**
	 * Execute instruction LDA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLda(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		//set general register with address
		switch(gN){
		case 0:
			GrINF.setR0(buffer);
			break;
		case 1:
			GrINF.setR1(buffer);
			break;
		case 2:
			GrINF.setR2(buffer);
			break;
		case 3:
			GrINF.setR3(buffer);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Execute instruction JZ
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execJz(){
		/*generate EA and store in buffer*/
		//genEa();
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		/*get condition from register*/
		String condition = new String();
		switch(gN){
		case 0:
			condition = GrINF.getR0().getStr();
			break;
		case 1:
			condition = GrINF.getR1().getStr();
			break;
		case 2:
			condition = GrINF.getR2().getStr();
			break;
		case 3:
			condition = GrINF.getR3().getStr();
			break;
		default:
			break;
		}
		if(Integer.parseInt(condition) == 0){
			PcINF.pcAdder(new Formatstr("00000000000001"));
		}
	}
	
	/**
	 * Execute instruction JNE
	 * 
	 * @param
	 * @return s
	 * @exception
	 */
	public static void execJne(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		/*get destination address from designated register*/
		String condition = new String();
		switch(gN){
		case 0:
			condition = GrINF.getR0().getStr();
			break;
		case 1:
			condition = GrINF.getR1().getStr();
			break;
		case 2:
			condition = GrINF.getR2().getStr();
			break;
		case 3:
			condition = GrINF.getR3().getStr();
			break;
		default:
			break;
		}
		if(Integer.parseInt(condition) != 0){
			PcINF.setPc(buffer);
		}
	}
	
	/**
	 * Execute instruction JMP
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execJmp(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*return destination address to PC*/
		PcINF.setPc(buffer);
	}
	
	/**
	 * Execute instruction JSR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execJsr(){
		/*generate EA and store in buffer*/
		genEa();
		
		/*get pc content*/
		Formatstr tmp = PcINF.getPc();
		
		/*save pc content into designated register*/
		GrINF.setR7(tmp);
		/*return destination address to PC*/
		PcINF.setPc(buffer);
	}
	
	/**
	 * Execute instruction RFS
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execRfs(){
		/*get return code*/
		buffer = OutregsINF.getOPD();
		
		/*store return code into R0*/
		GrINF.setR0(buffer);
		
		/*set PC = c(R7)*/
		PcINF.setPc(GrINF.getR7());
	}
	
	/**
	 * Execute instruction SOB
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execSob(){
		/*get register content*/
		int gN = getAc();
		
		/*buffer the register content*/
		String con = new String();
		switch(gN){
		case 0:
			con = GrINF.getR0().getStr();
			break;
		case 1:
			con = GrINF.getR1().getStr();
			break;
		case 2:
			con = GrINF.getR2().getStr();
			break;
		case 3:
			con = GrINF.getR3().getStr();
			break;
		default:
			break;
		}
		
		/*if(c(r)!=0)
		 * then r <- c(r) - 1
		 *      PC <- EA or PC <- (EA) if I is set 
		 * else PC<-PC+1
		 */
		int cr = Integer.parseInt(con, 2);
		
		if( cr != 0){
			/*Formatstr a = GrINF.getR3();
			int b = Integer.parseInt(a.getStr(), 2)+1;
			a.setStr(Integer.toBinaryString(b));
			GrINF.setR3(a);*/
			
			cr = cr - 1;
			Formatstr tempC = new Formatstr(Integer.toBinaryString(cr));
			switch(gN){
			case 0:
				GrINF.setR0(tempC);
				break;
			case 1:
				GrINF.setR1(tempC);
				break;
			case 2:
				GrINF.setR2(tempC);
				break;
			case 3:
				GrINF.setR3(tempC);
				break;
			default:
				break;
			}
			
			/*get EA*/
			genEa();
			
			PcINF.setPc(buffer);
		}
		else{
			return;
		}
	}
	
	/**
	 * Execute instruction OUT
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execOut(){
		/*get output content*/
		int gr = Integer.parseInt(OutregsINF.getROP1().getStr(), 2);
		switch(gr){
		case 0:
			buffer = GrINF.getR0();
			break;
		case 1:
			buffer = GrINF.getR1();
			break;
		case 2:
			buffer = GrINF.getR2();
			break;
		case 3:
			buffer = GrINF.getR3();
			break;
		default:
			break;
		}
		DevicesINF.toDevice(OutregsINF.getDEVID(), buffer);
		return;
	}
	
	/**
	 * 
	 */
	public static void execIN(){
		
	}
	
	/**
	 * Execute instruction ADD
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execAdd(){
		
		/*get c(ROP1) and c(ROP2) 
		 * store them in bus buffer to pass into IN1 and IN2
		*/
		int r1 = getAc();		
		int r2 = getIx();
		
		switch(r1){
		case 0:
			buffer = GrINF.getR0();
			break;
		case 1:
			buffer = GrINF.getR0();
			break;
		case 2:
			buffer = GrINF.getR0();
			break;
		case 3:
			buffer = GrINF.getR0();
			break;
		default:
			break;
		}
		
		OutregsINF.setIN1(buffer);
		
		switch(r2){
		case 0:
			buffer = GrINF.getR0();
			break;
		case 1:
			buffer = GrINF.getR0();
			break;
		case 2:
			buffer = GrINF.getR0();
			break;
		case 3:
			buffer = GrINF.getR0();
			break;
		default:
			break;
		}
		
		OutregsINF.setIN1(buffer);
		
		/*store the output into r1*/
		
		
		switch(r1){
		case 0:
			GrINF.setR0(buffer);
			break;
		case 1:
			GrINF.setR0(buffer);
			break;
		case 2:
			GrINF.setR0(buffer);
			break;
		case 3:
			GrINF.setR0(buffer);
			break;
		default:
			break;
		}
		
		return;
	}
}