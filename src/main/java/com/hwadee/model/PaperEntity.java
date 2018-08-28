/**
 * 
 */
package com.hwadee.model;

/**
 * 项目名称: URLIS
 * 类名称: PaperEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:38:12
 */
public class PaperEntity {

	private String paper_id; // 试卷编号
	private String paper_name; // 试卷名称
	private String paper_remark; // 试卷备注
	private String paper_score; // 试卷分数
	private String paper_time; // 考试时长
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
	 * @return the paper_name
	 */
	public String getPaper_name() {
		return paper_name;
	}
	/**
	 * @param paper_name the paper_name to set
	 */
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	/**
	 * @return the paper_remark
	 */
	public String getPaper_remark() {
		return paper_remark;
	}
	/**
	 * @param paper_remark the paper_remark to set
	 */
	public void setPaper_remark(String paper_remark) {
		this.paper_remark = paper_remark;
	}
	/**
	 * @return the papaer_score
	 */
	public String getPaper_score() {
		return paper_score;
	}
	/**
	 * @param papaer_score the papaer_score to set
	 */
	public void setPaper_score(String paper_score) {
		this.paper_score = paper_score;
	}
	/**
	 * @return the paper_time
	 */
	public String getPaper_time() {
		return paper_time;
	}
	/**
	 * @param paper_time the paper_time to set
	 */
	public void setPaper_time(String paper_time) {
		this.paper_time = paper_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaperEntity [paper_id=" + paper_id + ", paper_name=" + paper_name + ", paper_remark=" + paper_remark
				+ ", paper_score=" + paper_score + ", paper_time=" + paper_time + "]";
	}
	
}
