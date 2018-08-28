/**
 * 
 */
package com.hwadee.model;

import java.util.Date;

/**
 * 项目名称: URLIS
 * 类名称: PersonEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:29:47
 */
public class PersonEntity {

	private String per_id; // 身份证号
	private String dept_id; // 单位编号 
	private String per_name; // 姓名
	private String per_gender; // 性别
	private String per_birthday; // 出生日期
	private String per_nation; // 民族
	private String per_politics; // 政治面貌
	private String per_worktime; // 参加工作时间
	private String per_tel; // 联系电话
	private String per_state; // 在值状态
	private String per_remark; // 备注
	private String work_time; // 任职时间
	private String work_duty; // 工作职责
	private String password; // 密码
	private String pos_name; // 职务名称
	/**
	 * @return the per_id
	 */
	public String getPer_id() {
		return per_id;
	}
	/**
	 * @param per_id the per_id to set
	 */
	public void setPer_id(String per_id) {
		this.per_id = per_id;
	}
	/**
	 * @return the dept_id
	 */
	public String getDept_id() {
		return dept_id;
	}
	/**
	 * @param dept_id the dept_id to set
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	/**
	 * @return the per_name
	 */
	public String getPer_name() {
		return per_name;
	}
	/**
	 * @param per_name the per_name to set
	 */
	public void setPer_name(String per_name) {
		this.per_name = per_name;
	}
	/**
	 * @return the per_gender
	 */
	public String getPer_gender() {
		return per_gender;
	}
	/**
	 * @param per_gender the per_gender to set
	 */
	public void setPer_gender(String per_gender) {
		this.per_gender = per_gender;
	}
	/**
	 * @return the per_birthday
	 */
	public String getPer_birthday() {
		return per_birthday;
	}
	/**
	 * @param per_birthday the per_birthday to set
	 */
	public void setPer_birthday(String per_birthday) {
		this.per_birthday = per_birthday;
	}
	/**
	 * @return the per_nation
	 */
	public String getPer_nation() {
		return per_nation;
	}
	/**
	 * @param per_nation the per_nation to set
	 */
	public void setPer_nation(String per_nation) {
		this.per_nation = per_nation;
	}
	/**
	 * @return the per_politics
	 */
	public String getPer_politics() {
		return per_politics;
	}
	/**
	 * @param per_politics the per_politics to set
	 */
	public void setPer_politics(String per_politics) {
		this.per_politics = per_politics;
	}
	/**
	 * @return the per_worktime
	 */
	public String getPer_worktime() {
		return per_worktime;
	}
	/**
	 * @param per_worktime the per_worktime to set
	 */
	public void setPer_worktime(String per_worktime) {
		this.per_worktime = per_worktime;
	}
	/**
	 * @return the per_tel
	 */
	public String getPer_tel() {
		return per_tel;
	}
	/**
	 * @param per_tel the per_tel to set
	 */
	public void setPer_tel(String per_tel) {
		this.per_tel = per_tel;
	}
	/**
	 * @return the per_state
	 */
	public String getPer_state() {
		return per_state;
	}
	/**
	 * @param per_state the per_state to set
	 */
	public void setPer_state(String per_state) {
		this.per_state = per_state;
	}
	/**
	 * @return the per_remark
	 */
	public String getPer_remark() {
		return per_remark;
	}
	/**
	 * @param per_remark the per_remark to set
	 */
	public void setPer_remark(String per_remark) {
		this.per_remark = per_remark;
	}
	/**
	 * @return the work_time
	 */
	public String getWork_time() {
		return work_time;
	}
	/**
	 * @param work_time the work_time to set
	 */
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	/**
	 * @return the work_duty
	 */
	public String getWork_duty() {
		return work_duty;
	}
	/**
	 * @param work_duty the work_duty to set
	 */
	public void setWork_duty(String work_duty) {
		this.work_duty = work_duty;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the pos_name
	 */
	public String getPos_name() {
		return pos_name;
	}
	/**
	 * @param pos_name the pos_name to set
	 */
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonEntity [per_id=" + per_id + ", dept_id=" + dept_id + ", per_name=" + per_name + ", per_gender="
				+ per_gender + ", per_birthday=" + per_birthday + ", per_nation=" + per_nation + ", per_politics="
				+ per_politics + ", per_worktime=" + per_worktime + ", per_tel=" + per_tel + ", per_state=" + per_state
				+ ", per_remark=" + per_remark + ", work_time=" + work_time + ", work_duty=" + work_duty + ", password="
				+ password + ", pos_name=" + pos_name + "]";
	}
	
}
