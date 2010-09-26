package simulator.formatstr;

public class Formatstr {
	private String str;
	//instructions or data strings can be either binary or hex string
	
	public void setStr(String str){
		//set string
		this.str = str;
		return;
	}
	
	public String getStr(){
		//get string
		return str;
	}
	
	public void toBinary(){
		//transfer hex string to binary string
		int buffer = Integer.valueOf(str.toString(),16);
		this.str = Integer.toBinaryString(buffer).toString();
		return;
	}
	
	public void toHex(){
		//transfer binary string to hex string
		int buffer = Integer.valueOf(str,2);
		this.str = Integer.toHexString(buffer);
		return;
	}
}
