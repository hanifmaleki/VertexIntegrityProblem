package common;


import org.apache.log4j.Logger;

/**
 * This Class can be used to stop the time. The StopWatch is not precise,
 * it only measures user-time, not processor-time.
 * 
 * @author Martin Kronegger
 *
 */
public class Stopwatch {
	private static Logger logger = Logger.getLogger(Stopwatch.class);
	
	private long startTime = 0;
	private long stopTime = 0;
	
	private boolean running = false;
	
	public Stopwatch() { }
	
	public Stopwatch(long startTime, long stopTime) {
		this.startTime = startTime;
		this.stopTime = stopTime;
	}
	
	/**
	 * starts the StopWatch
	 */
	public void start() {
		this.running = true;
		this.startTime = System.currentTimeMillis();
		
		if (logger.isTraceEnabled())
			logger.trace("StopWatch: start() = " + this.startTime);
	}
	
	/**
	 * stops the StopWatch
	 */
	public void stop() {
		this.stopTime = System.currentTimeMillis();
		
		if (logger.isTraceEnabled())
			logger.trace("StopWatch: stop() = " + this.stopTime);
		
		this.running = false;
	}
	
	/**
	 * clears the StopWatch
	 */
	public void clear() {
		this.startTime = 0;
		this.stopTime = 0;
	}
	
	/**
	 * The elapsed Time in milliseconds.
	 * @return (currentTime - startTime) iff the StopWatch was started with start() but
	 * not stopped so far with stop(). <br>
	 * (stopTime - startTime) otherwise.
	 */
	public long elapsedTime() {
		long elapsedTime;
		
		if (this.running) {
			elapsedTime = System.currentTimeMillis() - this.startTime;
		} else {
			elapsedTime = this.stopTime - this.startTime;
		}
		
		if (logger.isTraceEnabled())
			logger.trace("StopWatch: elapsedTime() = " + elapsedTime);
		
		return elapsedTime;
	}
	
	/**
	 * 
	 * @return The elapsed Time in seconds.
	 */
	public double elapsedTimeInSeconds() {
		return ((double)this.elapsedTime())/1000;
	}
	
	/**
	 * Adds the elapsed time from that Stopwatch to this Stopwatch.
	 * Before calling this method, this Stopwatch has to be stopped, otherwise
	 * nothing will change to both Stopwatches after this operation.
	 * @param that
	 * @return true if that Stopwatch has been added to this Stopwatch, false otherwise
	 */
	public boolean add(Stopwatch that) {
		if (this.running) {
			return false;
		}
		
		this.stopTime += that.elapsedTime();
		return true;
	}
	
	/**
	 * @return the String representation of the StopWatch.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		long elapsedTime = this.elapsedTime();
		
		long days = elapsedTime / ( 1000 * 3600 * 24 );
		long hours = ( elapsedTime / ( 1000 * 3600 )) % 24;
		long min = ( elapsedTime / ( 1000 * 60 )) % 60;
		long sec = ( elapsedTime / 1000) % 60;
		long ms = elapsedTime % 1000;
		
		String daysPostfix = "days";
		if(days == 1) {
			daysPostfix = "day";
		}
		
		String hoursPostfix = "hours";
		if(hours == 1) {
			hoursPostfix = "hour";
		}
		
		if (days > 0) {
			sb.append(days);
			sb.append(daysPostfix);
			sb.append(" ");
			sb.append(hours);
			sb.append(hoursPostfix);
			sb.append(" ");
			sb.append(min);
			sb.append("min ");
			sb.append(sec);
			sb.append("sec ");
			sb.append(ms);
			sb.append("ms");
		} else if (hours > 0) {
			sb.append(hours);
			sb.append(hoursPostfix);
			sb.append(" ");
			sb.append(min);
			sb.append("min ");
			sb.append(sec);
			sb.append("sec ");
			sb.append(ms);
			sb.append("ms");
		} else if (min > 0) {
			sb.append(min);
			sb.append("min ");
			sb.append(sec);
			sb.append("sec ");
			sb.append(ms);
			sb.append("ms");
		} else if (sec > 0) {
			sb.append(sec);
			sb.append("sec ");
			sb.append(ms);
			sb.append("ms");
		} else {
			sb.append(ms);
			sb.append("ms");
		}
		
		String s = sb.toString();
		
		if (logger.isTraceEnabled())
			logger.trace("StopWatch: toString() = " + s);
		
		return s;
	}
}
