package com.ai.woego.busi.email;


/**
 * Description: 邮件发送 控制台监听<br>
 * Date: 2015年1月11日 <br>
 * @category 邮件发送
 * @author xuanjian
 */
public class ConsoleEmailSendListener implements IEmailSendListener {
	public void before(EmailContext emailInfo) {
		System.out.println("开始准备发送邮件:"+emailInfo.getEmailInfo().getTitle());
		System.out.println("接收人：" + emailInfo.getEmailInfo().getToList());
		System.out.println("主题：" + emailInfo.getEmailInfo().getTitle());
		System.out.println("内容：" + emailInfo.getEmailInfo().getContent());
	}

	public void after(EmailContext emailContext) {
		System.out.println("发送完成："+emailContext.getEmailInfo().getTitle());
	}

	public void afterThrowable(EmailContext emailContext) {
		System.out.println("发送异常："+ emailContext.getEmailInfo()+",exception:"+emailContext.getThrowable());
	}

}
