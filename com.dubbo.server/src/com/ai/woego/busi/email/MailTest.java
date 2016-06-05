package com.ai.woego.busi.email;

import java.util.HashMap;
import java.util.Map;

public class MailTest {

	public static void main(String[] args) {
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
			
			emailSendFacade = new EmailSendImportantFacade();
			emailInfo.setTemplateId("003");
			emailSendFacade.send(emailInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
