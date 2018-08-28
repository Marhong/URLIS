/**
 * 
 */
package com.hwadee.model;

import java.util.List;

/**
 * 项目名称: URLIS
 * 类名称: QuestionEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:07:57
 */
public class QuestionEntity {


	private int qu_id; // 题目编号
	private int qu_score; // 题目分值
	private String qu_type; // 题目类别
	private String qu_content; // 题目内容
	private String qu_option; // 题目选项
	private String qu_answer; // 题目答案
	
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
	/**
	 * @return the qu_socre
	 */
	public int getQu_score() {
		return qu_score;
	}
	/**
	 * @param qu_socre the qu_socre to set
	 */
	public void setQu_score(int qu_socre) {
		this.qu_score = qu_socre;
	}
	/**
	 * @return the qu_type
	 */
	public String getQu_type() {
		return qu_type;
	}
	/**
	 * @param qu_type the qu_type to set
	 */
	public void setQu_type(String qu_type) {
		this.qu_type = qu_type;
	}
	/**
	 * @return the qu_content
	 */
	public String getQu_content() {
		return qu_content;
	}
	/**
	 * @param qu_content the qu_content to set
	 */
	public void setQu_content(String qu_content) {
		this.qu_content = qu_content;
	}
	/**
	 * @return the qu_option
	 */
	public String getQu_option() {
		return qu_option;
	}
	/**
	 * @param qu_option the qu_option to set
	 */
	public void setQu_option(String qu_option) {
		this.qu_option = qu_option;
	}
	/**
	 * @return the qu_answer
	 */
	public String getQu_answer() {
		return qu_answer;
	}
	/**
	 * @param qu_answer the qu_answer to set
	 */
	public void setQu_answer(String qu_answer) {
		this.qu_answer = qu_answer;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionEntity [qu_id=" + qu_id + ", qu_score=" + qu_score + ", qu_type=" + qu_type + ", qu_content="
				+ qu_content + ", qu_option=" + qu_option + ", qu_answer=" + qu_answer + "]";
	}
	
}
