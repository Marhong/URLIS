/**
 * 
 */
package com.hwadee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IDepartmentDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: DepartmentController
 * 创建人: wangbin
 * 创建时间: 2018年8月24日 下午7:19:38
 */
@Controller
@RequestMapping("department")
public class DepartmentController {

	@RequestMapping("/index")
	public ModelAndView indexDepartment(String pno) {
		List<DepartmentEntity> deptlist = new ArrayList<DepartmentEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		deptlist = deptDao.getAllDepartment();

		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(deptlist.size()>0) {
			List<DepartmentEntity> tenQuestions = new ArrayList<DepartmentEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<deptlist.size();i++) {
				tenQuestions.add(deptlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("deptlist",tenQuestions);
			model.addAttribute("totalRecords",deptlist.size());
			int totalPage = 0;
			if(deptlist.size()%10 != 0) {
				totalPage = deptlist.size()/10+1;
				
			}else {
				totalPage = deptlist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/Organization/index",model);
		

	}
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchDept(String dept_name) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<DepartmentEntity> deptlist = new ArrayList<DepartmentEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		deptlist = deptDao.getDeptByName(dept_name);
		MyBatiesUtil.closeSqlSession();
		if(deptlist.size()>0) {
			resultMap.put("deptlist", deptlist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/add")
	public ModelAndView addPerson() {
		DepartmentEntity department = new DepartmentEntity();
		return new ModelAndView("/Organization/add","command",department);
		
	}
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> saveDepartment(DepartmentEntity department) {
	
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		boolean flag = deptDao.insertDepartment(department);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	@RequestMapping("/verify")
	@ResponseBody
	public Map<String, Object> verifyDepartment(String per_id) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao personDao = session.getMapper(IDepartmentDao.class);
		DepartmentEntity person = personDao.getDeptById(per_id);
		MyBatiesUtil.closeSqlSession();
		if(person != null) {
			resultMap.put("result", "success");
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/edit")
	public ModelAndView editDepartment(String dept_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao personDao = session.getMapper(IDepartmentDao.class);
		
		DepartmentEntity person = new DepartmentEntity();
		DepartmentEntity data = personDao.getDeptById(dept_id);
		if(data != null) {
			person.setDept_id(data.getDept_id());
			person.setDept_name(data.getDept_name());
	
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView("/Organization/edit","command",person);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
		
	}
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updatePerson(DepartmentEntity person) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao personDao = session.getMapper(IDepartmentDao.class);
		boolean flag = personDao.updateDepartment(person);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
}
