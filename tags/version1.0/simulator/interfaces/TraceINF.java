package simulator.interfaces;

import simulator.trace.Trace;
import java.util.*;

public class TraceINF {
	public static void write(String trace){
		Trace.write(trace);
	}
	
	public static boolean flushTraceBuffer(){
		return Trace.flushTraceBuffer();
	}
	
	public static ArrayList<String> read(){
		return Trace.read();
	}
	
	public static void initialTrace(){
		Trace.initialTrace();
	}
	
	public static void initialTrace(String logFileName){
		Trace.initialTrace(logFileName);
	}
}
