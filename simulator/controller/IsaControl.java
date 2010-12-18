/*
 * IsaControl.java
 * Created on 09-30-2010
 * By Yaxing Chen
 */
package simulator.controller;
import simulator.interfaces.*;
import simulator.fault.Fault;
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
	private static Formatstr buffer;
	
	private static Formatstr curInstrAdd  = new Formatstr();
	
	/**
	 * return the number of register designated by AC bits
	 */
	private static int getAc(){
		TraceINF.write("Getting the  register name designated by AC bits...");
		String grNo = OutregsINF.getROP1().getStr();
		int gN = Integer.parseInt(grNo,2);
		return gN;
	}
	
	/**
	 * return the number of register designated by IX bits
	 */
	private static int getIx(){
		TraceINF.write("Getting the  register name designated by IX bits...");
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
		//String ix = OutregsINF.getROP2().getStr();
		//int gr = Integer.parseInt(ix);
		int gr = getIx();
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
		
		TraceINF.write("Pending IX contents and OPD address...");
		index = index.substring(23,24);
		address = index + address.substring(11,24);
		
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
		TraceINF.write("Directly addressing...");
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
		TraceINF.write("Indirectly addressing...");
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
		TraceINF.write("Generating EA...");
		if(OutregsINF.getIBIT().getStr().equals("0")){
			dirGetEa();
		}
		else{
			inDirGetEa();
		}
	}
	
	/**
	 * Perform branch prediction (manipulate BHT)
	 * BHT is a FIFO
	 * When the branch doesn't exist in BHT, flush one the head item in BHT and insert the branch£¬
	 * if the jump is backward, then predict taken
	 * else predict non-taken
	 *  
	 *  When the branch exits in BHT
	 *  Check the taken bit (means how many times wrong predicted).
	 *  if the bit equals 2, meaning that there are already 2 wrong predicts on this predict PC,
	 *  then modify the predict (if it the destination address was taken address, then change it to non-taken address, vise-versa) 
	 * and clear the taken bit to 0
	 * 
	 * if the bit is less than 2, then no change has to be made, prediction of the branch will follow the current BHT.
	 * @param
	 * @return 
	 * @exception
	 */
	private static void branchPrediction(){
		TraceINF.write("Running branch prediction...");
		Formatstr branchPc = new Formatstr();
		Formatstr predictPc = new Formatstr();
		
		curInstrAdd = PcINF.getFormerPc();
		/*get the branch address and destination EA*/
		branchPc = curInstrAdd;
		predictPc = buffer;
		
		/*check if branch has already existed*/
		int pos = BhtINF.chkBht(branchPc);
		
		/*if not, insert*/
		if(pos < 0){
			int posInsert = BhtINF.insert(branchPc, predictPc);
			
			/*if jumping back then predict as taken
			 * else not taken
			 * */
			if(Integer.parseInt(branchPc.getStr(), 2) > Integer.parseInt(predictPc.getStr(), 2)){
				BhtINF.modiPredictedPc(posInsert, predictPc);
			}
			else{
				BhtINF.modiPredictedPc(posInsert, PcINF.getPc());
			}
			BhtINF.setTakeBit(posInsert, 0);
		}
		
		/*if already existed, */
		else{
			
			int takenBit = BhtINF.getTakeBit(pos);
			/*if non-taken 2 times, change predict address*/
			if(takenBit  == 2){
				Formatstr oldPredict = BhtINF.getPredictedPc(pos);
				if(oldPredict.getStr().equals(predictPc)){
					BhtINF.modiPredictedPc(pos, PcINF.getPc());
				}
				else{
					BhtINF.modiPredictedPc(pos, predictPc);
				}
			}
			
		}
		TraceINF.write("Branch prediction finished.");
	}
	
	/**
	 *Check whether the prediction was right or wrong
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void checkPrediction(Formatstr takenPc){
		
		TraceINF.write("Checking prediction...");
		/*check if the prediction right*/
		int pos = BhtINF.chkBht(curInstrAdd);
		Formatstr oldAdd = BhtINF.getPredictedPc(pos);
		/*if predicted right, taken bit -1*/
		if(takenPc.getStr().equals(oldAdd.getStr())){
			BhtINF.setTakeBit(pos, -1);
		}
		/*else taken bit +1*/
		else{
			BhtINF.setTakeBit(pos, 1);
		}
		TraceINF.write("Check prediction finished.");
	}
	
	/**
	 * Execute instruction LDR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLdr(){		
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		//OutregsINF.setMAR(buffer);
		
		/*set MBR with memory data from address in MAR*/
