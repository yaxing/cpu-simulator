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
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.outerregs
 * @since JDK 1.6
 */
public class OutRegs {
	private Formatstr opd;
	private Formatstr mar;
	private Formatstr mbr;
	private Formatstr mcr;
	private Formatstr ir;
	private Formatstr msr;
	
	
	public void setOPD(Formatstr opd){ 
		this.opd = opd;
	}
	
	public void setMAR(Formatstr mar){ 
		this.mar = mar;
	}
	
	public void setMBR(Formatstr mbr){ 
		this.mbr = mbr;
	}
	
	public void setMCR(Formatstr mcr){ 
		this.mcr = mcr;
	}
	
	public void setIR(Formatstr ir){ 
		this.ir = ir;
	}
	
	public void setMSR(Formatstr msr){
		this.msr = msr;
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
}
