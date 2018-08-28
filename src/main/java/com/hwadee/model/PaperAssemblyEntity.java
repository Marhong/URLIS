/**
 * 
 */
package com.hwadee.model;

/**
 * 项目名称: URLIS
 * 类名称: PaperAssemblyEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:36:52
 */
public class PaperAssemblyEntity {

	private String paper_id; // 试卷编号
	private int qu_id; // 题目编号
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
	 * @return the qu_id
	 */
	public int getQu_id() {
		return qu_id;
	}
	/**
	 * @param qu_id the qu_id to set
	 */
	public void setQu_id(int qu_id) {
		this.qu_id = qu_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaperAssemblyEntity [paper_id=" + paper_id + ", qu_id=" + qu_id + "]";
	}
	
	
}
