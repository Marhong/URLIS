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
import com.hwadee.dao.IExamRecordDao;
import com.hwadee.dao.IInformationChangeDao;
import com.hwadee.dao.IPaperDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.ExamEntity;
import com.hwadee.model.ExamRecordEntity;
import com.hwadee.model.PaperEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: ExamRecordController
 * 创建人: wangbin
 * 创建时间: 2018年8月27日 上午9:56:18
 */
@Controller
@RequestMapping("examrecord")
public class ExamRecordController {

/*	@RequestMapping("/index")
	public ModelAndView indexExamRecord(String exam_id) {
		List<ExamRecordEntity> recordlist = new ArrayList<ExamRecordEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
	
		if(exam_id != null && !exam_id.equals("")) {
			recordlist = recordDao.getExamsByExamId(Integer.parseInt(exam_id));
		}else {
			recordlist = recordDao.getAllExamRecords();
		}

		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		
		model.addAttribute("recordlist",recordlist);

		return new ModelAndView("/LawEnforcementBusinessment/MarkManagement/index",model);
	}*/
	@RequestMapping("/index")
	public ModelAndView indexExamRecord(String pno) {
		List<ExamRecordEntity> recordlist = new ArrayList<ExamRecordEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
	
		recordlist = recordDao.getAllExamRecords();
		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(recordlist.size()>0) {
			List<ExamRecordEntity> tenQuestions = new ArrayList<ExamRecordEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<recordlist.size();i++) {
				tenQuestions.add(recordlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("recordlist",tenQuestions);
			model.addAttribute("totalRecords",recordlist.size());
			int totalPage = 0;
			if(recordlist.size()%10 != 0) {
				totalPage = recordlist.size()/10+1;
				
			}else {
				totalPage = recordlist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/LawEnforcementBusinessment/MarkManagement/index",model);
	}
	@RequestMapping("/add")
	public ModelAndView addExam() {
		ExamRecordEntity record = new ExamRecordEntity();

		return new ModelAndView( "/LawEnforcementBusinessment/MarkManagement/add","command",record);
	}
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> saveExamRecord(ExamRecordEntity record) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		IExamDao examDao = session.getMapper(IExamDao.class);
		IPersonDao personDao =session.getMapper(IPersonDao.class);
		IDepartmentDao deptDao =session.getMapper(IDepartmentDao.class);
		if(record.getPer_id() != null && record.getExam_id() >0) {
			PersonEntity person = personDao.getPersonById(record.getPer_id());
			
			ExamEntity exam = examDao.getExamById(record.getExam_id());
			DepartmentEntity departmentEntity = deptDao.getDeptById(person.getDept_id());
			record.setPer_name(person.getPer_name());
			record.setPosname(person.getPos_name());
			record.setExam_name(exam.getExam_name());
			record.setDept_name(departmentEntity.getDept_name());
			
		}
		boolean flag = recordDao.insertExamRecord(record);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	@RequestMapping("/deleteItem")
	@ResponseBody
	public Map<String, Object> deleteExamRecord(String exam_id,String per_id) {
		System.out.println(exam_id+"     "+per_id);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		boolean flag = recordDao.deleteExamRecord(Integer.parseInt(exam_id), per_id);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	@RequestMapping("/deleteSome")
	@ResponseBody
	public Map<String, Object> deleteSomeRecords(String ids) {
		System.out.println(ids);
		// 将编号字符串转换为List集合
		List<String> recordidList = new ArrayList<String>();
		List<Integer> examidList = new ArrayList<Integer>();
		List<String> peridList = new ArrayList<String>();
		// 编号字符串是以","作为分隔符
		String[] idList = ids.split(",");
		if(idList.length>0) {
			for(int i=0;i<idList.length;i++) {
				recordidList.add(idList[i]);
				}
			System.out.println(recordidList);
			if(recordidList.size()>0) {
				for(int j=0;j<recordidList.size();j++) {
					String recordid = recordidList.get(j);
					examidList.add(Integer.parseInt(recordid.split(":")[0]));
					peridList.add(recordid.split(":")[1]);
				}
			}
		}
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		System.out.println(examidList+"\n"+peridList);
		boolean flag = false;
		for(int i=0;i<examidList.size();i++) {
			flag = recordDao.deleteExamRecord(examidList.get(i), peridList.get(i));
		}
		
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
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchExamRecord(String exam_name,String per_name) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<ExamRecordEntity> recordlist = new ArrayList<ExamRecordEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		
		recordlist = recordDao.getExamsByExamNameOrPerName(exam_name, per_name);
		MyBatiesUtil.closeSqlSession();
		
		if(recordlist.size()>0) {
			resultMap.put("recordlist", recordlist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@ModelAttribute("examlist")
	public Map<Integer, String> getExamList(){
		Map<Integer, String> list = new HashMap<Integer, String>();
		List<ExamEntity> examlist = new ArrayList<ExamEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamDao examdao = session.getMapper(IExamDao.class);
		examlist = examdao.getAllExams();
		MyBatiesUtil.closeSqlSession();
		if(examlist.size()>0) {
			for(ExamEntity exam: examlist) {
				list.put(exam.getExam_id(),exam.getExam_name());
			}
		}
		return list;
	}
}
