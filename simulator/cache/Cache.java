/*
 * Cache.java
 * Created on 11-16-2010
 * By Yichao Yu
 */

package simulator.cache;

import simulator.controller.Controller;
import simulator.formatstr.Formatstr;
import simulator.interfaces.GrINF;
import simulator.interfaces.MemoryINF;
import simulator.interfaces.OutregsINF;
import simulator.interfaces.TraceINF;

public class Cache {
	public static final int numline = 8;
	public static final int numword = 8;
	private static Formatstr[][] cacheline = new Formatstr[numline][numword];
	private static String[] tags = new String[numline];		//least significant bit
									// is the validity bit.
	
	public Cache() { }
	public static void init() {
		System.out.println("Cache is ok");
		for(int i=0;i<numline;i++) {
			tags[i] = new String("000000");
			for(int j=0;j<numword;j++) {
				cacheline[i][j] = new Formatstr("000000000000000000000000");
			}
		}
	}
	/**
	 * Controller give an address when cache miss occured.
	 * Use the address to locate the memory and fetch the
	 * memory for that whole line. 
	 * 
	 * @param 
	 * @return 			
	 */
	public static void fetchMemory(int p, String t, Formatstr add) {
		Formatstr a = new Formatstr(add.getStr().substring(0,11));
		a.getStr().concat("000");
		for(int i=0;i<numword;i++) {
			OutregsINF.setMAR(a);
			/*set MBR with memory data from address in MAR*/
			OutregsINF.setMCR(new Formatstr("0"));
			MemoryINF.operateMemory();
			cacheline[p][i].setStr(OutregsINF.getMBR().getStr());
			a.setStr((Integer.toBinaryString((Integer.parseInt(a.getStr(),2)+1))));
			while (a.getStr().length() < 14)
				a.setStr("0" + a.getStr());
			return;
			
		}
	}
	
	public static void readCache(Formatstr add) {
		String t = new String(add.getStr().substring(0, 5));
		int p = Integer.parseInt(add.getStr().substring(5, 11), 2);
		int offset = Integer.parseInt(add.getStr().substring(11, 14), 2);
		if(!checkmiss(p, t)) {
//			System.out.println("Cache missed tags "+tags[p]);
			TraceINF.write("Reading cache missed. Fetch from memory:"+add.getStr());
			fetchMemory(p, t, add);
			tags[p]=tags[p].substring(0,5).concat("1");
			TraceINF.write("Fetching finished.");
		}
		TraceINF.write("Read cache line: #"+p);
		OutregsINF.setCAP(cacheline[p][offset]);
	}
	
	public static void writethrough(int p, String t, int o, Formatstr add) {
		//write cache
		System.out.println("cap "+OutregsINF.getCAP().getStr());
		cacheline[p][o].setStr(OutregsINF.getCAP().getStr());
		//write memory
		OutregsINF.setMAR(add);
		OutregsINF.setMBR(OutregsINF.getCAP());
		OutregsINF.setMCR(new Formatstr("1"));
		MemoryINF.operateMemory();
	}
	
	public static void writeCache(Formatstr add) {
		String t = new String(add.getStr().substring(0, 5));
		int p = Integer.parseInt(add.getStr().substring(5, 11), 2);
		int offset = Integer.parseInt(add.getStr().substring(11, 14), 2);
		if(!checkmiss(p, t)) {
//			System.out.println("Cache missed tags "+tags[p]);
			TraceINF.write("Writing cache missed. Fetch from memory:"+add.getStr());
			fetchMemory(p, t, add);
			tags[p]=tags[p].substring(0,5).concat("1");
			TraceINF.write("Fetching finished.");
		}
		TraceINF.write("Write-through cache line: #"+p);
		writethrough(p, t, offset, add);
		TraceINF.write("Write-through cache finished");
	}
	
	public static boolean checkmiss(int p, String t) {
		if(tags[p].substring(5, 6).equals("0"))
			return false;
		
		if(!tags[p].substring(0, 5).equals(t.substring(0, 5)))
			return false;
		else return true;
	}
	
//	public static void main(String[] args){
//		Formatstr s = new Formatstr("010101010101010101010101");
//		Formatstr a = new Formatstr("00000000000001");
//		OutregsINF.setCAP(s);
//		Cache.writeCache(a);
//		
//	}
}
