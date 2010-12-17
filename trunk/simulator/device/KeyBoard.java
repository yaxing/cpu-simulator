/*
 * KeyBoard.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

/** 
 * Class KeyBoard
 * Keyboard device. IN instruction uses this device.
 * The keyboard has its own buffer. If the device use interrupt, it can decrease the 
 * number of interrupt and flush all the buffered input into memory at one time.
 * 
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class KeyBoard {
	private static String keyBuffer = new String("");
	//private static int numInput = 0;
	private static int statusPort;
	/**
	 * get the 24bits of keyBuffer 
	 * 
	 * @param c		the letter or number the user pressed.
	 * @return String	the input the register want.
	 */
	public static String in() {
		String result = new String();
//		int pos = (numInput % 4)*EOI.length();
//		if(!keyBuffer.substring(pos, pos+6).equals(EOI)) {
//			result = keyBuffer.substring(0, 24);
//		}
//		else {
			result = keyBuffer;
			setStatus(2);
//		}
//		keyBuffer = keyBuffer.substring(24);
				
		return result;
	}
	/**
	 * Default constructor
	*/
	public KeyBoard() {}
	
		
	/**
	 * encode the keyboard input letter to binary code.
	 * And buffer the binary code in keyBuffer
	 * 
	 * @param c		the letter or number the user pressed.
	 */
	public static void encode(char c) {
		//System.out.println(keyBuffer);
		String code = new String();
		switch(c) {
		case '0': code = "0110000";
			break;
		case '1': code = "0110001";
			break;
		case '2': code = "0110010";
			break;
		case '3': code = "0110011";
			break;
		case '4': code = "0110100";
			break;
		case '5': code = "0110101";
			break;
		case '6': code = "0110110";
			break;
		case '7': code = "0110111";
			break;
		case '8': code = "0111000";
			break;
		case '9': code = "0111001";
			break;
		case ' ': code = "0100000";
			break;
		case 'a': code = "1100001";
			break;
		case 'b': code = "1100010";
			break;
		case 'c': code = "1100011";
			break;
		case 'd': code = "1100100";
			break;
		case 'e': code = "1100101";
			break;
		case 'f': code = "1100110";
			break;
		case 'g': code = "1100111";
			break;
		case 'h': code = "1101000";
			break;
		case 'i': code = "1101001";
			break;
		case 'j': code = "1101010";
			break;
		case 'k': code = "1101011";
			break;
		case 'l': code = "1101100";
			break;
		case 'm': code = "1101101";
			break;
		case 'n': code = "1101110";
			break;
		case 'o': code = "1101111";
			break;
		case 'p': code = "1110000";
			break;
		case 'q': code = "1110001";
			break;
		case 'r': code = "1110010";
			break;
		case 's': code = "1110011";
			break;
		case 't': code = "1110100";
			break;
		case 'u': code = "1110101";
			break;
		case 'v': code = "1110110";
			break;
		case 'w': code = "1110111";
			break;
		case 'x': code = "1111000";
			break;
		case 'y': code = "1111001";
			break;
		case 'z': code = "1111010";
			break;
		case 'A': code = "1000001";
			break;
		case 'B': code = "1000010";
			break;
		case 'C': code = "1000011";
			break;
		case 'D': code = "1000100";
			break;
		case 'E': code = "1000101";
			break;
		case 'F': code = "1000110";
			break;
		case 'G': code = "1000111";
			break;
		case 'H': code = "1001000";
			break;
		case 'I': code = "1001001";
			break;
		case 'J': code = "1001010";
			break;
		case 'K': code = "1001011";
			break;
		case 'L': code = "1001100";
			break;
		case 'M': code = "1001101";
			break;
		case 'N': code = "1001110";
			break;
		case 'O': code = "1001111";
			break;
		case 'P': code = "1010000";
			break;
		case 'Q': code = "1010001";
			break;
		case 'R': code = "1010010";
			break;
		case 'S': code = "1010011";
			break;
		case 'T': code = "1010100";
			break;
		case 'U': code = "1010101";
			break;
		case 'V': code = "1010110";
			break;
		case 'W': code = "1010111";
			break;
		case 'X': code = "1011000";
			break;
		case 'Y': code = "1011001";
			break;
		case 'Z': code = "1011010";
			break;
			
		default: System.out.println("Invalid charactor.");
			break;
		}
		System.out.println("keyboard "+code+" "+statusPort);
		keyBuffer = code;
		//numInput++;
	}
	
	public static void addEndLine() {
		//keyBuffer = keyBuffer.concat(EOI);
		//numInput++;
	}
	
	public static int getStatus() {
		return statusPort;
	}
	
	/**
	 * When press the key, enter "working" status. When press the EOI key, change to
	 * "done" status. When the IN instruction read all keyBuffer, change to "idle" status.
	 * 
	 * @param s		the status will be set.
	 */
	public static void setStatus(int s) {
		statusPort = s;
	}
}
