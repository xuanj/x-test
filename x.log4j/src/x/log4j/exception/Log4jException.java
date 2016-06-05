package x.log4j.exception;

import org.apache.log4j.Logger;


public class Log4jException {
	
	public static void main(String[] args) {
		Logger LOG = Logger.getLogger(Log4jException.class);
		LOG.debug("log");
		try {
			int[] is = {};
			LOG.debug(is[1]);;
		} catch (Exception e) {
			LOG.debug("xxx", e);
		}
	}

}
