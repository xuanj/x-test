package com.ai.woego.busi.email;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEmailSendFacade implements IEmailSendFacade {

	protected IEmailTemplateService emailTemplateService;
	protected EmailServer emailServer;
	
	public IEmailTemplateService getEmailTemplateService() {
		return emailTemplateService;
	}

	public void setEmailTemplateService(IEmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}

	public EmailServer getEmailServer() {
		return emailServer;
	}

	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}
	
	public AbstractEmailSendFacade() {
		
	}
	
	@Override
	public void send(EmailInfo emailInfo) {
		
	}
	
	/**
	 * 通知emailId获取要发送给的对象
	 * @param emailId
	 * @return
	 * Date: 2015-1-11 <br>
	 * @author xuanjian
	 */
	public List<String> getToList(String emailId){
		List<String> toLists = new ArrayList<String>();
		toLists.add("xuanjian@live.com");
		toLists.add("zhaoyafei@asiainfo.com");
		toLists.add("fangyunfeng@asiainfo.com");
		return toLists;
	}
	
	/**
	 * 通知templateId获取邮件主题
	 * @param templateId
	 * @return
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public String getTitle(String templateId){
		return "Email Template Test";
	}
}
