package com.spring.inAction.Bean21;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年12月15日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category 测试类
 * @author xuanjian
 */
public class Test {
    
    private static String SPRINGIDOL_XML = "Spring-idol.xml";
    
    public static void main(String[] args) {
        testJuggler();
//        testPoeticJuggler();
    }
    
    /**
     * 杂技师测试
     * Date: 2014年12月16日 <br>
     * @author xuanjian
     */
    public static void testJuggler(){
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(SPRINGIDOL_XML);
        Performer performer = (Performer)ctx.getBean("duke0");
        System.out.println(performer.hashCode());//两次获取到的是同一个对象？
        Performer performer0 = (Performer)ctx.getBean("duke0");
        System.out.println(performer0.hashCode());
        performer0.perform();
        Performer performer1 = (Performer)ctx.getBean("duke1");
        System.out.println(performer1.hashCode());
        performer1.perform();
    }

    /**
     * 诵诗杂技师测试
     * Date: 2014年12月16日 <br>
     * @author xuanjian
     */
    public static void testPoeticJuggler(){
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(SPRINGIDOL_XML);
        Performer performer = (Performer)ctx.getBean("poeticDuke");
        performer.perform();
    }
}
