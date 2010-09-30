/*
 * Decode.java
 * Created on 09-27-2010
 * By Lei Li
 */

package simulator.irdecode;

/** 
 * Class Decode
 * Decode the instruction in the IS register, 
 * and put each part into different register.
 *                          
 * @author Lei Li
 * @version 09-30-2010
 * @see simulator.isdecode
 * @since JDK 1.6
 */

public class Irdecode {
	
	/**Store the instruction*/
	private String is;
	
	/**Store the opcode of a instruction*/
	private String opcode;
	
	/**Store the OPD of a instruction, if there exists*/
	private String OPD;
	
	/**Store the I-bit of a instruction, if there exists*/
	private String iBit;
	
	/**Store the general register # of a instruction, if there exists*/
	private String rop1;
	private String rop2;
	
	/**Store the immediate number of a instruction, if there exists*/
	private String imNum;
	
	/**Store the flags for Shift/Rotate instructions*/
	private String lr;
	private String ar;
	
	/**
	 * Read the instruction
	 * 
	 * @param is	instruction need to decode.
	 * @return 
	 * @exception
	 */
	public void setIs(String is) {
		this.is = is;
	}
	
	/**
	 * decode
	 */
	
	

}
