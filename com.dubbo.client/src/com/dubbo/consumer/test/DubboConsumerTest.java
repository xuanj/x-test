package com.dubbo.consumer.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.provider.front.DemoService;

public class DubboConsumerTest {

	public static final String CRM_DUBBO_CONSUMER_XML = "applicationConsumer.xml";

	public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { CRM_DUBBO_CONSUMER_XML });
		context.registerShutdownHook();
		context.start();

		String[] names = context.getBeanDefinitionNames();
		System.out.print("=========Beans:");
		for (String string : names)
			System.out.print(string + ",");
		System.out.println("=============");
		DemoService iDemoService = (DemoService)context.getBean("demoService");
		
		iDemoService.sayHello();
		
		iDemoService.sayHello();
		
		System.out.println(iDemoService.retHello());
		
	}

}
