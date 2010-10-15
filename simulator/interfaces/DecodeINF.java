/*
 * DecodeINF.java
 * Created on 10-1-2010
 * By Lei Li
 */

package simulator.interfaces;
import simulator.irdecode.*;

/** 
 * Class DecodeINF
 * as the interface layer of Class DecodeINF, 
 * other modules call Irdecode through this class
 *                          
 * @author Lei Li
 * @version 10-1-2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */

public class DecodeINF {
	private static Irdecode deco = new Irdecode();
	
	public static void decode(){
		deco.decode();
	}

}
