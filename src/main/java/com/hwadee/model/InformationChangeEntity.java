/**
 * 
 */
package com.hwadee.model;

import java.util.Date;

/**
 * 项目名称: URLIS
 * 类名称: InformationChangeEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:40:52
 */
public class InformationChangeEntity {

	private String per_id; // 身份证号
	private String chan_time; // 变更时间
	private String chan_dep; // 单位编号变更
	private String chan_tel; // 电话变更
	private String chan_onpost; // 在值状态变更
	private String chan_workduty; // 工作职责变更
	private String chan_posname; // 工作职务变更
	private String per_name; // 姓名
	private String pos_name; // 现任职务名称
	private String dept_id; // 现任单位编号
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
	 * @return the chan_time
	 */
	public String getChan_time() {
		return chan_time;
	}
	/**
	 * @param chan_time the chan_time to set
	 */
	public void setChan_time(String chan_time) {
		this.chan_time = chan_time;
	}
	/**
	 * @return the chan_dep
	 */
	public String getChan_dep() {
		return chan_dep;
	}
	/**
	 * @param chan_dep the chan_dep to set
	 */
	public void setChan_dep(String chan_dep) {
		this.chan_dep = chan_dep;
	}
	/**
	 * @return the chan_tel
	 */
	public String getChan_tel() {
		return chan_tel;
	}
	/**
	 * @param chan_tel the chan_tel to set
	 */
	public void setChan_tel(String chan_tel) {
		this.chan_tel = chan_tel;
	}
	/**
	 * @return the chan_onpost
	 */
	public String getChan_onpost() {
		return chan_onpost;
	}
	/**
	 * @param chan_onpost the chan_onpost to set
	 */
	public void setChan_onpost(String chan_onpost) {
		this.chan_onpost = chan_onpost;
	}
	/**
	 * @return the chan_workduty
	 */
	public String getChan_workduty() {
		return chan_workduty;
	}
	/**
	 * @param chan_workduty the chan_workduty to set
	 */
	public void setChan_workduty(String chan_workduty) {
		this.chan_workduty = chan_workduty;
	}
	/**
	 * @return the chan_posname
	 */
	public String getChan_posname() {
		return chan_posname;
	}
	/**
	 * @param chan_posname the chan_posname to set
	 */
	public void setChan_posname(String chan_posname) {
		this.chan_posname = chan_posname;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InformationChangeEntity [per_id=" + per_id + ", chan_time=" + chan_time + ", chan_dep=" + chan_dep
				+ ", chan_tel=" + chan_tel + ", chan_onpost=" + chan_onpost + ", chan_workduty=" + chan_workduty
				+ ", chan_posname=" + chan_posname + ", per_name=" + per_name + ", pos_name=" + pos_name + ", dept_id="
				+ dept_id + "]";
	}
	
	
}
