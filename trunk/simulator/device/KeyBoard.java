/*
 * KeyBoard.java
 * Created on 10-16-2010
 * By Yichao Yu
 */

package simulator.device;

/** 
 * Class KeyBoard
 * Keyboard device. Extends from Devices.
 *                          
 * @author Yichao Yu
 * @version 10-16-2010
 * @see simulator.device
 * @since JDK 1.6
 */
public class KeyBoard extends Devices {
	private static String keyBuffer = new String("");
	//IN instruction method, called by controller
	public static void in() {}
	/**
	 * Default constructor
	*/
	public KeyBoard() {}
	
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
	}
	
	
}
