package com.dubbo.provider.front.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.ai.woego.busi.email.EmailInfo;
import com.ai.woego.busi.email.EmailSendCommonFacade;
import com.ai.woego.busi.email.IEmailSendFacade;
import com.dubbo.provider.front.DemoService;
import com.dubbo.provider.util.CommonMethods;


public class DemoServiceImpl implements DemoService{
	@Override
	public void sayHello() {
		System.out.println("hello zookeeper!");
		System.out.println("=====邮件功能测试开始========");
		try {
            // 邮件发送门面类
            IEmailSendFacade emailSendFacade = new EmailSendCommonFacade();

            // 测试数据
            EmailInfo emailInfo = new EmailInfo();
            emailInfo.setTemplateId("002");
            Map<Object,Object> map = new HashMap<Object, Object>();
            map.put("name", "xuanjian");
            emailInfo.setParameters(map);
            // 发送
            emailSendFacade.send(emailInfo);
            
//            emailSendFacade = new EmailSendImportantFacade();
//            emailInfo.setTemplateId("003");
//            emailSendFacade.send(emailInfo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		System.out.println("=====邮件功能测试结束========");
	}

	@Override
	public String retHello() {
		return "Dubbo服务主机："+CommonMethods.getCurrentHostIp() + "。Dubbo Sever Return Msg:Hello Too!";
	}
}