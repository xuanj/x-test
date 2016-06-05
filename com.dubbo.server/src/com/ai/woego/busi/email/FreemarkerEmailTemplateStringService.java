package com.ai.woego.busi.email;

import java.io.StringWriter;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;

/**
 * Description: Freemarker 基于String的模板服务<br>
 * Date: 2015-1-11 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public class FreemarkerEmailTemplateStringService extends
		AbstractFreemarkerEmailTemplateService {
	private StringTemplateLoader strLoader = new StringTemplateLoader();
	
	@Override
	public void init() throws Exception {
		super.init();
		if(TEMPLATE_CACHE.isEmpty()){
			initTemplateCache();
		} 
	}
	
	@Override
	public String getText(String templateId, Map<Object, Object> parameters) throws Exception{
		Template template = TEMPLATE_CACHE.get(templateId);
		if(template == null){
			System.out.println("不存在模板：" + templateId + ",请先添加模板！");
			throw new RuntimeException();
		}
		StringWriter stringWriter = new StringWriter();
		template.process(parameters, stringWriter);
		return stringWriter.toString();
	}

	//初始化String模板缓存
	private void initTemplateCache() throws Exception{
		//TODO:从数据库初始化
		addTemplate("001", "Hello~ ${name}'s world!");
		addTemplate("002", "Bye~ ${name}'s world!");
		
	}
	
	@Override
	public void addTemplate(String templateId, String templateSource) throws Exception{
		if(templateSource==null||templateSource==""){
			System.out.println("设置模板语句有误！");
			throw new Exception();
		}
		strLoader.putTemplate(templateId, templateSource);
		cfg.setTemplateLoader(strLoader);
		Template template = cfg.getTemplate(templateId);
		TEMPLATE_CACHE.put(templateId, template);
	}
}
