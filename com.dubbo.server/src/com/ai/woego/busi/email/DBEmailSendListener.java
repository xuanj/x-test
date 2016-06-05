package com.ai.woego.busi.email;

/**
 * Description: 邮件发送 数据库监听<br>
 * Date: 2015年1月11日 <br>
 * @category 类别
 * @author xuanjian
 */
public class DBEmailSendListener implements IEmailSendListener {

	public void before(EmailContext emailInfo) {
		System.out.println("发送前，邮件信息入库！");
	}

	public void after(EmailContext emailContext) {
		System.out.println("发送后，邮件信息更新！");
	}

	public void afterThrowable(EmailContext emailContext) {
		System.out.println("发送邮件发生异常,异常信息入库！");
	}


}
