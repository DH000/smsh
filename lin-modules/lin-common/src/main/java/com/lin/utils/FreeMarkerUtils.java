package com.lin.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @ClassName: FreeMarkerUtils
 * @Description: freeMarker辅助类
 * @author xuelin
 * @date Jul 21, 2015 6:43:13 PM
 *
 */
public final class FreeMarkerUtils  {

	/**
	 * 合并模板
	 * 
	 * @param configuration
	 * @param templateName
	 * @param model
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String processTemplateIntoString(Configuration configuration, String templateName, Object model) throws IOException, TemplateException {
		StringWriter result = new StringWriter();
		processTemplate(configuration, result, templateName, model);
		return result.toString();
	}

	/**
	 * 合并模板
	 * 
	 * @param configuration
	 * @param templateName
	 * @param model
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void processTemplate(Configuration configuration, Writer writer, String templateName, Object model) throws IOException, TemplateException {
		Template template = configuration.getTemplate(templateName);
		template.process(model, writer);
	}

}
