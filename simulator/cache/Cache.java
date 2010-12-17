/*
 * Cache.java
 * Created on 11-16-2010
 * By Yichao Yu
 */

package simulator.cache;

import simulator.formatstr.Formatstr;

public class Cache {
	public static final int numline = 8;
	public static final int numword = 8;
	private static Formatstr[][] cacheline = new Formatstr[numline][numword];
	
	public Cache() {
		System.out.println("Cache is ok");
		for(int i=0;i<numline;i++) {
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
	 * @param add	the address ;
	 * @return boolean	whether fetch success.				
	 */
	public static boolean fetchMemory(Formatstr add) {
		return true;
	}
	
	public static boolean readCache(Formatstr add) {
		return true;
	}
	
	
	
	
	
}
