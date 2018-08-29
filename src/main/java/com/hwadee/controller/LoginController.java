/**
 * 
 */
package com.hwadee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/**
	 * @Title: login
	 * @Description: 验证登陆
	 * @Time: 2018年8月29日 下午12:08:39
	 * @author: wangbin
	 * @param per_id 身份证
	 * @param password 密码
	 * @param req 
	 * @param resp
	 * @return
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public Map<String, Object> login(String per_id,String password,HttpServletRequest req, HttpServletResponse resp) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		PersonEntity person = personDao.getPersonById(per_id);
		Map<String,Object> model = new HashMap<String, Object>();
		 // 只有per_id和password都互相匹配才正确
		if(person != null) {
			if(password.equals(person.getPassword())) {
				
				model.put("result","success");
			}else {
				model.put("result","wrong");
			}
		}else {
			model.put("result","wrong");
		}
		MyBatiesUtil.closeSqlSession();
		return model;
	}
	

	/**
	 * @Title: goWebIndex
	 * @Description: 跳转到首页
	 * @Time: 2018年8月29日 下午12:09:38
	 * @author: wangbin
	 * @param per_id 身份证
	 * @return
	 */
	@RequestMapping("/admin")
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
	@RequestMapping("/login")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/login";
		
	}
}
