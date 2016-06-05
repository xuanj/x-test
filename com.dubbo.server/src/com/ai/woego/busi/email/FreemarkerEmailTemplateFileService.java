package com.ai.woego.busi.email;

import java.io.StringWriter;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Template;

/**
 * Description: Freemarker 基于File的模板服务<br>
 * Date: 2015-1-11 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public class FreemarkerEmailTemplateFileService extends
		AbstractFreemarkerEmailTemplateService {

	private static final String TEMPLATE_PATH = "/";

	private String fileName;
	
	public FreemarkerEmailTemplateFileService(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void init() throws Exception {
		super.init();
		cfg.setTemplateLoader(new ClassTemplateLoader(FreemarkerEmailTemplateFileService.class, TEMPLATE_PATH));
	}
	
	public String getText(String templateId,Map<Object, Object> parameters) throws Exception{
		try {
			Template template = TEMPLATE_CACHE.get(templateId);
			if(template == null){
				template = cfg.getTemplate(fileName);
				TEMPLATE_CACHE.put(templateId, template);
			}
			StringWriter stringWriter = new StringWriter();
			template.process(parameters, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
