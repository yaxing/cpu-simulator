/*
 * Outregs.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.outerregs;

import simulator.formatstr.*;
import simulator.interfaces.TraceINF;
/** 
 * Class Outregs
 * control all out registers
 *                          
 * @author Yaxing Chen, Lei Li
 * @version 10-15-2010
 * @see simulator.outerregs
 * @since JDK 1.6
 */
public class OutRegs {
	private static String format = "000000000000000000000000";
	
	/**Register MAR, MBR, MCR*/
	private static Formatstr mar = new Formatstr(format);
	private static Formatstr mbr = new Formatstr(format);
	private static Formatstr mcr = new Formatstr("0");
	
	/**Instruction Register*/
	private static Formatstr ir = new Formatstr(format);
	
	/**MSR*/
	private static Formatstr msr  = new Formatstr(format);
	
	/**MFR*/
	private static Formatstr mfr = new Formatstr("0000");
	
	/**Registers that store OPCode & OPD*/
	private static Formatstr opcode = new Formatstr(format);
	private static Formatstr opd = new Formatstr(format);
	
	/**Register that store I-bit*/
	private static Formatstr iBit = new Formatstr("0");
	
	/**Registers that store the # of general registers*/
	private static Formatstr rop1 = new Formatstr(format);
	private static Formatstr rop2 = new Formatstr(format);
	
	/**Registers that store Operands*/
	private static Formatstr in1 = new Formatstr(format);
	private static Formatstr in2 = new Formatstr(format);
	
	/**Condition code*/
	private static Formatstr cc = new Formatstr("0000");
	
	/**ALU result*/
	private static Formatstr out = new Formatstr(format);
	
	/**Registers that store Shift/Rotate Operation*/
	private static Formatstr lr = new Formatstr(format);
	private static Formatstr ar = new Formatstr(format);
	
	/**Register that store device ID for I/O instruction*/
	private static Formatstr devid = new Formatstr(format);
	
	/**
	 * Guarantee that general register is 24 bits
	 * 
	 * @param Formatstr gr-formatstr need to be formated
	 * 		  String grName-gr name
	 * @return 
	 * @exception
	 */
	private void formatStr(Formatstr gr, String grName){
		String temp = gr.getStr();
		
		if(gr.getStr() == null){
			return;
		}
		
		/*format mfr, cc*/
		if(grName.equals("mfr")||grName.equals("cc")){
			if(temp.length() < 4){
				gr.setStr(format.substring(0,4 - gr.getStr().length()) + temp);
			}
			else if (temp.length() > 4){
				
			}
		}
		
		/*format mcr, i*/
		else if(grName.equals("mcr")||grName.equals("iBit")){
			if(temp.length() == 0){
				gr.setStr("0");
			}
			else if (temp.length() > 1){
				
			}
		}
		
		/*format other registers*/
		else{
			if(temp.length() < 24){
				gr.setStr(format.substring(0,24 - gr.getStr().length()) + temp);
			}
			else if (temp.length() > 24){
				
			}
		}
		return;
	}
	
	
	/*set methods*/
	public void setOPCODE(Formatstr opcodeNew) {
		TraceINF.write("Set opcode.");
		formatStr(opcodeNew, "opcode");
		opcode.setStr(opcodeNew.getStr());
	}
	
	public void setIBIT(Formatstr iBitNew) {
		TraceINF.write("Set iBit.");
		formatStr(iBitNew, "iBit");
		iBit.setStr(iBitNew.getStr());
	}

	public void setROP1(Formatstr rop1New) {
		TraceINF.write("Set ROP1");
		formatStr(rop1New, "rop1");
		rop1.setStr(rop1New.getStr());
	}
	
	public void setROP2(Formatstr rop2New) {
		TraceINF.write("Set ROP2");
		formatStr(rop2New, "rop2");
		rop2.setStr(rop2New.getStr());
	}
	
	public void setIN1(Formatstr in1New) {
		TraceINF.write("Set IN1");
		formatStr(in1New, "in1");
		in1.setStr(in1New.getStr());
	}
	
	public void setIN2(Formatstr in2New) {
		TraceINF.write("Set IN2");
		formatStr(in2New, "in2");
		in2.setStr(in2New.getStr());
	}

	public void setOUT(Formatstr outNew) {
		TraceINF.write("Set OUT.");
		formatStr(outNew, "out");
		out.setStr(outNew.getStr());
	}

