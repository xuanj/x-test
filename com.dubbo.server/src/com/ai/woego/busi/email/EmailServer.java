package com.ai.woego.busi.email;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EmailServer {
	
    private static final int nThreads = 10;
	private List<IEmailSendListener> emailSendListeners = new ArrayList<IEmailSendListener>();
	private static ExecutorService exec;
//	private static MailService mailService;
	
	public EmailServer() {
	    if(exec == null){
	        exec = Executors.newFixedThreadPool(nThreads);
	    }
//	    if(mailService == null){
//	        mailService = PaasContextHolder.getBean(AipSvcName.MailService);
//	    }
    }
	public void send(final EmailInfo emailInfo){
		
	    exec.execute(new Runnable() {
			
			@Override
			public void run() {
				EmailContext context = new EmailContext();
				context.setEmailInfo(emailInfo);
				sendBefore(context);
				try {
				    //调用AIP服务发送邮件
//					mailService.sendMail(emailInfo.getToList(), emailInfo.getTitle(), emailInfo.getContent());
					sendAfter(context);
				} catch (Exception e) {
					context.setThrowable(e);
					sendThrowable(context);
				}
				
				
			}
		});
	}
	
	public void addEmailListener(IEmailSendListener emailSendListener){
		this.emailSendListeners.add(emailSendListener);
	}
	
	public void send(List<EmailInfo> emailInfoList){
		for (EmailInfo emailInfo : emailInfoList) {
			send(emailInfo);
		}
	}
	
	private void sendBefore(EmailContext context){
		for(IEmailSendListener emailSendListener: this.emailSendListeners){
			emailSendListener.before(context);
		}
	}
	
	private void sendAfter(EmailContext context){
		for(IEmailSendListener emailSendListener: this.emailSendListeners){
			emailSendListener.after(context);
		}
	}
	
	private void sendThrowable(EmailContext context){
		for(IEmailSendListener emailSendListener: this.emailSendListeners){
			emailSendListener.afterThrowable(context);
		}
	}
	
}
