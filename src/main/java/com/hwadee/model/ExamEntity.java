/**
 * 
 */
package com.hwadee.model;

import java.util.Date;

/**
 * 项目名称: URLIS
 * 类名称: ExamEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:50:20
 */
public class ExamEntity {

	private int exam_id; // 考试编号
	private String paper_id; // 试卷编号
	private String exam_name; // 考试名称
	private String exam_datetime; // 考试日期;
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
	 * @return the paper_id
	 */
	public String getPaper_id() {
		return paper_id;
	}
	/**
	 * @param paper_id the paper_id to set
	 */
	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
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
	 * @return the exam_datetime
	 */
	public String getExam_datetime() {
		return exam_datetime;
	}
	/**
	 * @param exam_datetime the exam_datetime to set
	 */
	public void setExam_datetime(String exam_datetime) {
		this.exam_datetime = exam_datetime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamEntity [exam_id=" + exam_id + ", paper_id=" + paper_id + ", exam_name=" + exam_name
				+ ", exam_datetime=" + exam_datetime + "]";
	}
	
}
