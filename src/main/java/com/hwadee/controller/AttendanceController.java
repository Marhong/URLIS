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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hwadee.dao.IAssessmentDao;
import com.hwadee.dao.IAttendanceDao;
import com.hwadee.dao.IDepartmentDao;
import com.hwadee.dao.IPersonDao;
import com.hwadee.model.AssessmentEntity;
import com.hwadee.model.AttendanceEntity;
import com.hwadee.model.DepartmentEntity;
import com.hwadee.model.PersonEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 项目名称: URLIS
 * 类名称: AttendanceController
 * 创建人: wangbin
 * 创建时间: 2018年8月24日 下午9:40:37
 */
@Controller
@RequestMapping("attendance")
public class AttendanceController {
	
	@RequestMapping("/index")
	public ModelAndView indexAttendance(String pno) {
		List<AttendanceEntity> attnlist = new ArrayList<AttendanceEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao attnDao = session.getMapper(IAttendanceDao.class);
		attnlist = attnDao.getAllAttendance();

		MyBatiesUtil.closeSqlSession();
		ModelMap model = new ModelMap();
		if(attnlist.size()>0) {
			List<AttendanceEntity> tenQuestions = new ArrayList<AttendanceEntity>();
			int count = 0;
			int no = 0;
			// 因为数据分页，所以每次最多传10条数据
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			// 根据页码更改取数据的起点
			for(int i=(StaticNumber.PAGE_ITEMS*no);i<attnlist.size();i++) {
				tenQuestions.add(attnlist.get(i));
				count++;
				if(count == StaticNumber.PAGE_ITEMS) {
					break;
				}
			}
			model.addAttribute("attnlist",tenQuestions);
			model.addAttribute("totalRecords",attnlist.size());
			int totalPage = 0;
			// 设定每页展示10条数据，由此计算总共应有多有页
			if(attnlist.size()%StaticNumber.PAGE_ITEMS != 0) {
				totalPage = attnlist.size()/StaticNumber.PAGE_ITEMS+1;
				
			}else {
				totalPage = attnlist.size()/StaticNumber.PAGE_ITEMS;
			
			}
			
			model.addAttribute("totalPage",totalPage);
		}
		
		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/index",model);
		

	}
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> searchAttn(String per_name,String per_id) {
		System.out.println(per_name+" "+per_id);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<AttendanceEntity> attnlist = new ArrayList<AttendanceEntity>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao deptDao = session.getMapper(IAttendanceDao.class);
		attnlist = deptDao.getAttnByNameAndId(per_name, per_id);
		MyBatiesUtil.closeSqlSession();
		if(attnlist.size()>0) {
			resultMap.put("attnlist", attnlist);
			return resultMap;
		}else {
			return resultMap;
		}	
	}
	@RequestMapping("/startwork")
	public ModelAndView startWork() {

		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/add");
	}
	@RequestMapping("/add")
	public ModelAndView addaAttendance() {

		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/add");
	}
	@RequestMapping("/endwork")
	public ModelAndView endWork() {

		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/endwork");
	}
	/**
	 * @Title: saveAttendance
	 * @Description: 上班打卡
	 * @Time: 2018年8月29日 上午11:47:14
	 * @author: wangbin
	 * @param per_id 身份证
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> saveAttendance(String  per_id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao attendanceDao = session.getMapper(IAttendanceDao.class);
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		PersonEntity person = personDao.getPersonById(per_id);
		if (person != null) {
			String per_name = person.getPer_name();
			// 获取当前的年月日 2018-08-25
			Calendar calendar = Calendar.getInstance();
			String attn_date = String.valueOf(calendar.get(Calendar.YEAR))+"-";
			String month=String.valueOf(calendar.get(Calendar.MONTH)+1);
			String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
			if(calendar.get(Calendar.MONTH)+1<10) {
				month = "0"+month;	
			}
			if(calendar.get(Calendar.DAY_OF_MONTH)<10) {
				day =  "0"+day;	
			}
			attn_date += month +"-"+day;
			String attn_status ="";
			// 获取当前的时分秒 08:15:20
			String start_time = "",hour="",min="",sec="";
			hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			min = String.valueOf(calendar.get(Calendar.MINUTE));
			sec = String.valueOf(calendar.get(Calendar.SECOND));
			// 如果当前时间比规定上班时间晚，则为迟到
			if(compTime(hour+":"+min+":"+sec, StaticNumber.START_WORK)) {
				attn_status = "迟到";
			}else {
				attn_status = "正常";
			}
			if(calendar.get(Calendar.HOUR_OF_DAY)<10) {
				hour = "0"+hour;	
			}
			if(calendar.get(Calendar.MINUTE)<10) {
				min =  "0"+min;	
			}
			if(calendar.get(Calendar.SECOND)<10) {
				sec = "0"+sec;	
			}
			start_time = hour+":"+min+":"+sec;
			String end_time = "00:00:00";
			boolean flag = false;
			AttendanceEntity oldAttendanceEntity = attendanceDao.getAttnByPerIdAndDate(per_id, attn_date);
			// 判断当前打卡人员是否是重复打卡
			if(oldAttendanceEntity != null) {
				// 如果是，则更新打卡时间
				oldAttendanceEntity.setAttn_status(attn_status);
				oldAttendanceEntity.setStart_time(start_time);
				 flag = attendanceDao.updateWholeAttendance(oldAttendanceEntity);
			}else {
				// 如果不是则添加该人员的打开记录
				AttendanceEntity attendanceEntity = new AttendanceEntity();
				attendanceEntity.setAttn_date(attn_date);
				attendanceEntity.setAttn_status(attn_status);
				attendanceEntity.setEnd_time(end_time);
				attendanceEntity.setPer_id(per_id);
				attendanceEntity.setPer_name(per_name);
				attendanceEntity.setStart_time(start_time);
				 flag = attendanceDao.insertAttendance(attendanceEntity);
			}

			if(flag) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "false");
			}

			return resultMap;
		} 
		session.commit();
		MyBatiesUtil.closeSqlSession();
		return resultMap;
	}
	/**
	 * @Title: updateAttendance
	 * @Description: 下班打卡
	 * @Time: 2018年8月29日 上午11:47:29
	 * @author: wangbin
	 * @param per_id 身份证
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updateAttendance(String per_id) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao attendanceDao = session.getMapper(IAttendanceDao.class);
		
		Calendar calendar = Calendar.getInstance();
		// 当前年月日 2018-08-25
		String attn_date = String.valueOf(calendar.get(Calendar.YEAR))+"-";
		String month=String.valueOf(calendar.get(Calendar.MONTH)+1);
		String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if(calendar.get(Calendar.MONTH)+1<10) {
			month = "0"+month;	
		}
		if(calendar.get(Calendar.DAY_OF_MONTH)<10) {
			day =  "0"+day;	
		}
		attn_date += month +"-"+day;
		AttendanceEntity attendanceEntity = attendanceDao.getAttnByPerIdAndDate(per_id, attn_date);
		String attn_status = "";
		if(attendanceEntity != null) {
			// 当前时分秒 18:10:20
			String end_time = "",hour="",min="",sec="";
			hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			min = String.valueOf(calendar.get(Calendar.MINUTE));
			sec = String.valueOf(calendar.get(Calendar.SECOND));
			// 如果当前时间早于规定下班时间则为早退
			if(!compTime(hour+":"+min+":"+sec, StaticNumber.END_WORK)) {
				attn_status = "早退";
			}else {
				attn_status = "正常";
			}
			if(calendar.get(Calendar.HOUR_OF_DAY)<10) {
				hour = "0"+hour;	
			}
			if(calendar.get(Calendar.MINUTE)<10) {
				min =  "0"+min;	
			}
			if(calendar.get(Calendar.SECOND)<10) {
				sec = "0"+sec;	
			}
			end_time = hour+":"+min+":"+sec;
			attendanceEntity.setAttn_status(attn_status);
			attendanceEntity.setEnd_time(end_time);
			boolean flag = attendanceDao.updateAttendance(attendanceEntity);
			if (flag) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "false");
			}
		}
		session.commit();
		MyBatiesUtil.closeSqlSession();
		
		return resultMap;
	}
	/**
	 * @Title: compTime
	 * @Description: 比较两个时间的早晚 
	 * @Time: 2018年8月29日 上午11:49:04
	 * @author: wangbin
	 * @param s1 18:20:30
	 * @param s2 18:30:30
	 * @return 如果s2比s1早则返回true
	 */
	public  boolean compTime(String s1,String s2){
		try {
			if (s1.indexOf(":")<0||s2.indexOf(":")<0) {
				System.out.println("格式不正确");
			}else{
				String[]array1 = s1.split(":");
				int total1 = Integer.valueOf(array1[0])*3600+Integer.valueOf(array1[1])*60+Integer.valueOf(array1[2]);
				String[]array2 = s2.split(":");
				int total2 = Integer.valueOf(array2[0])*3600+Integer.valueOf(array2[1])*60+Integer.valueOf(array2[2]);
				return total1-total2>0?true:false;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
 
	}

}
