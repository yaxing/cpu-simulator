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
	private static final String EOI = "111111";		//end of input
	private static String keyBuffer = new String("");
	private static int numInput = 0;
	private static int statusPort;
	/**
	 * get the 24bits of keyBuffer 
	 * 
	 * @param c		the letter or number the user pressed.
	 * @return String	the input the register want.
	 */
	public static String in() {
		String result = new String();
		int pos = (numInput % 4)*EOI.length();
		if(!keyBuffer.substring(pos, pos + 6).equals(EOI)) {
			result = keyBuffer.substring(0, 24);
		}
		else {
			result = keyBuffer;
			setStatus(0);
		}
		keyBuffer = keyBuffer.substring(24);
				
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
		System.out.println(keyBuffer);
		String code = new String();
		switch(c) {
		case '0': code = "000000";
			break;
		case '1': code = "000001";
			break;
		case '2': code = "000010";
			break;
		case '3': code = "000011";
			break;
		case '4': code = "000100";
			break;
		case '5': code = "000101";
			break;
		case '6': code = "000110";
			break;
		case '7': code = "000111";
			break;
		case '8': code = "001000";
			break;
		case '9': code = "001001";
			break;
		case ' ': code = "001010";
			break;
		case 'a': code = "001011";
			break;
		case 'b': code = "001100";
			break;
		case 'c': code = "001101";
			break;
		case 'd': code = "001110";
			break;
		case 'e': code = "001111";
			break;
		case 'f': code = "010000";
			break;
		case 'g': code = "010001";
			break;
		case 'h': code = "010010";
			break;
		case 'i': code = "010011";
			break;
		case 'j': code = "010100";
			break;
		case 'k': code = "010101";
			break;
		case 'l': code = "010110";
			break;
		case 'm': code = "010111";
			break;
		case 'n': code = "011000";
			break;
		case 'o': code = "011001";
			break;
		case 'p': code = "011010";
			break;
		case 'q': code = "011011";
			break;
		case 'r': code = "011100";
			break;
		case 's': code = "011101";
			break;
		case 't': code = "011110";
			break;
		case 'u': code = "011111";
			break;
		case 'v': code = "100000";
			break;
		case 'w': code = "100001";
			break;
		case 'x': code = "100010";
			break;
		case 'y': code = "100011";
			break;
		case 'z': code = "100100";
			break;
		default: System.out.println("Invalid charactor.");
			break;
		}
		keyBuffer = keyBuffer.concat(code);
		numInput++;
	}
	
	public static void addEndLine() {
		keyBuffer = keyBuffer.concat(EOI);
		numInput++;
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
