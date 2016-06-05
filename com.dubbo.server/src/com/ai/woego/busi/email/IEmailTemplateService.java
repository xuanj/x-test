package com.ai.woego.busi.email;

import java.util.Map;

/**
 * Description: 模板邮件服务接口<br>
 * Date: 2015年1月11日 <br>
 * @category 模板邮件
 * @author xuanjian
 */
public interface IEmailTemplateService {

	/**
	 * 模板引擎初始化
	 * @throws Exception
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public void init() throws Exception;

	
	/**
	 * 添加模板
	 * @param templateId
	 * @param templateSource
	 * @throws Exception
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public void addTemplate(String templateId, String templateSource) throws Exception;
	
	
	/**
	 * 获取模板内容
	 * @param templateId
	 * @param parameters
	 * @return
	 * @throws Exception
	 * Date: 2015年1月11日 <br>
	 * @author xuanjian
	 */
	public String getText(String templateId, Map<Object, Object> parameters) throws Exception;
	
	

}
