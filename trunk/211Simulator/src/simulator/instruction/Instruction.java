package simulator.instruction;

public class Instruction {
	private static String instr;
	//instruction can be either binary or hex string
	
	public boolean setStr(String instr){
		//set instruction
		instr = instr;
		return true;
	}
	
	public String getStr(){
		//get instruction
		return instr;
	}
	
	public String toBinary(){
		//transfer hex string instruction to binary string
		int buffer = Integer.valueOf(instr.toString(),16);
		return Integer.toBinaryString(buffer).toString();
	}
	
	public String toHex(){
		//transfer binary instruction to hex string
		int buffer = Integer.valueOf(instr,2);
		return Integer.toHexString(buffer);
	}
}
