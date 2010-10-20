/*
 * Outregs.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.outerregs;

import simulator.formatstr.*;
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
	public void setOPCODE(Formatstr opcode) {
		formatStr(opcode, "opcode");
		this.opcode.setStr(opcode.getStr());
	}
	
	public void setIBIT(Formatstr iBit) {
		formatStr(iBit, "iBit");
		this.iBit.setStr(iBit.getStr());
	}

	public void setROP1(Formatstr rop1) {
		formatStr(rop1, "rop1");
		this.rop1.setStr(rop1.getStr());
	}
	
	public void setROP2(Formatstr rop2) {
		formatStr(rop2, "rop2");
		this.rop2.setStr(rop2.getStr());
	}
	
	public void setIN1(Formatstr in1) {
		formatStr(in1, "in1");
		this.in1.setStr(in1.getStr());
	}
	
	public void setIN2(Formatstr in2) {
		formatStr(in2, "in2");
		this.in2.setStr(in2.getStr());
	}

	public void setOUT(Formatstr out) {
		formatStr(out, "out");
		this.out.setStr(out.getStr());
	}

	public void setLR(Formatstr lr) {
		formatStr(lr, "lr");
		this.lr.setStr(lr.getStr());
	}

	public void setDEVID(Formatstr devid) {
		formatStr(devid, "devid");
		this.devid.setStr(devid.getStr());
	}

	public void setAR(Formatstr ar) {
		formatStr(ar, "ar");
		this.ar.setStr(ar.getStr());
	}

	public void setOPD(Formatstr opd){
		formatStr(opd, "opd");
		this.opd.setStr(opd.getStr());
	}
	
	public void setMAR(Formatstr mar){
		formatStr(mar, "mar");
		this.mar.setStr(mar.getStr());
	}
	
	public void setMBR(Formatstr mbr){
		formatStr(mbr, "mbr");
		this.mbr.setStr(mbr.getStr());
	}
	
	public void setMCR(Formatstr mcr){
		formatStr(mcr, "mcr");
		this.mcr.setStr(mcr.getStr());
	}
	
	public void setMFR(Formatstr mfr){
		formatStr(mfr, "mfr");
		this.mfr.setStr(mfr.getStr());
	}
	
	public void setIR(Formatstr ir){
		formatStr(ir, "ir");
		this.ir.setStr(ir.getStr());
	}
	
	public void setMSR(Formatstr msr){
		formatStr(msr, "msr");
		this.msr.setStr(msr.getStr());
	}
	
	public void setCC(int pos, int i){
		int[] temp = new int[4];
		String buffer = "";
		for(int j = 0; j < 4; j++){
			temp[j] = (int)this.cc.getStr().charAt(j) - 0x30;
		}
		temp[pos - 1] = i;
		for(int j = 0; j < 4; j++){
			buffer += String.valueOf(temp[j]);
		}
		this.cc.setStr(buffer);
	} 

	/*get methods*/
	public Formatstr getOPCODE() {
		return opcode;
	}
	
	public Formatstr getIBIT() {
		return iBit;
	}

	public Formatstr getROP1() {
		return rop1;
	}

	public Formatstr getROP2() {
		return rop2;
	}

	public Formatstr getIN1() {
		return in1;
	}

	public Formatstr getIN2() {
		return in2;
	}
	
	public Formatstr getOUT() {
		return out;
	}

	public Formatstr getLR() {
		return lr;
	}

	public Formatstr getAR() {
		return ar;
	}
	
	public Formatstr getDEVID() {
		return devid;
	}
	
	public Formatstr getOPD(){
		return this.opd;
	}
	
	public Formatstr getMAR(){
		return this.mar;
	}
	
	public Formatstr getMBR(){
		return this.mbr;
	}
	
	public Formatstr getMCR(){
		return this.mcr;
	}
	
	public Formatstr getIR(){
		return this.ir;
	}
	
	public Formatstr getMSR(){
		return this.msr;
	}
	
	public Formatstr getMFR(){
		return this.mfr;
	}
	
	public Formatstr getCC(){
		return this.cc;
	}
}
