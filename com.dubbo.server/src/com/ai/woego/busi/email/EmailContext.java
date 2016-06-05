package com.ai.woego.busi.email;

/**
 * Description: 邮件发送执行的上下文信息<br>
 * Date: 2015年1月11日 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public class EmailContext {

	private EmailInfo emailInfo;
	private Throwable throwable;


	public EmailInfo getEmailInfo() {
		return emailInfo;
	}

	
	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
