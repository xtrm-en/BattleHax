package me.xtrm.battlehax.utils;

public class TimeHelper {
	
	private long lastMS = 0L;
	
	public void setLastMS(long lastMS){ this.lastMS = System.currentTimeMillis(); }
	public long getLast() { return getCurrentMS() - this.lastMS; }
	public boolean hasReached(long milliseconds){ return getLast() >= milliseconds; }
	public void reset(){ reset(0); }
	public void reset(long offset){ lastMS = getCurrentMS() - offset; }
	
	public static long getCurrentMS(){ return System.nanoTime() / 1000000L; }
	public static int convertToMS(int perSecond){ return 1000/perSecond; }
}
