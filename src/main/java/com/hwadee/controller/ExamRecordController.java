/**
 * 
 */
package com.hwadee.controller;

import java.util.ArrayList;
import java.util.Calendar;
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


	/**
	 * @Title: indexExamRecord
	 * @Description: 获取考试成绩记录
	 * @Time: 2018年8月29日 上午11:56:29
	 * @author: wangbin
	 * @param pno 展示的数据页码，从1开始
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView indexExamRecord(String pno) {
		List<ExamRecordEntity> recordlist = new ArrayList<ExamRecordEntity>();
		ModelMap model = new ModelMap();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
			recordlist = recordDao.getAllExamRecords();
			if(recordlist.size()>0) {
				List<ExamRecordEntity> tenQuestions = new ArrayList<ExamRecordEntity>();
				int count = 0;
				int no = 0;
				// 因为数据分页，所以每次最多传10条数据
				if(pno != null && !pno.equals("")) {
					no= Integer.parseInt(pno)-1;
				}else {
					no=0;
				}
				// 根据页码更改取数据的起点
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
				// 设定每页展示10条数据，由此计算总共应有多有页
				if(recordlist.size()%10 != 0) {
					totalPage = recordlist.size()/10+1;
					
				}else {
					totalPage = recordlist.size()/10;
				
				}
				
				model.addAttribute("totalPage",totalPage);
			}
			
		MyBatiesUtil.closeSqlSession();
		model.addAttribute("hidepage","false");
		return new ModelAndView("/LawEnforcementBusinessment/MarkManagement/index",model);
	}
	/**
	 * @Title: getRecordsByExamId
	 * @Description: 获取某一场考试的所有考试记录
	 * @Time: 2018年8月29日 下午12:00:04
	 * @author: wangbin
	 * @param exam_id 待查询的考试编号
	 * @return
	 */
	@RequestMapping("/getRecordsByExamId")
	public ModelAndView getRecordsByExamId(String exam_id) {
		List<ExamRecordEntity> recordlist = new ArrayList<ExamRecordEntity>();
		ModelMap model = new ModelMap();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		if(exam_id != null) {
			
			recordlist = recordDao.getExamsByExamId(Integer.parseInt(exam_id));
			// 如果是从考试成绩页面点击查看成绩跳转到成绩页面则隐藏分页按钮
			model.addAttribute("hidepage","true");
			model.addAttribute("recordlist",recordlist);
		}
		MyBatiesUtil.closeSqlSession();
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
		// 存储考试成绩编号的list集合
		// 考试成绩记录是以exam_id和per_id作为主键。所以需要二次分割
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
				// 二次分割，得到exam_id和per_id
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
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(examlist.size()>0) {
			// 只展示在当前日期的考试
			for(ExamEntity exam: examlist) {
				String[] tokens = exam.getExam_datetime().split("-");
				int curYear = Integer.parseInt(tokens[0]);
				int curMonth = Integer.parseInt(tokens[1]);
				int curDay = Integer.parseInt(tokens[2]);
				if(curYear<year) {
					list.put(exam.getExam_id(),exam.getExam_name());
				}else if(curMonth < month) {
					list.put(exam.getExam_id(),exam.getExam_name());
				}else if(curDay < day) {
					list.put(exam.getExam_id(),exam.getExam_name());
				}
				
			}
		}
		return list;
	}
}
