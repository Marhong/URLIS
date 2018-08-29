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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IAssessmentDao;
import com.hwadee.dao.IAttendanceDao;
import com.hwadee.dao.IExamDao;
import com.hwadee.dao.IExamRecordDao;
import com.hwadee.dao.IPaperDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.AssessmentEntity;
import com.hwadee.model.AttendanceEntity;
import com.hwadee.model.ExamEntity;
import com.hwadee.model.ExamRecordEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS 类名称: AssessmentController 创建人: wangbin 创建时间: 2018年8月27日 下午5:49:46
 */
@Controller
@RequestMapping("assessment")
public class AssessmentController {
	

	/**
	 * @Title: indexAssessment
	 * @Description: 获取所有的考核记录
	 * @Time: 2018年8月29日 上午11:24:37
	 * @author: wangbin
	 * @param pno 展示的数据页码，从1开始
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView indexAssessment(String pno) {
		List<AssessmentEntity> assesslist = new ArrayList<AssessmentEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assessDao = session.getMapper(IAssessmentDao.class);

		assesslist = assessDao.getAllAssessments();

		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(assesslist.size()>0) {
			List<AssessmentEntity> tenQuestions = new ArrayList<AssessmentEntity>();
			int count = 0;
			int no = 0;
			// 因为数据分页，所以每次最多传10条数据
			
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			
			// 根据页码更改取数据的起点
			for(int i=(StaticNumber.PAGE_ITEMS*no);i<assesslist.size();i++) {
				tenQuestions.add(assesslist.get(i));
				count++;
				if(count == StaticNumber.PAGE_ITEMS) {
					break;
				}
			}
			model.addAttribute("assesslist",tenQuestions);
			model.addAttribute("totalRecords",assesslist.size());
			int totalPage = 0;
			// 设定每页展示10条数据，由此计算总共应有多有页
			if(assesslist.size()%StaticNumber.PAGE_ITEMS != 0) {
				totalPage = assesslist.size()/StaticNumber.PAGE_ITEMS+1;
				
			}else {
				totalPage = assesslist.size()/StaticNumber.PAGE_ITEMS;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/JobPerformanceAssessment/AssessmentManagement/index",model);
		
	}

	@RequestMapping("/add")
	public ModelAndView addAssessment() {

		return new ModelAndView("/JobPerformanceAssessment/AssessmentManagement/add");
	}

	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> saveAssessment(AssessmentEntity assessment) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assessmentDao = session.getMapper(IAssessmentDao.class);
		// 如果该员工已经生成了次月的考核记录，则提示错误
		if (assessmentDao.getAssessmentByPerIdAndYearMon(assessment.getPer_id(), assessment.getAsse_date()) != null) {
			resultMap.put("result", "exist");
			return resultMap;
		} else {
			// 默认是在每月28号考核
			assessment.setAsse_date(assessment.getAsse_date() + "-28");
			boolean flag = assessmentDao.insertAssessment(assessment);
			session.commit();
			MyBatiesUtil.closeSqlSession();
			if (flag) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "false");
			}

		}
		return resultMap;
	}

	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchAssessment(String per_name) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AssessmentEntity> assesslist = new ArrayList<AssessmentEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assedao = session.getMapper(IAssessmentDao.class);

		assesslist = assedao.getAssessmentsByName(per_name);
		MyBatiesUtil.closeSqlSession();
		if (assesslist != null) {
			resultMap.put("assesslist", assesslist);
			return resultMap;
		} else {
			return resultMap;
		}

	}

	@RequestMapping("/deleteItem")
	@ResponseBody
	public Map<String, Object> deleteAssessment(int asse_id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assessmentDao = session.getMapper(IAssessmentDao.class);
		boolean flag = assessmentDao.deleteAssessment(asse_id);
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
	public Map<String, Object> deleteSomeAssessments(String ids) {

		// 存储考核记录编号的集合
		List<Integer> list = new ArrayList<Integer>();
		// 编号字符串是以","作为分隔符，将String转为int型，加入到集合中
		String[] idList = ids.split(",");
		if (idList.length > 0) {
			for (int i = 0; i < idList.length; i++) {
				list.add(Integer.parseInt(idList[i]));
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assessmentDao = session.getMapper(IAssessmentDao.class);

		boolean flag = assessmentDao.deleteSomeAssessments(list);
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

	@RequestMapping("/calAssessmentScore")
	@ResponseBody
	public Map<String, Object> calAssessmentScore(String per_id, String yearmon) {
		SqlSession session = MyBatiesUtil.getSqlSession();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		float score = calExamScore(per_id, yearmon,session);
		if (score > 0) {
			resultMap.put("score", score);
		}
		MyBatiesUtil.closeSqlSession();
		return resultMap;
	}

	@RequestMapping("/calAttendanceScore")
	@ResponseBody
	public Map<String, Object> calAttendanceScore(String per_id, String yearmon) {
		SqlSession session = MyBatiesUtil.getSqlSession();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		float score = calAttnScore(per_id, yearmon,session);
		if (score > 0) {
			resultMap.put("score", score);
		}
		MyBatiesUtil.closeSqlSession();
		return resultMap;
	}

	@RequestMapping("/detail")
	public ModelAndView detailAssessment(int asse_id) {

		SqlSession session = MyBatiesUtil.getSqlSession();
		IAssessmentDao assessmentDao = session.getMapper(IAssessmentDao.class);
		AssessmentEntity assessment = assessmentDao.getAssessmentById(asse_id);

		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (assessment != null) {

			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("assessment", assessment);
			return new ModelAndView("/JobPerformanceAssessment/AssessmentManagement/detail", modelMap);

		} else {
			return new ModelAndView();
		}

	}

	/**
	 * @Title: calExamScore
	 * @Description: 计算该月的考试分数
	 * @Time: 2018年8月29日 上午11:32:10
	 * @author: wangbin
	 * @param per_id 身份证
	 * @param yearmon 年月 2018-08
	 * @param session
	 * @return
	 */
	public float calExamScore(String per_id, String yearmon,SqlSession session) {
		
		IExamRecordDao recordDao = session.getMapper(IExamRecordDao.class);
		IExamDao examDao = session.getMapper(IExamDao.class);
		List<ExamRecordEntity> recordlist = recordDao.getRecordssByPerId(per_id);
		List<ExamEntity> examlist = examDao.getExamsByYearMon(yearmon);
		float sum = 0, score = 0;
		int examItems = 0;

		// 先获取per_id的所有成绩记录
		// 再筛选出那些是在指定年月的成绩记录进行计算
		for (ExamRecordEntity record : recordlist) {

			int exam_id = record.getExam_id();
			boolean isCurMonExam = false;
			
			for (ExamEntity exam : examlist) {
				if (exam.getExam_id() == exam_id) {
					isCurMonExam = true;
					break;
				}
			}
			if (isCurMonExam) {
				sum += record.getExam_score();
				examItems += 1;
			}
		}

		// 成绩取平均分
		if (examItems > 0) {
			score = sum / examItems;
			return score;
		} else {
			return 0;
		}

	}

