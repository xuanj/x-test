package com.ai.woego.busi.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailInfo {
	
    private String emailId;
    
	private String templateId;
	
	private List<String> toList;
	
	/**
	 * 抄送对象
	 */
	private List<String> ccList;
	
	private String title;
	
	private String content;

	private Map<Object, Object> parameters = new HashMap<Object, Object>();
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public List<String> getToList() {
		return toList;
	}

	public void setToList(List<String> toList) {
		this.toList = toList;
	}

	
	public Map<Object, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<Object, Object> parameters) {
		this.parameters = parameters;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }
}
