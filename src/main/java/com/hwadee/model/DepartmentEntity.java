/**
 * 
 */
package com.hwadee.model;

/**
 * 项目名称: URLIS
 * 类名称: DepartmentEntity
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:51:58
 */
public class DepartmentEntity {

	private String dept_id; // 组织机构编号
	private String dept_name; // 组织机构名称
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DepartmentEntity [dept_id=" + dept_id + ", dept_name=" + dept_name + "]";
	}
	
}
