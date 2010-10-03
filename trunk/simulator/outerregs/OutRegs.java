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
 * @version 10-2-2010
 * @see simulator.outerregs
 * @since JDK 1.6
 */
public class OutRegs {
	/**Register MAR, MBR, MCR*/
	private Formatstr mar;
	private Formatstr mbr;
	private Formatstr mcr;
	
	/**Instruction Register*/
	private Formatstr ir;
	
	/**MSR*/
	private Formatstr msr;
	
	/**MFR*/
	private Formatstr mfr;
	
	/**Registers that store OPCode & OPD*/
	private Formatstr opcode;
	private Formatstr opd;
	
	/**Register that store I-bit*/
	private Formatstr iBit;
	
	/**Registers that store the # of general registers*/
	private Formatstr rop1;
	private Formatstr rop2;
	
	/**Registers that store Operands*/
	private Formatstr in1;
	private Formatstr in2;
	
	/**ALU result*/
	private Formatstr out;
	
	/**Registers that store Shift/Rotate Operation*/
	private Formatstr lr;
	private Formatstr ar;
	
	/**Register that store device ID for I/O instruction*/
	private Formatstr devid;
	
	public Formatstr getOPCODE() {
		return opcode;
	}
	
	public void setOPCODE(Formatstr opcode) {
		this.opcode = opcode;
	}

	public Formatstr getIBIT() {
		return iBit;
	}

	public void setIBIT(Formatstr iBit) {
		this.iBit = iBit;
	}

	public Formatstr getROP1() {
		return rop1;
	}

	public void setROP1(Formatstr rop1) {
		this.rop1 = rop1;
	}

	public Formatstr getROP2() {
		return rop2;
	}

	public void setROP2(Formatstr rop2) {
		this.rop2 = rop2;
	}

	public Formatstr getIN1() {
		return in1;
	}

	public void setIN1(Formatstr in1) {
		this.in1 = in1;
	}

	public Formatstr getIN2() {
		return in2;
	}

	public void setIN2(Formatstr in2) {
		this.in2 = in2;
	}
	
	public Formatstr getOUT() {
		return out;
	}

	public void setOUT(Formatstr out) {
		this.out = out;
	}

	public Formatstr getLR() {
		return lr;
	}

	public void setLR(Formatstr lr) {
		this.lr = lr;
	}

	public Formatstr getAR() {
		return ar;
	}
	
	public Formatstr getDEVID() {
		return devid;
	}

	public void setDEVID(Formatstr devid) {
		this.devid = devid;
	}

	public void setAR(Formatstr ar) {
		this.ar = ar;
	}

	public void setOPD(Formatstr opd){ 
		this.opd = opd;
	}
	
	public Formatstr getOPD(){
		return this.opd;
	}
	
	public void setMAR(Formatstr mar){ 
		this.mar = mar;
	}
	
	public Formatstr getMAR(){
		return this.mar;
	}
	
	public void setMBR(Formatstr mbr){ 
		this.mbr = mbr;
	}
	
	public Formatstr getMBR(){
		return this.mbr;
	}
	
	public void setMCR(Formatstr mcr){ 
		this.mcr = mcr;
	}
	
	public Formatstr getMCR(){
		return this.mcr;
	}
	
	
	public void setIR(Formatstr ir){ 
		this.ir = ir;
	}
	
	public Formatstr getIR(){
		return this.ir;
	}
	
	public void setMSR(Formatstr msr){
		this.msr = msr;
	}
	
	public Formatstr getMSR(){
		return this.msr;
	}
	
	public void setMFR(Formatstr mfr){
		this.mfr = mfr;
	}
	
	public Formatstr getMFR(){
		return this.mfr;
	}
}
