package com.ai.woego.busi.email;

/**
 * Description: 邮件服务监听器<br>
 * Date: 2015年1月11日 <br>
 * @category 模板邮件 
 * @author xuanjian
 */
public interface IEmailSendListener {
    
	/**
	 * 邮件发送前的操作
	 * @param context
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public abstract void before(EmailContext context);

	
	/**
	 *  邮件发送后的操作
	 * @param context
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public abstract void after(EmailContext context);

	/**
	 * 发送邮件出现异常的处理
	 * @param context
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public abstract void afterThrowable(EmailContext context);
}
