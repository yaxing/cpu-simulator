package simulator.formatstr;

public class Formatstr {
	private String str;
	//instructions or data strings can be either binary or hex string
	
	public boolean setStr(String str){
		//set string
		this.str = str;
		return true;
	}
	
	public String getStr(){
		//get string
		return str;
	}
	
	public String toBinary(){
		//transfer hex string to binary string
		int buffer = Integer.valueOf(str.toString(),16);
		return Integer.toBinaryString(buffer).toString();
	}
	
	public String toHex(){
		//transfer binary string to hex string
		int buffer = Integer.valueOf(str,2);
		return Integer.toHexString(buffer);
	}
}
