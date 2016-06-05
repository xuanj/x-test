package com.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Title: MVNO-CRM <br>
 * Description:
 * 平常我们new一个实例，这个实例的控制权是我们程序员，而控制反转是指new实例工作不由我们程序员来做而是交给spring容器来做。<br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * 
 * @category Ioc 控制反转测试类
 * @author xuanjian
 */
@Configuration
public class Ioc {

	// @PropertySource("classpath:/config.properties}")
	@Value("#{systemProperties['mongodb.port'] ?: 27017}")
	private String mongodbPort;

	@Value("#{config['mongodb.url'] ?: '127.0.0.1'}")
	private String mongodbUrl;

	public static void main(String[] args) {
		Ioc i = new Ioc();
		i.testSpringIoc();
		System.out.println(i.mongodbUrl);
		// testSetIoc();
		// testStructureIoc();
	}

	/**
	 * Set 注入测试 Date: 2014年12月15日 <br>
	 * 
	 * @author xuanjian
	 */
	public static void testSetIoc() {
		SetIocAction sa = new SetIocAction();
		sa.setSpringDao(new IocDao());// 如果没有这步，会空指针异常，new对象，现在还是自己做，不是Spring容器
		sa.daoAction();
	}

	/**
	 * 构造器 注入测试 Date: 2014年12月15日 <br>
	 * 
	 * @author xuanjian
	 */
	public static void testStructureIoc() {
		StructureIocAction sia = new StructureIocAction(new IocDao());// 构造对象时，将要注入的对象放到构造方法里
		sia.daoAction();
	}

	/**
	 * Spging Ioc测试 Date: 2014年12月15日 <br>
	 * 
	 * @author xuanjian
	 */
	public void testSpringIoc() {
		@SuppressWarnings("resource")
		ApplicationContext context = new FileSystemXmlApplicationContext("config/application.xml");
		// Spring 是使用反射来new对象，或者说，创建Bean
		IocDao dao = (IocDao) context.getBean("IocDaoService");// new对象，不再用自己new，是Spring容器
		dao.ok();
	}
}
