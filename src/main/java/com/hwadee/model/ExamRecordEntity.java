/**
 * 
 */
package com.hwadee.model;

/**
 * 项目名称: URLIS
 * 类名称: ExamRecord
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:45:41
 */
public class ExamRecordEntity {

	private String per_id; // 身份证号
	private int exam_id; // 考试编号
	private float exam_score; // 考试分数
	private String per_name; // 姓名
	private String exam_name; // 考试名称
	private String dept_name; // 所在部门名称
	private String posname; // 职位名称
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
	 * @return the exam_id
	 */
	public int getExam_id() {
		return exam_id;
	}
	/**
	 * @param exam_id the exam_id to set
	 */
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	/**
	 * @return the exam_score
	 */
	public float getExam_score() {
		return exam_score;
	}
	/**
	 * @param exam_score the exam_score to set
	 */
	public void setExam_score(float exam_score) {
		this.exam_score = exam_score;
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
	 * @return the exam_name
	 */
	public String getExam_name() {
		return exam_name;
	}
	/**
	 * @param exam_name the exam_name to set
	 */
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	/**
	 * @return the dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}
	/**
	 * @param dept_name the dept_name to set
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	/**
	 * @return the posname
	 */
	public String getPosname() {
		return posname;
	}
	/**
	 * @param posname the posname to set
	 */
	public void setPosname(String posname) {
		this.posname = posname;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamRecordEntity [per_id=" + per_id + ", exam_id=" + exam_id + ", exam_socre=" + exam_score
				+ ", per_name=" + per_name + ", exam_name=" + exam_name + ", dept_name=" + dept_name + ", posname="
				+ posname + "]";
	}
	
}
