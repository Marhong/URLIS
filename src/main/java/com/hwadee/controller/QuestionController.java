/**
 * 
 */
package com.hwadee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS 类名称: QuestionController 创建人: wangbin 创建时间: 2018年8月11日 上午9:07:08
 */
@Controller
@RequestMapping("question")
public class QuestionController {
	
	
	/**
	 * @Title: addQuestion
	 * @Description: 跳转到添加问题页面
	 * @Time: 2018年8月23日 上午11:20:55
	 * @author: wangbin
	 * @return
	 */
	@RequestMapping("/add")
	public String addQuestion() {
		return "/LawEnforcementBusinessment/QuestionManagement/add";
	}

	/**
	 * @Title: editQuestion
	 * @Description: 跳转到编辑问题页面
	 * @Time: 2018年8月23日 上午11:21:12
	 * @author: wangbin
	 * @param qu_id 待编辑问题的编号
	 * @return ModelAndView 包含编辑页面地址和待编辑问题的属性
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public ModelAndView editQuestion(String qu_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		QuestionEntity question = questionDao.getQuestionById(Integer.parseInt(qu_id));
		
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if(question != null) {
			
			String[] options =  question.getQu_option().split(";");
			ModelMap modelMap = new ModelMap();

			modelMap.addAttribute("question",question);
			modelMap.addAttribute("optionA",options[0]);
			modelMap.addAttribute("optionB",options[1]);
			if(options.length == 4) {
				modelMap.addAttribute("optionC",options[2]);
				modelMap.addAttribute("optionD",options[3]);
			}
			
			return new ModelAndView("/LawEnforcementBusinessment/QuestionManagement/edit",modelMap);
		}else {
			return new ModelAndView();
		}
		
	}
	/**
	 * @Title: detailQuestion
	 * @Description: 查看一个问题详细信息
	 * @Time: 2018年8月23日 上午11:22:39
	 * @author: wangbin
	 * @param qu_id 待查看问题的编号
	 * @return ModelAndView 包含查看问题详细信息页面和问题的属性
	 */
	@RequestMapping("/detail")
	public ModelAndView detailQuestion(String qu_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		QuestionEntity question = questionDao.getQuestionById(Integer.parseInt(qu_id));
		
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if(question != null) {
			
			String[] options =  question.getQu_option().split(";");
			ModelMap modelMap = new ModelMap();

			modelMap.addAttribute("question",question);
			modelMap.addAttribute("optionA",options[0]);
			modelMap.addAttribute("optionB",options[1]);
			if(options.length == 4) {
				modelMap.addAttribute("optionC",options[2]);
				modelMap.addAttribute("optionD",options[3]);
			}
			
			return new ModelAndView("/LawEnforcementBusinessment/QuestionManagement/detail",modelMap);
		}else {
			return new ModelAndView();
		}
		
	}
	/**
	 * @Title: updateQuesion
	 * @Description: 更新一个问题
	 * @Time: 2018年8月23日 上午11:23:21
	 * @author: wangbin
	 * @param question 修改后的问题
	 * @return Map<String, Object>后台修改问题是否成功的返回值，返回给前端页面
	 */
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updateQuesion(QuestionEntity question) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		boolean flag = questionDao.updateQuestion(question);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	/**
	 * @Title: saveQuesion
	 * @Description: 将一个新问题插入数据库汇总
	 * @Time: 2018年8月23日 上午11:24:28
	 * @author: wangbin
	 * @param question 待插入的问题
	 * @return Map<String, Object>后台插入新问题是否成功的返回值，返回给前端页面
	 */
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> saveQuesion(QuestionEntity question) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		
		int id = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0, 10));
		question.setQu_id(id);
		boolean flag = questionDao.insertQuestion(question);
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}

	
	/**
	 * @Title: indexQuestion
	 * @Description: 跳转到问题管理的首页面
	 * @Time: 2018年8月23日 上午11:25:15
	 * @author: wangbin
	 * @param pno 获取pno页的数据
	 * @return String 问题管理首页面的地址
	 */
	@RequestMapping("/index")
	public ModelAndView indexQuestionPno(String pno) {
		
		List<QuestionEntity> qulist = new ArrayList<QuestionEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		qulist = questionDao.getAllQuestion();
		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(qulist.size()>0) {
			List<QuestionEntity> tenQuestions = new ArrayList<QuestionEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<qulist.size();i++) {
				tenQuestions.add(qulist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("qulist",tenQuestions);
			model.addAttribute("totalRecords",qulist.size());
			int totalPage = 0;
			if(qulist.size()%10 != 0) {
				totalPage = qulist.size()/10+1;
				
			}else {
				totalPage = qulist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/LawEnforcementBusinessment/QuestionManagement/index",model);
	}
	/**
	 * @Title: searchQuestion
	 * @Description: 根据问题类型和问题内容插叙问题
	 * @Time: 2018年8月23日 上午11:26:03
	 * @author: wangbin
	 * @param qu_content 输入的问题内容
	 * @param qu_type 输入的问题类型
	 * @return Map<String, Object>将查询得到的问题作为一个属性值返回给前端页面
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchQuestion(String qu_content,String qu_type) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<QuestionEntity> qulist = new ArrayList<QuestionEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		qulist = questionDao.getQuestionsByContentAndType(qu_content, qu_type);
		
		MyBatiesUtil.closeSqlSession();
		
		if(qulist.size()>0) {
			resultMap.put("qulist", qulist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	
	/**
	 * @Title: deleteQuestion
	 * @Description: 删除一个问题
	 * @Time: 2018年8月23日 上午11:27:12
	 * @author: wangbin
	 * @param qu_id 待删除问题的编号
	 * @return  Map<String, Object>后台删除问题是否成功的返回值，返回给前端页面
	 */
	@RequestMapping("/deleteItem")
	@ResponseBody
	public Map<String, Object> deleteQuestion(String qu_id) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		boolean flag = questionDao.deleteQuestion(Integer.parseInt(qu_id));
	
		session.commit();
		MyBatiesUtil.closeSqlSession();

		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	
	/**
	 * @Title: deleteSomeQuestions
	 * @Description: 批量删除问题
	 * @Time: 2018年8月23日 上午11:28:11
	 * @author: wangbin
	 * @param ids 待删除问题的编号串
	 * @return Map<String, Object>后台删除问题是否成功的返回值，返回给前端页面
	 */
	@RequestMapping("/deleteSome")
	@ResponseBody
	public Map<String, Object> deleteSomeQuestions(String ids) {
		System.out.println("QuestionController运行了");
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
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		System.out.println(list);
		boolean flag = questionDao.deleteSomeQuestions(list);
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
