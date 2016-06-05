package com.ai.woego.busi.email;


/**
 * Description: 邮件发送 短信监听<br>
 * Date: 2015年1月11日 <br>
 * @category 邮件发送
 * @author xuanjian
 */
public class SmsEmailSendListener implements IEmailSendListener {

	public void before(EmailContext emailInfo) {
	}

	public void after(EmailContext emailContext) {
	}

	/**
	 *邮件发送出现问题时，发送短信
	 */
	public void afterThrowable(EmailContext emailContext) {
		System.out.println("Error in Email System!");
	}

}
