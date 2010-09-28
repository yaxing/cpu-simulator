/*
 * Outregs.java
 * Created on 09-25-2010
 * By Yaxing Chen
 */
package simulator.outerregs;

/** 
 * Class Outregs
 * control all out registers
 *                          
 * @author Yaxing Chen
 * @version 09-26-2010
 * @see simulator.outerregs
 * @since JDK 1.6
 */
public class Outregs {
	private String opd;
	private String mar;
	private String mbr;
	private String mcr;
	private String ir;
	
	
	public void setOPD(String opd){ 
		this.opd = opd;
	}
	
	public void setMAR(String mar){ 
		this.mar = mar;
	}
	
	public void setMBR(String mbr){ 
		this.mbr = mbr;
	}
	
	public void setMCR(String mcr){ 
		this.mcr = mcr;
	}
	
	public void setIR(String ir){ 
		this.ir = ir;
	}
	
	public String getOPD(){
		return this.opd;
	}
	
	public String getMAR(){
		return this.mar;
	}
	
	public String getMBR(){
		return this.mbr;
	}
	
	public String getMCR(){
		return this.mcr;
	}
	
	public String getIR(){
		return this.ir;
	}
}
