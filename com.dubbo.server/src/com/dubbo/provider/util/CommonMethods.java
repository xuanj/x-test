package com.dubbo.provider.util;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonMethods {
	public static final String module = CommonMethods.class.getName();
	/**
	 * 获取当前主机IP
	 * @return
	 */
	public static String getCurrentHostIp(){
		InetAddress host = null;
		String hostAddress="UnknownHostException";
		try {
			host = InetAddress.getLocalHost();
			hostAddress = host.getHostAddress();
			System.out.println("host address :"+hostAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostAddress;
	}
	/**
	 * 获取当前主机名称
	 * @return
	 */
	public static String getCurrentHostName(){
		InetAddress host = null;
		String hostName="UnknownHostException";
		try {
			host = InetAddress.getLocalHost();
			hostName = host.getHostName();
			System.out.println("host address :"+hostName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}
	/**
	 * 获取当前进程ID
	 * @return
	 */
	public static long getCurrentPID() {
	    String processName =
	        java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
	    return Long.parseLong(processName.split("@")[0]);
	}
	/**
	 * 获取当前进程ID
	 * @return
	 */
	public static String getCurrentProcessName() {
		return	java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		 
	}

}
