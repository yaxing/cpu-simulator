/*
 * PcINF.java
 * Created on 12-11-2010
 * By Yaxing Chen
 */
package simulator.interfaces;
import simulator.pc.*;
import simulator.bht.Bht;
import simulator.formatstr.*;

/** 
 * Class BhtINF
 * as the interface layer of Class Bht, 
 * other modules call Bht through this class
 *                          
 * @author Yaxing Chen
 * @version 12-10--2010
 * @see simulator.interfaces
 * @since JDK 1.6
 */
public class BhtINF {
	
	/**
	 * initial BHT
	 * 
	 * @param
	 * @return 
	 * @exception 
	 */
	public static void initial(){
		Bht.initial();
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
		return Bht.chkBht(branchPc);
	}
	
	/**
	 *get taken bit
	 * 
	 * @param int pos: position
	 * @return boolean
	 * @exception 
	 */
	public static int getTakeBit(int pos){
		return Bht.getTakeBit(pos);
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
		Bht.setTakeBit(pos, offset);
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
		return Bht.getPredictedPc(pos);
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
		return Bht.insert(branchPc, predictPc);
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
		return Bht.modiPredictedPc(pos, newPc);
	}
}
