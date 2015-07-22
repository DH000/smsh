package com.lin.pager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lin.utils.SpringContextUtils;

/**
 * 
 * @ClassName: PagerTag
 * @Description: jsp分页标签，页码替换字符为#pageNum
 * @author xuelin
 * @date Jul 22, 2015 11:20:07 AM
 *
 */
public class PagerTag extends TagSupport {
	/**
	 * 分页链接<br/>
	 * 可为javascript
	 */
	private String url;

	/**
	 * 分页模板下标
	 * 
	 */
	private int type;

	/**
	 * 分页信息
	 * 
	 */
	private PageInfo pageInfo;

	/**
	 * 分页模板<br/>
	 * 默认使用第一个
	 */
	private static List<String> templateNames;

	private static final Logger logger = LoggerFactory.getLogger(PagerTag.class);

	private static final long serialVersionUID = -301554591896298181L;

	static {
		templateNames = new ArrayList<>(1);
		// 分页模板
		templateNames.add("pager.vm");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public int doStartTag() throws JspException {
		String templateName = templateNames.get(type);

		Map<String, Object> model = new HashMap<>();
		model.put("pageInfo", pageInfo);
		model.put("url", url);

		// 获取velocity引擎
		VelocityEngine velocityEngine = (VelocityEngine) SpringContextUtils.getBean("velocityEngine");
		String pageHtml = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);

		try {
			pageContext.getOut().append(pageHtml);
		} catch (IOException e) {
			e.printStackTrace();
			if (logger.isErrorEnabled()) {
				logger.error("分页器io异常", e);
			}
		}

		return SKIP_BODY;
	}
}
