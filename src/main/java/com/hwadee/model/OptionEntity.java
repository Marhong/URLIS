/**
 * 
 */
package com.hwadee.model;

/**
 * 项目名称: URLIS
 * 类名称: OptionEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月13日 上午11:39:07
 */
public class OptionEntity {

	private int id;
	private String name;
	private int qu_id;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OptionEntity [id=" + id + ", name=" + name + "]";
	}
	
}
