/*
 * OutregsINF.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */

package simulator.interfaces;
import simulator.outerregs.*;
import simulator.formatstr.*;

/** 
 * Class Outregs
 * interface layer of Outregs class
 * other modules call Outregs functions through this class
 *                          
 * @author Yaxing Chen, Lei Li
 * @version 10-1-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class OutregsINF {
	private static OutRegs or = new OutRegs();
	
	public static void setOPD(Formatstr opd){
		or.setOPD(opd);
	}

	public static void setMAR(Formatstr mar){	//must be 14 bits.
		or.setMAR(mar);
	}
	
	public static void setMBR(Formatstr mbr){
		or.setMBR(mbr);
	}
	
	public static void setMCR(Formatstr mcr){
		or.setMCR(mcr);
	}
	
	public static void setIR(Formatstr ir){
		or.setIR(ir);
	}
	
	public static void setMSR(Formatstr msr){
		or.setMSR(msr);
	}
	
	public static void setOPCODE(Formatstr opcode) {
		or.setOPCODE(opcode);
	}
	
	public static void setIBIT(Formatstr iBit) {
		or.setIBIT(iBit);
	}
	
	public static void setROP1(Formatstr rop1) {
		or.setROP1(rop1);
	}
	
	public static void setROP2(Formatstr rop2) {
		or.setROP2(rop2);
	}
	
	public static void setIN1(Formatstr in1) {
		or.setIN1(in1);
	}

	public static void setIN2(Formatstr in2) {
		or.setIN2(in2);
	}
	
	public static void setOUT(Formatstr out){
		or.setOUT(out);
	}
	
	public static void setOUT1(Formatstr out1){
		or.setOUT1(out1);
	}
	
	public static void setOUT2(Formatstr out2){
		or.setOUT2(out2);
	}
	
	public static void setLR(Formatstr lr) {
		or.setLR(lr);
	}
	
	public static void setAL(Formatstr al) {
		or.setAL(al);
	}
	
	
	public static void setAR(Formatstr ar) {
		or.setLR(ar);
	}
	
	public static void setDEVID(Formatstr devid){
		or.setDEVID(devid);
	}
	
	public static void setCC(int pos, int i){
		or.setCC(pos, i);
	}
	
	public static void setCAP(Formatstr cap){
		or.setCAP(cap);
	}
	
	public static Formatstr getOPD(){
		return or.getOPD();
	}
	
	public static Formatstr getMBR(){
		return or.getMBR();
	}
	
	public static Formatstr getMAR(){
		return or.getMAR();
	}
	
	public static Formatstr getMCR(){
		return or.getMCR();
	}
	
	public static Formatstr getMFR(){
		return or.getMFR();
	}
	
	public static Formatstr getIR(){
		return or.getIR();
	}
	
	public static Formatstr getMSR(){
		return or.getMSR();
	}
	
	public static Formatstr getOPCODE() {
		return or.getOPCODE();
	}

	public static Formatstr getIBIT() {
		return or.getIBIT();
	}

	public static Formatstr getROP1() {
		return or.getROP1();
	}

	public static Formatstr getROP2() {
		return or.getROP2();
	}

	public static Formatstr getIN1() {
		return or.getIN1();
	}

	
	public static Formatstr getIN2() {
		return or.getIN2();
	}
	
	public static Formatstr getOUT(){
		return or.getOUT();
	}

	public static Formatstr getLR() {
		return or.getLR();
	}

	public static Formatstr getAR() {
		return or.getAR();
	}
	
	public static Formatstr getAL() {
		return or.getAL();
	}
	
	public static Formatstr getDEVID() {
		return or.getDEVID();
	}
	
	public static Formatstr getCC() {
		return or.getCC();
	}
	
	public static Formatstr getCAP() {
		return or.getCAP();
	}
}
