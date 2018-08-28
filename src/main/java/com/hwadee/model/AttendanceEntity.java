/**
 * 
 */
package com.hwadee.model;

import java.util.Date;

/**
 * 项目名称: URLIS
 * 类名称: AttendanceEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:53:03
 */
public class AttendanceEntity {

	private int attn_id; // 考勤编号
	private String per_id; // 身份证号
	private String attn_date; // 考勤日期
	private String attn_status; // 考勤状态
	private String start_time; // 上班打卡时间
	private String end_time; // 下班打卡时间
	private String per_name; // 姓名
	/**
	 * @return the attn_id
	 */
	public int getAttn_id() {
		return attn_id;
	}
	/**
	 * @param attn_id the attn_id to set
	 */
	public void setAttn_id(int attn_id) {
		this.attn_id = attn_id;
	}
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
	 * @return the attn_date
	 */
	public String getAttn_date() {
		return attn_date;
	}
	/**
	 * @param attn_date the attn_date to set
	 */
	public void setAttn_date(String attn_date) {
		this.attn_date = attn_date;
	}
	/**
	 * @return the attn_status
	 */
	public String getAttn_status() {
		return attn_status;
	}
	/**
	 * @param attn_status the attn_status to set
	 */
	public void setAttn_status(String attn_status) {
		this.attn_status = attn_status;
	}
	/**
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AttendanceEntity [attn_id=" + attn_id + ", per_id=" + per_id + ", attn_date=" + attn_date
				+ ", attn_status=" + attn_status + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", per_name=" + per_name + "]";
	}
	
}
