/**
 * 
 */
package com.hwadee.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IDepartmentDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.model.PersonEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: LoginController
 * 创建人: wangbin
 * 创建时间: 2018年8月28日 下午6:54:10
 */
@Controller
public class LoginController {
	
	@RequestMapping("/webindex")
	public ModelAndView goWebIndex(String per_id) {
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);

		PersonEntity person = personDao.getPersonById(per_id);
		MyBatiesUtil.closeSqlSession();
		if(person != null) {
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("per_name",person.getPer_name());
			
			return new ModelAndView("/index",modelMap);
		}else {
		
			return new ModelAndView();
		}
	}
	@RequestMapping("/logout")
	public String logout() {
	
		return "/login";
		
	}
}
