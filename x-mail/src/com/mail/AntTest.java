package com.mail;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AntTest {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger root = LogManager.getRootLogger();
		root.debug("ant test.");
	}

}