	/**
	 * @Title: calAttnScore
	 * @Description: 计算考勤分数
	 * @Time: 2018年8月29日 上午11:33:59
	 * @author: wangbin
	 * @param per_id 身份证
	 * @param yearmon 年月 2018-08
	 * @param session
	 * @return
	 */
	public float calAttnScore(String per_id, String yearmon,SqlSession session) {
		
		IAttendanceDao attendanceDao = session.getMapper(IAttendanceDao.class);
		List<AttendanceEntity> attnlist = attendanceDao.getAttnsByIdAndYearMon(per_id, yearmon);
		
		// 设定基础分为60，有一次不正常记录就扣一分
		float score = StaticNumber.BASIC_SCORE;
		for (AttendanceEntity attn : attnlist) {
			if (!attn.getAttn_status().equals("正常")) {
				score -= 1;
			} else {
				score += 1;
			}
		}

		return score;
	}

	/**
	 * @Title: generateAssessment
	 * @Description: 自动生成所有的考核记录
	 * @Time: 2018年8月29日 上午11:35:37
	 * @author: wangbin
	 * @return
	 */
	@RequestMapping("/generate")
	public String generateAssessment() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		// 先获取要生成记录的年月 2018-08
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if (Integer.parseInt(month) < 10) {
			month = "0" + month;
		}
		SqlSession session = MyBatiesUtil.getSqlSession();
		
		IAssessmentDao assessmentDao = session.getMapper(IAssessmentDao.class);
		// 如果该年月的考核记录已经生成就返回原页面
		if (assessmentDao.getAssessmentByYearMon(year + "-" + month).size()>0) {
			return "redirect:/assessment/index";
		} else {

			IPersonDao personDao = session.getMapper(IPersonDao.class);

			String yearmon = year + "-" + month;
			List<PersonEntity> personlist = personDao.getAllPerson();
			
			// 为所有尚未生成该年月考核记录的人员生成考核记录
			// 如果有的人已经生成了考核记录，就跳过这个人
			for (PersonEntity person : personlist) {
				String per_id = person.getPer_id();
				String per_name = person.getPer_name();
				float exam_score = calExamScore(per_id, yearmon,session);
				float attn_score = calAttnScore(per_id, yearmon,session);

				float asse_score = (float) (exam_score * 0.7 + attn_score * 0.3);
				
				AssessmentEntity assessment = new AssessmentEntity();
				assessment.setPer_id(per_id);
				assessment.setAsse_date(year + "-" + month + "-28");
				assessment.setAsse_score(asse_score);
				assessment.setPer_name(per_name);
				
				boolean flag = assessmentDao.insertAssessment(assessment);
				if (!flag) {
					continue;
				}
			}
			session.commit();
			MyBatiesUtil.closeSqlSession();
			resultMap.put("result", "success");
			return "redirect:/assessment/index";
		}
	}
}
