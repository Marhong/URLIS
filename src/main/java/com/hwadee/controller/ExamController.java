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

	/**
	 * @Title: indexExam
	 * @Description: 获取考试记录
	 * @Time: 2018年8月29日 上午11:54:10
	 * @author: wangbin
	 * @param pno 展示的数据页码，从1开始
	 * @return
	 */
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
			// 因为数据分页，所以每次最多传10条数据
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			// 根据页码更改取数据的起点
			for(int i=(StaticNumber.PAGE_ITEMS*no);i<examlist.size();i++) {
				tenQuestions.add(examlist.get(i));
				count++;
				if(count == StaticNumber.PAGE_ITEMS) {
					break;
				}
			}
			model.addAttribute("examlist",tenQuestions);
			model.addAttribute("totalRecords",examlist.size());
			// 设定每页展示10条数据，由此计算总共应有多有页
			int totalPage = 0;
			if(examlist.size()%StaticNumber.PAGE_ITEMS != 0) {
				totalPage = examlist.size()/StaticNumber.PAGE_ITEMS+1;
				
			}else {
				totalPage = examlist.size()/StaticNumber.PAGE_ITEMS;
			
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
				// 将exam_id改为exam_name以便在jsp页面展示
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
	
		// 存储exam_id的list集合
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
	public Map<String, String> getPaperList(){
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
