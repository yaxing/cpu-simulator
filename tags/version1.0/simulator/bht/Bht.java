/*
 * Bht.java
 * Created on 12-10-2010
 * By Yaxing Chen
 */

package simulator.bht;
import simulator.interfaces.*;
import simulator.formatstr.*;

/** 
 * Class Bht
 * Branch prediction history table
 *                          
 * @author Yaxing Chen
 * @version 12-10-2010
 * @see simulator.bht
 * @since JDK 1.6
 */

public class Bht {
	
	/*bht is maintained as a FIFO where bht[1] is the head, bht[0] is the tail*/
	private static Formatstr[][] bht = new Formatstr[2][2];
	private static int[] takeBit = new int[2];
	private static int branchCounter = 0;
	private static String flush = "000000000000000000000000";
	
	/**
	 * initial BHT
	 * 
	 * @param
	 * @return 
	 * @exception 
	 */
	public static void initial(){
		TraceINF.write("Initialize BHT.");
		flush(0);
		flush(1);
		return;
	}
	
	/**
	 * changeCounter
	 * change the value of branch counter when insert or flush the bht.
	 * the counter is used to monitor how many branches are in the table
	 * 
	 * @param int offset: the value that need to be added to the counter, can be negative
	 * @return 
	 * @exception 
	 */
	private static void changeCounter(int offset){
		int buf = branchCounter;
		buf += offset;
		if(buf >=0 && buf <= 2){
			branchCounter = buf;
		}
		TraceINF.write("Change BHT counter to " + branchCounter);
		return;
	}
	
	/**
	 *insertItem
	 * insert new item into BHT to designated position
	 * 
	 * @param int pos: position for new item
	 * @param Formatstr branchPc: new branch pc address
	 * @param Formatstr predictPc: new predicted pc for new item
	 * @return 
	 * @exception 
	 */
	private static void insertItem(int pos, Formatstr branchPc, Formatstr predictPc){
		TraceINF.write("Insert branch into BHT.");
		bht[pos][0].setStr(branchPc.getStr());
		bht[pos][1].setStr(predictPc.getStr());
		//setTakeBit(pos, false);
		changeCounter(1);
	}
	
	/**
	 *flush
	 * flush designated item
	 * 
	 * @param int pos: position
	 * @return 
	 * @exception 
	 */
	private static void flush(int pos){
		TraceINF.write("Flush BHT line.");
		bht[pos][0] = new Formatstr();
		bht[pos][1] = new Formatstr();
		bht[pos][0].setStr(flush);
		bht[pos][1].setStr(flush);
		takeBit[pos] = 0;
		changeCounter(-1);
	}
	
	/**
	 *replace
	 * according to the FIFO rule, replace old item in BHT with new branch
	 * 
	 * @param Formatstr branchPc, Formatstr predictPc: new item
	 * @return boolean: if the replacement success
	 * @exception 
	 */
	private static boolean replace(Formatstr branchPc, Formatstr predictPc){
		TraceINF.write("Replacing BHT line...");
		TraceINF.write("Move FIFO item.");
		bht[1][0].setStr(bht[0][0].getStr());
		bht[1][1].setStr(bht[0][1].getStr());
		setTakeBit(1, takeBit[0]);
		flush(0);
		changeCounter(-1);
		insertItem(0, branchPc, predictPc);
		TraceINF.write("Replacement finished.");
		return true;
	}
	
	/**
	 *chkBht
	 * check whether a branch is already in the BHT
	 * if it does, return its position
	 * 
	 * @param Formatstr branchPc: the address needs to be checked
	 * @return int: position or -1
	 * @exception 
	 */
	public static int chkBht(Formatstr branchPc){
		if(bht[0][0].getStr().equals(branchPc.getStr())){
			TraceINF.write("Search BHT. Branch alreay exists");
			return 0;
		}
		if(bht[1][0].getStr().equals(branchPc.getStr())){
			TraceINF.write("Search BHT. Branch alreay exists");
			return 1;
		}
		TraceINF.write("Search BHT. Branch doesn't exist");
		return -1;
	}
	
	/**
	 *get taken bit
	 * 
	 * @param int pos: position
	 * @return boolean
	 * @exception 
	 */
	public static int getTakeBit(int pos){
		TraceINF.write("Get taken bit.");
		if(pos >= 0 && pos <= 2){
			return takeBit[pos];
		}
		else{
			return -1;
		}
	}
	
	/**
	 *setTakeBit
	 *Set take bit in certain position
	 * 
	 * @param int pos: position 
	 * @param boolean: take bit += offset
	 * @return 
	 * @exception 
	 */
	public static void setTakeBit(int pos, int offset){
		if(offset > 0){
			TraceINF.write("Set taken bit: " + offset + ", prediction missed.");
		}
		if(offset < 0){
			TraceINF.write("Set taken bit: " + offset + ", prediction hit.");
		}
		if(offset == 0){
			TraceINF.write("Set taken bit: " + offset);
		}
		takeBit[pos] += offset;
		if(takeBit[pos] < 0){
			takeBit[pos] = 0;
		}
		if(takeBit[pos] > 2){
			takeBit[pos] = 2;
		}
		return;
	}
	
	/**
	 *Get predicted address
	 * 
	 * @param int pos: position 
	 * @param Formatstr: corresponding predicted address
	 * @return 
	 * @exception 
	 */
	public static Formatstr getPredictedPc(int pos){
		return bht[pos][1];
	}
	
	
	/**
	 * insert
	 * insert new item into BHT
	 * 
	 * @param 
	 * @return 
	 * @exception 
	 */
	public static int insert(Formatstr branchPc, Formatstr predictPc){
		TraceINF.write("Tring to insert new branch to BHT...");
		int pos = -1;
		switch(branchCounter){
		case 0:
			insertItem(1, branchPc, predictPc);
			pos = 1;
			break;
		case 1:
			insertItem(0, branchPc, predictPc);
			pos = 0;
			break;
		case 2:
			replace(branchPc, predictPc);
			pos = 0;
			break;
		default:
			TraceINF.write("BHT fault!");
			break;
		}
		TraceINF.write("BHT modification finished.");
		return pos;
	}
	
	/**
	 * modify predicted PC
	 * change predicted pc value
	 * 
	 * @param int pos: position in BHT
	 * @param Formatstr newPc: new pc
	 * @return boolean: success or not
	 * @exception 
	 */
	public static boolean modiPredictedPc(int pos, Formatstr newPc){
		TraceINF.write("Mofifing predicted PC...");
		if(bht[pos][0].getStr().equals(flush)){
			TraceINF.write("Modification failed.");
			return false;
		}
		else{
			bht[pos][1].setStr(newPc.getStr());
			TraceINF.write("Modification successfully done.");
			return true;
		}
	}

}