	public void setLR(Formatstr lrNew) {
		TraceINF.write("Set LR.");
		formatStr(lrNew, "lr");
		lr.setStr(lrNew.getStr());
	}

	public void setDEVID(Formatstr devidNew) {
		TraceINF.write("Set DEVID.");
		formatStr(devidNew, "devid");
		devid.setStr(devidNew.getStr());
	}

	public void setAR(Formatstr arNew) {
		TraceINF.write("Set AR.");
		formatStr(arNew, "ar");
		ar.setStr(arNew.getStr());
	}

	public void setOPD(Formatstr opdNew){
		TraceINF.write("Set OPD.");
		formatStr(opdNew, "opd");
		opd.setStr(opdNew.getStr());
	}
	
	public void setMAR(Formatstr marNew){
		TraceINF.write("Set MAR.");
		formatStr(marNew, "mar");
		mar.setStr(marNew.getStr());
	}
	
	public void setMBR(Formatstr mbrNew){
		TraceINF.write("Set MBR.");
		formatStr(mbrNew, "mbr");
		mbr.setStr(mbrNew.getStr());
	}
	
	public void setMCR(Formatstr mcrNew){
		TraceINF.write("Set MCR.");
		formatStr(mcrNew, "mcr");
		mcr.setStr(mcrNew.getStr());
	}
	
	public void setMFR(Formatstr mfrNew){
		TraceINF.write("Set MFR.");
		formatStr(mfrNew, "mfr");
		mfr.setStr(mfrNew.getStr());
	}
	
	public void setIR(Formatstr irNew){
		TraceINF.write("Set IR.");
		formatStr(irNew, "ir");
		ir.setStr(irNew.getStr());
	}
	
	public void setMSR(Formatstr msrNew){
		TraceINF.write("Set MSR.");
		formatStr(msrNew, "msr");
		msr.setStr(msrNew.getStr());
	}
	
	public void setCC(int pos, int i){
		TraceINF.write("Set CC bits " + i + ".");
		int[] temp = new int[4];
		String buffer = "";
		for(int j = 0; j < 4; j++){
			temp[j] = (int)cc.getStr().charAt(j) - 0x30;
		}
		temp[pos - 1] = i;
		for(int j = 0; j < 4; j++){
			buffer += String.valueOf(temp[j]);
		}
		cc.setStr(buffer);
	} 

	/*get methods*/
	public Formatstr getOPCODE() {
		TraceINF.write("Get OPCODE.");
		return opcode;
	}
	
	public Formatstr getIBIT() {
		TraceINF.write("Get IBit.");
		return iBit;
	}

	public Formatstr getROP1() {
		TraceINF.write("Get ROP1.");
		return rop1;
	}

	public Formatstr getROP2() {
		TraceINF.write("Get ROP2.");
		return rop2;
	}

	public Formatstr getIN1() {
		TraceINF.write("Get IN1.");
		return in1;
	}

	public Formatstr getIN2() {
		TraceINF.write("Get IN2.");
		return in2;
	}
	
	public Formatstr getOUT() {
		TraceINF.write("Get OUT.");
		return out;
	}

	public Formatstr getLR() {
		TraceINF.write("Get LR.");
		return lr;
	}

	public Formatstr getAR() {
		TraceINF.write("Get AR.");
		return ar;
	}
	
	public Formatstr getDEVID() {
		TraceINF.write("Get DEVID.");
		return devid;
	}
	
	public Formatstr getOPD(){
		TraceINF.write("Get OPD.");
		return opd;
	}
	
	public Formatstr getMAR(){
		TraceINF.write("Get MAR.");
		return mar;
	}
	
	public Formatstr getMBR(){
		TraceINF.write("Get MBR.");
		return mbr;
	}
	
	public Formatstr getMCR(){
		TraceINF.write("Get MCR.");
		return mcr;
	}
	
	public Formatstr getIR(){
		TraceINF.write("Get IR.");
		return ir;
	}
	
	public Formatstr getMSR(){
		TraceINF.write("Get MSR.");
		return msr;
	}
	
	public Formatstr getMFR(){
		TraceINF.write("Get MFR.");
		return mfr;
	}
	
	public Formatstr getCC(){
		TraceINF.write("Get CC.");
		return cc;
	}
}
