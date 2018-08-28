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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IDepartmentDao;
import com.hwadee.dao.IInformationChangeDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.InformationChangeEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: PersonController
 * 创建人: wangbin
 * 创建时间: 2018年8月23日 下午3:57:07
 */
@Controller
@RequestMapping("person")
public class PersonController {
	
	/**
	 * @Title: indexPerson
	 * @Description: 跳转到人员信息管理首页面
	 * @Time: 2018年8月23日 下午4:12:18
	 * @author: wangbin
	 * @param model 传递给首页面的数据
	 * @return 人员信息管理首页面地址
	 */
	@RequestMapping("/index")
	public ModelAndView indexPerson(String pno) {
		List<PersonEntity> perlist = new ArrayList<PersonEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		perlist = personDao.getAllPerson();
		if(perlist.size()>0) {
			for(PersonEntity person: perlist) {
				String dept_id = person.getDept_id();
				DepartmentEntity dept = deptDao.getDeptById(dept_id);

				if(dept != null) {
					person.setDept_id(dept.getDept_name());
				}
			}
		}
		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(perlist.size()>0) {
			List<PersonEntity> tenQuestions = new ArrayList<PersonEntity>();
			int count = 0;
			int no = 0;
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<perlist.size();i++) {
				tenQuestions.add(perlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("perlist",tenQuestions);
			model.addAttribute("totalRecords",perlist.size());
			int totalPage = 0;
			if(perlist.size()%10 != 0) {
				totalPage = perlist.size()/10+1;
				
			}else {
				totalPage = perlist.size()/10;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/PersonnelInformation/PersonnelBasicInformationManagement/index",model);

	}
	/**
	 * @Title: detailPerson
	 * @Description: 查看人员详细信息
	 * @Time: 2018年8月24日 上午10:26:12
	 * @author: wangbin
	 * @param per_id 待查看人员身份证号
	 * @return ModelAndView 查看详情页面地址和人员详细信息
	 */
	@RequestMapping("/detail")
	public ModelAndView detailPerson(String per_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		
		PersonEntity person = new PersonEntity();
		PersonEntity data = personDao.getPersonById(per_id);
		if(data != null) {
			person.setPer_name(data.getPer_name());
			person.setPer_id(data.getPer_id());
			person.setPer_gender(data.getPer_gender());
			person.setPer_birthday(data.getPer_birthday());
			person.setPer_nation(data.getPer_nation());
			person.setPer_politics(data.getPer_politics());
			person.setPer_state(data.getPer_state());
			person.setPer_tel(data.getPer_tel());
			person.setPassword(data.getPassword());
			person.setPer_worktime(data.getPer_worktime());
			person.setWork_time(data.getWork_time());
			person.setDept_id(data.getDept_id());
			person.setPos_name(data.getPos_name());
			person.setWork_duty(data.getWork_duty());
			person.setPer_remark(data.getPer_remark());
			
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView("/PersonnelInformation/PersonnelBasicInformationManagement/detail","command",person);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
		
	}
	/**
	 * @Title: editPerson
	 * @Description: 修改人员信息
	 * @Time: 2018年8月24日 上午10:28:57
	 * @author: wangbin
	 * @param per_id 待修改人员身份证号
	 * @return ModelAndView 修改人员信息页面地址和新的人员信息
	 */
	@RequestMapping("/edit")
	public ModelAndView editPerson(String per_id) {
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		
		PersonEntity person = new PersonEntity();
		PersonEntity data = personDao.getPersonById(per_id);
		if(data != null) {
			person.setPer_name(data.getPer_name());
			person.setPer_id(data.getPer_id());
			person.setPer_gender(data.getPer_gender());
			person.setPer_birthday(data.getPer_birthday());
			person.setPer_nation(data.getPer_nation());
			person.setPer_politics(data.getPer_politics());
			person.setPer_state(data.getPer_state());
			person.setPer_tel(data.getPer_tel());
			person.setPassword(data.getPassword());
			person.setPer_worktime(data.getPer_worktime());
			person.setWork_time(data.getWork_time());
			person.setDept_id(data.getDept_id());
			person.setPos_name(data.getPos_name());
			person.setWork_duty(data.getWork_duty());
			person.setPer_remark(data.getPer_remark());
			
			MyBatiesUtil.closeSqlSession();
			System.out.println(person);
			return new ModelAndView("/PersonnelInformation/PersonnelBasicInformationManagement/edit","command",person);
		}else {
			MyBatiesUtil.closeSqlSession();
			return new ModelAndView();
		}
		
	}
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updatePerson(PersonEntity person) {
		generateChange(person);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		boolean flag = personDao.updatePerson(person);
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
	 * @Title: addPerson
	 * @Description: 跳转到添加新人员信息页面
	 * @Time: 2018年8月23日 下午8:27:20
	 * @author: wangbin
	 * @return String 添加新人员信息页面地址
	 */
	@RequestMapping("/add")
	public ModelAndView addPerson() {
		PersonEntity person = new PersonEntity();
		return new ModelAndView("/PersonnelInformation/PersonnelBasicInformationManagement/add","command",person);
		
	}
	/**
	 * @Title: savePerson
	 * @Description: 添加一个新人员信息
	 * @Time: 2018年8月23日 下午8:29:24
	 * @author: wangbin
	 * @param person 待添加的新人员信息
	 * @return Map<String, Object>后台插入新问题是否成功的返回值，返回给前端页面 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, Object> savePerson(PersonEntity person) {
		
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		boolean flag = personDao.insertPerson(person);
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
	 * @Title: searchPerson
	 * @Description: 根据姓名和身份证号查询人员信息
	 * @Time: 2018年8月23日 下午5:29:42
	 * @author: wangbin
	 * @param per_name 待查询人员姓名
	 * @param per_id 待查询人员身份证号
	 * @return Map<String, Object>将查询得到的人员信息作为一个属性值返回给前端页面
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchPerson(String per_name,String per_id) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<PersonEntity> perlist = new ArrayList<PersonEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		perlist = personDao.queryPerson(per_name, per_id);
		MyBatiesUtil.closeSqlSession();
		if(perlist.size()>0) {
			resultMap.put("perlist", perlist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/verify")
	@ResponseBody
	public Map<String, Object> verifyPerson(String per_id) {
		System.out.println(per_id);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		PersonEntity person = personDao.getPersonById(per_id);
		MyBatiesUtil.closeSqlSession();
		if(person != null) {
			resultMap.put("result", "success");
			resultMap.put("person",person);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/getperson")
	@ResponseBody
	public Map<String, Object> getPerson(String per_id) {
		System.out.println(per_id);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		IDepartmentDao departmentDao = session.getMapper(IDepartmentDao.class);
		PersonEntity person = personDao.getPersonById(per_id);
		
		if(person != null) {
			DepartmentEntity departmentEntity = departmentDao.getDeptById(person.getDept_id());
			person.setDept_id(departmentEntity.getDept_name());
			resultMap.put("result", "success");
			resultMap.put("person",person);
			MyBatiesUtil.closeSqlSession();
			return resultMap;
		}else {
			MyBatiesUtil.closeSqlSession();
			return resultMap;
		}	
	}
	/**
	 * @Title: deletePerson
	 * @Description: 根据身份证号删除人员信息
	 * @Time: 2018年8月23日 下午5:49:21
	 * @author: wangbin
	 * @param per_id 待删除人员身份证号
	 * @return  Map<String, Object>后台删除问题是否成功的返回值，返回给前端页面
	 */
	@RequestMapping("/deleteItem")
	@ResponseBody
	public Map<String, Object> deletePerson(String per_id) {
		System.out.println(per_id+"person controller");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		boolean flag = personDao.deletePerson(per_id);	
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
	 * @Title: deleteSomePerson
	 * @Description: 根据身份证号批量删除人员信息
	 * @Time: 2018年8月23日 下午5:43:50
	 * @author: wangbin
	 * @param ids 待删除问题的编号串
	 * @return Map<String, Object>后台删除问题是否成功的返回值，返回给前端页面
	 */
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
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		
		boolean flag = personDao.deleSomePerson(list);
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
	public void generateChange(PersonEntity newperson) {
		String per_id = newperson.getPer_id();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		IInformationChangeDao changeDao = session.getMapper(IInformationChangeDao.class);
		PersonEntity oldperson = personDao.getPersonById(per_id);
		InformationChangeEntity change = new InformationChangeEntity();
		change.setPer_id(per_id);
		Calendar calendar = Calendar.getInstance();
		String today = String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		change.setChan_time(today);
		change.setPer_name(newperson.getPer_name());
		change.setPos_name(newperson.getPos_name());
		change.setDept_id(newperson.getDept_id());
		boolean isChange = false;
		if(newperson.getPer_state() != oldperson.getPer_state()) {
			isChange = true;
			change.setChan_onpost(oldperson.getPer_state()+" -> "+newperson.getPer_state());
		}
		if(newperson.getDept_id() != oldperson.getDept_id()) {
			isChange = true;
			change.setChan_dep(oldperson.getDept_id()+" -> "+newperson.getDept_id());
		}
		if(newperson.getPos_name() != oldperson.getPos_name()) {
			isChange = true;
			change.setChan_posname(oldperson.getPos_name()+" -> "+newperson.getPos_name());
		}
		if(newperson.getWork_duty() != oldperson.getWork_duty()) {
			isChange = true;
			change.setChan_workduty(oldperson.getWork_duty()+" -> "+newperson.getWork_duty());
		}
		if(newperson.getPer_tel() != oldperson.getPer_tel()) {
			isChange = true;
			change.setChan_tel(oldperson.getPer_tel()+" -> "+newperson.getPer_tel());
		}
		if(isChange) {
			changeDao.insertChange(change);
		}
		System.out.println(change);
		session.commit();
		MyBatiesUtil.closeSqlSession();
	}
	@ModelAttribute("nationList")
	public List<String> getNationList(){
		List<String> nationList = new ArrayList<>();
		nationList.add("汉族");
		nationList.add("壮族");
		nationList.add("维吾尔族");
		nationList.add("回族");
		return nationList;
	}
	@ModelAttribute("politicsList")
	public List<String> getPoliticsList(){
		List<String> politicsList = new ArrayList<>();
		politicsList.add("党员");
		politicsList.add("预备党员");
		politicsList.add("共青团员");
		politicsList.add("其他");
		return politicsList;
	}
	
	/**
	 * @Title: getDepartmentList
	 * @Description:  获取组织机构列表供添加新人员信息时选择
	 * @Time: 2018年8月24日 上午9:23:19
	 * @author: wangbin
	 * @return Map<String, Object>将获取的数据返回给前端
	 */ 
	@ModelAttribute("deptList")
	public Map<String, String> getDepartmentList(){
		Map<String, String> deptList = new HashMap<String, String>();
		List<DepartmentEntity> departmentList = new ArrayList<DepartmentEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		departmentList = deptDao.getAllDepartment();
		MyBatiesUtil.closeSqlSession();
		if(departmentList.size()>0) {
			for(DepartmentEntity dept: departmentList) {
				deptList.put(dept.getDept_id(), dept.getDept_name());
			}
		}
		return deptList;
	}
}
