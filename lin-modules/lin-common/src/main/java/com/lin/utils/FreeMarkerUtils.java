package com.lin.utils;

import java.io.IOException;
import java.io.Writer;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

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
public final class FreeMarkerUtils extends FreeMarkerTemplateUtils {

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
		Template template = configuration.getTemplate(templateName);
		return processTemplateIntoString(template, model);
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
	public static void processTemplateIntoString(Configuration configuration, Writer writer, String templateName, Object model) throws IOException, TemplateException {
		Template template = configuration.getTemplate(templateName);
		template.process(model, writer);
	}

}
