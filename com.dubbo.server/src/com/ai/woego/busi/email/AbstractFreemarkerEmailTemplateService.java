package com.ai.woego.busi.email;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;


public abstract class AbstractFreemarkerEmailTemplateService implements
		IEmailTemplateService {

	protected Configuration cfg = new Configuration();
	//缓存模板
	protected static final Map<String,Template> TEMPLATE_CACHE = new HashMap<String, Template>();
	
	public void init() throws Exception{
		cfg = new Configuration();
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		cfg.setDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	

	@Override
	public void addTemplate(String templateId, String templateSource)
			throws Exception {
		
	}
}
