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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IDepartmentDao;
import com.hwadee.dao.IExamDao;
import com.hwadee.dao.IPaperDao;
import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.ExamEntity;
import com.hwadee.model.PaperEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: ExamController
 * 创建人: wangbin
 * 创建时间: 2018年8月27日 上午8:55:52
 */
@Controller
@RequestMapping("exam")
public class ExamController {

	@RequestMapping("/index")
	public ModelAndView indexExam(String pno) {
		List<ExamEntity> examlist = new ArrayList<ExamEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamDao examDao = session.getMapper(IExamDao.class);
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		examlist = examDao.getAllExams();
		if(examlist != null) {
			for(ExamEntity exam: examlist) {
				exam.setPaper_id(paperDao.getPaperById(exam.getPaper_id()).getPaper_name());
			}
		}
		
		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(examlist.size()>0) {
			List<ExamEntity> tenQuestions = new ArrayList<ExamEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<examlist.size();i++) {
				tenQuestions.add(examlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("examlist",tenQuestions);
			model.addAttribute("totalRecords",examlist.size());
			int totalPage = 0;
			if(examlist.size()%10 != 0) {
				totalPage = examlist.size()/10+1;
				
			}else {
				totalPage = examlist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/LawEnforcementBusinessment/ExamManagement/index",model);
	}
	
	@RequestMapping("/add")
	public ModelAndView addExam() {
		PaperEntity paperEntity = new PaperEntity();

		return new ModelAndView( "/LawEnforcementBusinessment/ExamManagement/add","command",paperEntity);
	}
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> saveExam(ExamEntity exam) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamDao examDao = session.getMapper(IExamDao.class);
		
		
		boolean flag = examDao.insertExam(exam);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchExam(String exam_name) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<ExamEntity> examList = new ArrayList<ExamEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamDao examDao = session.getMapper(IExamDao.class);
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		examList = examDao.getExamsByName(exam_name);
		if(examList != null) {
			for(ExamEntity exam: examList) {
				exam.setPaper_id(paperDao.getPaperById(exam.getPaper_id()).getPaper_name());
			}
		}
		MyBatiesUtil.closeSqlSession();
		
		if(examList.size()>0) {
			resultMap.put("examlist", examList);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/deleteSome")
	@ResponseBody
	public Map<String, Object> deleteSomeExams(String ids) {
	
		// 将编号字符串转换为List集合
		List<Integer> list = new ArrayList<Integer>();
		// 编号字符串是以","作为分隔符
		String[] idList = ids.split(",");
		if(idList.length>0) {
			for(int i=0;i<idList.length;i++) {
				String idString = idList[i];
				if(idString != null && idString != "") {
					int id = Integer.parseInt(idString);
					list.add(id);
				}
				
			}
		}
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamDao examDao = session.getMapper(IExamDao.class);
	
		boolean flag = examDao.deleteSomeExams(list);
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
	@ModelAttribute("paperlist")
	public Map<String, String> getDepartmentList(){
		Map<String, String> paperMap = new HashMap<String, String>();
		List<PaperEntity> paperlist = new ArrayList<PaperEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		paperlist = paperDao.getAllPaper();
		MyBatiesUtil.closeSqlSession();
		if(paperlist.size()>0) {
			for(PaperEntity paper: paperlist) {
				paperMap.put(paper.getPaper_id(),paper.getPaper_name());
			}
		}
		return paperMap;
	}
}
