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
			if(pno != null && !pno.equals("")) {
				no= Integer.parseInt(pno)-1;
			}else {
				no=0;
			}
			 
			for(int i=(10*no);i<attnlist.size();i++) {
				tenQuestions.add(attnlist.get(i));
				count++;
				if(count == 10) {
					break;
				}
			}
			model.addAttribute("attnlist",tenQuestions);
			model.addAttribute("totalRecords",attnlist.size());
			int totalPage = 0;
			if(attnlist.size()%10 != 0) {
				totalPage = attnlist.size()/10+1;
				
			}else {
				totalPage = attnlist.size()/10;
			
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
	public ModelAndView addaAssessment() {

		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/add");
	}
	@RequestMapping("/endwork")
	public ModelAndView endWork() {

		return new ModelAndView("/JobPerformanceAssessment/AttendanceManagement/endwork");
	}
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> saveAssessment(String  per_id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao attendanceDao = session.getMapper(IAttendanceDao.class);
		IPersonDao personDao = session.getMapper(IPersonDao.class);
		PersonEntity person = personDao.getPersonById(per_id);
		if (person != null) {
			String per_name = person.getPer_name();
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
			String start_time = "",hour="",min="",sec="";
			hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			min = String.valueOf(calendar.get(Calendar.MINUTE));
			sec = String.valueOf(calendar.get(Calendar.SECOND));
			if(compTime(hour+":"+min+":"+sec, "8:0:0")) {
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
			if(oldAttendanceEntity != null) {
				oldAttendanceEntity.setAttn_status(attn_status);
				oldAttendanceEntity.setStart_time(start_time);
				 flag = attendanceDao.updateWholeAttendance(oldAttendanceEntity);
			}else {
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
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String, Object> updateAttendance(String per_id) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SqlSession session = MyBatiesUtil.getSqlSession();
		IAttendanceDao attendanceDao = session.getMapper(IAttendanceDao.class);
		
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
		AttendanceEntity attendanceEntity = attendanceDao.getAttnByPerIdAndDate(per_id, attn_date);
		String attn_status = "";
		if(attendanceEntity != null) {
			String end_time = "",hour="",min="",sec="";
			hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			min = String.valueOf(calendar.get(Calendar.MINUTE));
			sec = String.valueOf(calendar.get(Calendar.SECOND));
			if(!compTime(hour+":"+min+":"+sec, "18:0:0")) {
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
