/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.AttendanceEntity;


/**
 * 项目名称: URLIS
 * 类名称: AttendanceDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午11:06:45
 */
public interface IAttendanceDao {

	/**
	 * @Title: insertAttendance
	 * @Description: 添加一条考勤
	 * @Time: 2018年8月11日 上午11:10:50
	 * @author: wangbin
	 * @param attendanceEntity 待添加的考勤实体
	 * @return true:成功 false:失败
	 */
	public boolean insertAttendance(AttendanceEntity attendanceEntity);
	
	/**
	 * @Title: deleteAttendance
	 * @Description: 删除一条考勤记录
	 * @Time: 2018年8月11日 上午11:11:25
	 * @author: wangbin
	 * @param attn_id 待删除考勤记录编号
	 * @return true:成功 false:失败
	 */
	public boolean deleteAttendance(int attn_id);
	/**
	 * @Title: updateAttendance
	 * @Description: 更新一条考勤记录
	 * @Time: 2018年8月11日 上午11:11:52
	 * @author: wangbin
	 * @param attendanceEntity 更新后的考勤记录
	 * @return true:成功 false:失败
	 */
	public boolean updateAttendance(AttendanceEntity attendanceEntity);
	public boolean updateWholeAttendance(AttendanceEntity attence);
	/**
	 * @Title: queryAttendance
	 * @Description: 查询某一个人的考勤记录
	 * @Time: 2018年8月11日 上午11:12:18
	 * @author: wangbin
	 * @param per_id 身份证号
	 * @return List<AttendanceEntity>查询得到的考勤记录列表
	 */
	public List<AttendanceEntity> queryAttendance(String per_id);
	/**
	 * @Title: getAllAttendance
	 * @Description: 获取所有的考勤记录
	 * @Time: 2018年8月27日 下午8:03:02
	 * @author: wangbin
	 * @return
	 */
	public List<AttendanceEntity> getAllAttendance();
	/**
	 * @Title: getAttnByNameAndId
	 * @Description: 通过姓名或者身份证获取考勤记录
	 * @Time: 2018年8月27日 下午8:03:16
	 * @author: wangbin
	 * @param per_name 姓名
	 * @param per_id 身份证
	 * @return
	 */
	public List<AttendanceEntity> getAttnByNameAndId(String per_name,String per_id);
	/**
	 * @Title: getAttnsByIdAndYearMon
	 * @Description: 通过身份证和日期获取考勤记录
	 * @Time: 2018年8月27日 下午8:04:17
	 * @author: wangbin
	 * @param per_id 身份证
	 * @param yearmon 日期， 年-月 ，2018-08
	 * @return
	 */
	public List<AttendanceEntity> getAttnsByIdAndYearMon(String per_id,String yearmon);
	public AttendanceEntity getAttnByPerIdAndDate(String per_id,String date);
	
}
