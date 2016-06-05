package com.dubbo.provider.daemon;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年11月20日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category dubbo服务启动
 * @author xuanjian
 */
public class DubboDaemon {

	public static final String CRM_DUBBO_PROVIDER_XML = "applicationProvider.xml";

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { CRM_DUBBO_PROVIDER_XML });
		context.registerShutdownHook();
		context.start();

		String[] names = context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names)
			System.out.print(string + ",");
		System.out.println();
		while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(3L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
