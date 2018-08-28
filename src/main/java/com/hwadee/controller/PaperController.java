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

import com.hwadee.dao.IAssessmentDao;
import com.hwadee.dao.IPaperAssemblyDao;
import com.hwadee.dao.IPaperDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.PaperAssemblyEntity;
import com.hwadee.model.PaperEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: PaperController
 * 创建人: wangbin
 * 创建时间: 2018年8月25日 下午5:25:55
 */
@Controller
@RequestMapping("paper")
public class PaperController {

	@RequestMapping("/index")
	public ModelAndView indexPaper(String pno) {
		List<PaperEntity> paperlist = new ArrayList<PaperEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		paperlist = paperDao.getAllPaper();
		ModelMap model = new ModelMap();
		MyBatiesUtil.closeSqlSession();
		if(paperlist.size()>0) {
			List<PaperEntity> tenQuestions = new ArrayList<PaperEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<paperlist.size();i++) {
				tenQuestions.add(paperlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("paperlist",tenQuestions);
			model.addAttribute("totalRecords",paperlist.size());
			int totalPage = 0;
			if(paperlist.size()%10 != 0) {
				totalPage = paperlist.size()/10+1;
				
			}else {
				totalPage = paperlist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}

		return new ModelAndView("/LawEnforcementBusinessment/PaperManagement/index",model);
	}
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchPaper(String paper_name) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<PaperEntity> paperlist = new ArrayList<PaperEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		paperlist = paperDao.getPaperByName(paper_name);
		
		MyBatiesUtil.closeSqlSession();
		
		if(paperlist.size()>0) {
			System.out.println(paperlist);
			resultMap.put("paperlist", paperlist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/add")
	public String addPaper(Model model) {
		List<QuestionEntity> qulist = new ArrayList<QuestionEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		qulist = questionDao.getAllQuestion();
		MyBatiesUtil.closeSqlSession();
		model.addAttribute("qulist",qulist);
		return "/LawEnforcementBusinessment/PaperManagement/add";
	}
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> savePaper(PaperEntity paper) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		String idString = paper.getPaper_id();
		String id = String.valueOf(System.currentTimeMillis()).substring(0, 10);
		paper.setPaper_id(id);
		boolean flag = paperDao.insertPaper(paper);
		// 通过分解得到的题目id，生成一个个的PaperAssemblyEntity
		
		String[] ids = idString.split(",");
		for(int i=0;i<ids.length;i++) {
			if(ids[i] != null && !ids[i].equals("")) {
				PaperAssemblyEntity assemblyEntity = new PaperAssemblyEntity();
				assemblyEntity.setPaper_id(id);
				assemblyEntity.setQu_id(Integer.parseInt(ids[i]));
				
				assemblyDao.insertPaperAssembly(assemblyEntity);
			}
		}
		
		session.commit();
		MyBatiesUtil.closeSqlSession();
		if (flag) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "false");
		}
		return resultMap;
	}
	@RequestMapping("/detail")
	public ModelAndView detailQuestion(String paper_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		PaperEntity paper = paperDao.getPaperById(paper_id);
		
		List<PaperAssemblyEntity> assemblyEntities = assemblyDao.getPaperAssemblysById(paper_id);
		
		List<QuestionEntity> qulist = new ArrayList<QuestionEntity>();
		
		
		if(paper != null && assemblyEntities != null) {
			
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("paper",paper);
			for(PaperAssemblyEntity assemblyEntity: assemblyEntities) {
				qulist.add(questionDao.getQuestionById(assemblyEntity.getQu_id()));
			}
			if(qulist.size()>0) {
				modelMap.addAttribute("qulist",qulist);
			}
			
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView("/LawEnforcementBusinessment/PaperManagement/detail",modelMap);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
		
	}
	@RequestMapping("/edit")
	@ResponseBody
	public ModelAndView editPaper(String paper_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		PaperEntity paper = paperDao.getPaperById(paper_id);
		
		List<PaperAssemblyEntity> assemblyEntities = assemblyDao.getPaperAssemblysById(paper_id);
		
		List<QuestionEntity> qulist = new ArrayList<QuestionEntity>();
		
		
		if(paper != null && assemblyEntities != null) {
			
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("paper",paper);
			String ids="";
			for(PaperAssemblyEntity assemblyEntity: assemblyEntities) {
				ids += assemblyEntity.getQu_id()+",";
			}
			qulist = questionDao.getAllQuestion();
			if(qulist.size()>0) {
				modelMap.addAttribute("qulist",qulist);
			}
			modelMap.addAttribute("ids",ids);
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView("/LawEnforcementBusinessment/PaperManagement/edit",modelMap);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updatePaper(PaperEntity paper) {
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		
		// 传递的paper_id包括paper本身的id和新选择的50道题的id字符串
		// 所以在更新paper其他内容之前先要从传递的paper_id中得到真正的paper_id
		String idString = paper.getPaper_id();
		String[] ids = idString.split(",");
		paper.setPaper_id(ids[0]);
		
		boolean flag = paperDao.updatePaper(paper);
		// 先删除paper_assembly表中该套试卷的所有的数据
		// 然后再往paper_assembly表中插入更新后的数据
		assemblyDao.deletePaperAssemblyById(paper.getPaper_id());
		// 通过分解得到的题目id，生成一个个的PaperAssemblyEntity
		// 题目的id就要从第二个开始获取
		for(int i=1;i<ids.length;i++) {
			if(ids[i] != null && !ids[i].equals("")) {
				PaperAssemblyEntity assemblyEntity = new PaperAssemblyEntity();
				assemblyEntity.setPaper_id(paper.getPaper_id());
				assemblyEntity.setQu_id(Integer.parseInt(ids[i]));			
				assemblyDao.insertPaperAssembly(assemblyEntity);
			}
		}
		
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
	public Map<String, Object> deletePaper(String paper_id) {
		System.out.println(paper_id+"paper_id controller");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		boolean flag = paperDao.deletePaper(paper_id);	
		assemblyDao.deletePaperAssemblyById(paper_id);
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
	public Map<String, Object> deleteSomePerson(String ids) {
		
		// 将编号字符串转换为List集合
		List<String> list = new ArrayList<String>();
		// 编号字符串是以","作为分隔符
		String[] idList = ids.split(",");
		if(idList.length>0) {
			for(int i=0;i<idList.length;i++) {
					list.add(idList[i]);
				}
			}
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPaperDao paperDao = session.getMapper(IPaperDao.class);
		IPaperAssemblyDao assemblyDao = session.getMapper(IPaperAssemblyDao.class);
		boolean flag = paperDao.deleteSomePaper(list);
		assemblyDao.deletePaperBySomeId(list);
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
