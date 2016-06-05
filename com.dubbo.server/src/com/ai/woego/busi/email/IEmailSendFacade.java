package com.ai.woego.busi.email;

/**
 * Description: 邮件门面接口<br>
 * Date: 2015年1月11日 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public interface IEmailSendFacade {
	
	/**
	 * 发送邮件
	 * @param emailInfo
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public void send(EmailInfo emailInfo);
	
}
