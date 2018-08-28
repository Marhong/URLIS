/**
 * 
 */
package com.hwadee.model;

import java.util.Date;

/**
 * 项目名称: URLIS
 * 类名称: AssessmentEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:58:47
 */
public class AssessmentEntity {

	private int asse_id; // 考核编号
	private String per_id; // 身份证号
	private String asse_date; // 考核日期
	private float asse_score; // 考核分数
	private String per_name; // 姓名
	/**
	 * @return the asse_id
	 */
	public int getAsse_id() {
		return asse_id;
	}
	/**
	 * @param asse_id the asse_id to set
	 */
	public void setAsse_id(int asse_id) {
		this.asse_id = asse_id;
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
	 * @return the asse_date
	 */
	public String getAsse_date() {
		return asse_date;
	}
	/**
	 * @param asse_date the asse_date to set
	 */
	public void setAsse_date(String asse_date) {
		this.asse_date = asse_date;
	}
	/**
	 * @return the asse_score
	 */
	public float getAsse_score() {
		return asse_score;
	}
	/**
	 * @param asse_score the asse_score to set
	 */
	public void setAsse_score(float asse_score) {
		this.asse_score = asse_score;
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
		return "AssessmentEntity [asse_id=" + asse_id + ", per_id=" + per_id + ", asse_date=" + asse_date
				+ ", asse_score=" + asse_score + ", per_name=" + per_name + "]";
	}
	
}
