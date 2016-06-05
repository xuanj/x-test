package x.log4j.init;

import org.apache.log4j.Logger;


public class Log4jInit {
	
	public static void main(String[] args) {
		Logger LOG = Logger.getLogger(Log4jInit.class);
		LOG.debug("log");
	}
}