//		OutregsINF.setMCR(new Formatstr("0"));
//		MemoryINF.operateMemory();
		
		CacheINF.readCacheINF(buffer);
		
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		/*store MBR content into the target register*/
		switch(gN){
		case 0:
			GrINF.setR0(OutregsINF.getCAP());
			break;
		case 1:
			GrINF.setR1(OutregsINF.getCAP());
			break;
		case 2:
			GrINF.setR2(OutregsINF.getCAP());
			break;
		case 3:
			GrINF.setR3(OutregsINF.getCAP());
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
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*generate EA and store in buffer*/
		genEa();
		/*fetch MAR*/
		//OutregsINF.setMAR(buffer);
		
		/*get the target register AC from ROP1*/
		int gN = getAc();
		
		
		/*test code*/
		/*GrINF.setR2(new Formatstr(Integer.toBinaryString(Integer.parseInt(GrINF.getR2().getStr(),2)+1)));
		*/
		
		
		/*set MBR with register content*/
		switch(gN){
		case 0:
			//OutregsINF.setMBR(GrINF.getR0());
			OutregsINF.setCAP(GrINF.getR0());
			break;
		case 1:
			//OutregsINF.setMBR(GrINF.getR1());
			OutregsINF.setCAP(GrINF.getR1());
			break;
		case 2:
			//OutregsINF.setMBR(GrINF.getR2());
			OutregsINF.setCAP(GrINF.getR2());
			break;
		case 3:
			//OutregsINF.setMBR(GrINF.getR3());
			OutregsINF.setCAP(GrINF.getR3());
			break;
		default:
			break;
		}
		
		//write MBR content into memory unit located in MAR address
		//OutregsINF.setMCR(new Formatstr("1"));
		//MemoryINF.operateMemory();
		CacheINF.writeCacheINF(buffer);
	}
	
	/**
	 * Execute instruction LDA
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLda(){
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*generate EA and store in buffer*/
		genEa();
		
		branchPrediction();
		
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
			//PcINF.pcAdder(new Formatstr("00000000000001"));
			PcINF.setPc(buffer);
			checkPrediction(buffer);
		}
		else{
			checkPrediction(PcINF.getPc());
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
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*generate EA and store in buffer*/
		genEa();
		
		branchPrediction();
		
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
			checkPrediction(buffer);
		}
		else{
			checkPrediction(PcINF.getPc());
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
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*generate EA and store in buffer*/
		genEa();
		
		branchPrediction();
		
		/*get pc content*/
		Formatstr tmp = PcINF.getPc();
		
		/*save pc content into designated register*/
		GrINF.setR7(tmp);
		/*return destination address to PC*/
		PcINF.setPc(buffer);
		
		checkPrediction(buffer);
	}
	
	/**
	 * Execute instruction RFS
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execRfs(){
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		/*flush buffer*/
		buffer = new Formatstr();
		
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
	 * Execute instruction IN
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execIn(){
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*get device id*/
		buffer = OutregsINF.getDEVID();
		
		while(DevicesINF.checkStatus() != 2){
			
		}
		
		String input = DevicesINF.fromDevice(buffer);
		
		buffer.setStr(input);
		
		/*get the target register*/
		int gr = Integer.parseInt(OutregsINF.getROP1().getStr(), 2);
		switch(gr){
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
	 * Execute instruction ADD or SUB
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execAddSub(){
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		
		OutregsINF.setIN1(buffer);
		
		switch(r2){
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
		
		OutregsINF.setIN2(buffer);
		
		/*store the output into r1*/
		
		AluINF.calc();
		buffer = OutregsINF.getOUT();
		switch(r1){
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
		
		return;
	}
	
	/**
	 * Execute instruction AIR or SIR
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execAirSir(){
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*get c(ROP1) and c(ROP2) 
		*/
		int r1 = getAc();		
		int r2 = getIx();
		
		/*immediate number is already passed into IN2 by decode module*/
		
		switch(r2){
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
		
		OutregsINF.setIN1(buffer);
		
		/*store the output into r1*/
		
		AluINF.calc();
		buffer = OutregsINF.getOUT();
		switch(r1){
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
		
		return;
	}
	
	/**
	 * Execute instruction MUL
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execMulDiv(){
		/*flush buffer*/
		buffer = new Formatstr();
		
		/*get c(ROP1) and c(ROP2) 
		*/
		int r1 = getAc();		
		int r2 = getIx();
		
		switch(r1){
		case 0:
			buffer = GrINF.getR0();
			break;
		case 2:
			buffer = GrINF.getR2();
			break;
		default:
			OutregsINF.setMFR(new Formatstr("0010"));
			FaultINF.evoke();
			return;
		}
		
		OutregsINF.setIN1(buffer);
		
		if(r2 == r1 + 1 || r2 == 2){
			switch(r2){
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
		}
		
		else{
			OutregsINF.setMFR(new Formatstr("0010"));
			FaultINF.evoke();
			return;
		}
		
		OutregsINF.setIN2(buffer);
		
		AluINF.calc();
		
		Formatstr cc = OutregsINF.getCC();
		//over flow
		if(cc.getStr().charAt(0) == '1'){
			OutregsINF.setMFR(new Formatstr("0011"));
			FaultINF.evoke();
		}
		//under flow
		else if(cc.getStr().charAt(1) == '1'){
			OutregsINF.setMFR(new Formatstr("0100"));
			FaultINF.evoke();
		}
		//divide 0
		else if(cc.getStr().charAt(2) == '1'){
			OutregsINF.setMFR(new Formatstr("0101"));
			FaultINF.evoke();
		}
		
		switch(r1){
		case 0:
			GrINF.setR0(OutregsINF.getOUT1());
			GrINF.setR1(OutregsINF.getOUT2());
			break;
		case 2:
			GrINF.setR2(OutregsINF.getOUT1());
			GrINF.setR3(OutregsINF.getOUT2());
			break;
		default:
			OutregsINF.setMFR(new Formatstr("0010"));
			FaultINF.evoke();
			return;
		}		
	}

	/**
	 * Execute instruction Logic instructions (And, or, not)
	 * 
	 * @param
	 * @return 
	 * @exception
	 */
	public static void execLogic(){
		/*flush buffer*/
		buffer = new Formatstr();
		
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
		
		OutregsINF.setIN1(buffer);
		
		switch(r2){
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
		
		OutregsINF.setIN2(buffer);
		
		/*store the output into r1*/
		
		AluINF.calc();
		buffer = OutregsINF.getOUT();
		switch(r1){
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
		
		return;
	}
}