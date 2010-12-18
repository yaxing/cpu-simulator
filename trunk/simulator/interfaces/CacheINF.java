/*
 * CacheINF.java
 * Created on 11-16-2010
 * By Yichao Yu
 */

package simulator.interfaces;

import simulator.cache.Cache;
import simulator.formatstr.Formatstr;

public class CacheINF {
	public static void readCacheINF(Formatstr add) {
		Cache.readCache(add);
	}
	
	public static void writeCacheINF(Formatstr add) {
		Cache.writeCache(add);
	}
}
