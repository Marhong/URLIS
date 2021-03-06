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
import com.hwadee.dao.IInformationChangeDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.InformationChangeEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: InformationChangeController
 * 创建人: wangbin
 * 创建时间: 2018年8月24日 下午6:43:49
 */
@Controller
@RequestMapping("change")
public class InformationChangeController {
	
	/**
	 * @Title: indexChnage
	 * @Description: 获取人员信息变更记录
	 * @Time: 2018年8月29日 下午12:05:02
	 * @author: wangbin
	 * @param pno 展示的数据页码，从1开始
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView indexChnage(String pno) {
		List<InformationChangeEntity> changeList = new ArrayList<InformationChangeEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IInformationChangeDao changeDao = session.getMapper(IInformationChangeDao.class);
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		changeList = changeDao.getAllChange();
		
		if(changeList.size()>0) {
			for(InformationChangeEntity change: changeList) {
				String dept_id = change.getDept_id();
				change.setDept_id(deptDao.getDeptById(dept_id).getDept_name());
			}
		}
		
		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(changeList.size()>0) {
			List<InformationChangeEntity> tenQuestions = new ArrayList<InformationChangeEntity>();
			int count = 0;
			int no = 0;
			// 因为数据分页，所以每次最多传10条数据
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			// 根据页码更改取数据的起点
			for(int i=(StaticNumber.PAGE_ITEMS*no);i<changeList.size();i++) {
				tenQuestions.add(changeList.get(i));
				count++;
				if(count == StaticNumber.PAGE_ITEMS) {
					break;
				}
			}
			model.addAttribute("changeList",tenQuestions);
			model.addAttribute("totalRecords",changeList.size());
			// 设定每页展示10条数据，由此计算总共应有多有页
			int totalPage = 0;
			if(changeList.size()%StaticNumber.PAGE_ITEMS != 0) {
				totalPage = changeList.size()/StaticNumber.PAGE_ITEMS+1;
				
			}else {
				totalPage = changeList.size()/StaticNumber.PAGE_ITEMS;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/PersonnelInformation/StaffChangeManagement/index",model);
	}
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchChange(String per_name,String per_id) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<InformationChangeEntity> changelist = new ArrayList<InformationChangeEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IInformationChangeDao changeDao = session.getMapper(IInformationChangeDao.class);
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		changelist = changeDao.getChangeByNameOrId(per_name, per_id);

		if(changelist.size()>0) {
			for(InformationChangeEntity change: changelist) {
				String dept_id = change.getDept_id();
				change.setDept_id(deptDao.getDeptById(dept_id).getDept_name());
			}
			resultMap.put("changelist", changelist);
			MyBatiesUtil.closeSqlSession();
			return resultMap;
		}else {
			MyBatiesUtil.closeSqlSession();
			return resultMap;
		}	
	}
	@RequestMapping("/detail")
	public ModelAndView detailChange(String chanid) {
		String per_id = "",chan_time="";
		
		if(chanid != null) {
			per_id = chanid.split(" ")[0];
			chan_time = chanid.split(" ")[1];
		}else {
			return null;
		}
		SqlSession session = MyBatiesUtil.getSqlSession();
		IInformationChangeDao changeDao = session.getMapper(IInformationChangeDao.class);
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		InformationChangeEntity change = new InformationChangeEntity();
		InformationChangeEntity data = changeDao.getChangeByIdAndDate(per_id, chan_time);
		if(data != null) {
			change.setChan_dep(data.getChan_dep());
			change.setChan_onpost(data.getChan_onpost());
			change.setChan_posname(data.getChan_posname());
			change.setChan_tel(data.getChan_tel());
			change.setChan_time(data.getChan_time());
			change.setChan_workduty(data.getChan_workduty());
			change.setPer_id(data.getPer_id());
			change.setPer_name(data.getPer_name());
			change.setPos_name(data.getPos_name());
			DepartmentEntity dept = deptDao.getDeptById(data.getDept_id());
			change.setDept_id(dept.getDept_name());
			
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView("/PersonnelInformation/StaffChangeManagement/detail","command",change);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
		
	}
	@RequestMapping("/deleteSome")
	@ResponseBody
	public Map<String, Object> deleteSomeChange(String ids) {
		System.out.println(ids);
		// 存储人员信息变更记录的编号
		// 因为人员信息变更记录是以per_id和chan_time为主键的所以需要二次分割
		// 这里设定的是一个人员一天只修改一次信息
		List<String> chanidList = new ArrayList<String>();
		List<String> peridList = new ArrayList<String>();
		List<String> chantimeList = new ArrayList<String>();
		// 编号字符串是以","作为分隔符
		String[] idList = ids.split(",");
		if(idList.length>0) {
			for(int i=0;i<idList.length;i++) {
				chanidList.add(idList[i]);
				}
			System.out.println(chanidList);
			if(chanidList.size()>0) {
				// 二次分割得到per_id和chan_time
				for(int j=0;j<chanidList.size();j++) {
					String chanid = chanidList.get(j);
					peridList.add(chanid.split(":")[0]);
					chantimeList.add(chanid.split(":")[1]);
				}
			}
		}
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IInformationChangeDao changeDao = session.getMapper(IInformationChangeDao.class);
		
		boolean flag = changeDao.deleteSomeChange(peridList, chantimeList);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			// 如果删除成功，返回“success”
			resultMap.put("result", "success");
		} else {
			// 如果失败,返回"false"
			resultMap.put("result", "false");
		}
		return resultMap;
	}
}
