package com.ai.woego.busi.email;


/**
 * Description: 通用邮件发送门面，如预警类邮件<br>
 * Date: 2015年1月11日 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public class EmailSendCommonFacade extends AbstractEmailSendFacade{

	@Override
	public void send(EmailInfo emailInfo){
		try {
			String templateId = emailInfo.getTemplateId();
			
			setEmailServer(new EmailServer());//注入邮件服务器
			emailServer.addEmailListener(new ConsoleEmailSendListener());
			emailServer.addEmailListener(new DBEmailSendListener());
			
			//模板初始化 启动模板服务
			setEmailTemplateService(new FreemarkerEmailTemplateStringService());//注入模板
			emailTemplateService.init();// 模板引擎初始化
			
			//接收人
			emailInfo.setToList(getToList(""));
			//主题
			String title = getTitle("");
			emailInfo.setTitle(title);
			//内容
			String content = emailTemplateService.getText(templateId, emailInfo.getParameters());
			emailInfo.setContent(content);
			
			emailServer.send(emailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
