package com.lin.action.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lin.entity.test.Tester;
import com.lin.pager.PageInfo;
import com.lin.service.test.TesterService;
import com.lin.web.BaseController;

@Controller
@Scope("prototype")
public class PagerController extends BaseController {
	@Resource
	private TesterService testerService;

	@RequestMapping(value = "/test/pager.asp")
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response){
		int page = Integer.valueOf(request.getParameter("page"));
		PageInfo pageInfo = new PageInfo(10, page, testerService.count());
		List<Tester> list = testerService.findForPage(pageInfo.getOffset(), pageInfo.getPageSize());
		System.out.println(list);
		
		ModelAndView view = new ModelAndView("index");
		view.addObject("pageInfo", pageInfo);
		
		return view;
	}
	
}
