/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.DepartmentEntity;

/**
 * 项目名称: URLIS
 * 类名称: DepartmentDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午11:01:49
 */
public interface IDepartmentDao {

	/**
	 * @Title: insertDepartment
	 * @Description: 添加一个组织机构
	 * @Time: 2018年8月11日 上午11:04:23
	 * @author: wangbin
	 * @param deptEntity 待添加的组织机构
	 * @return true:成功 false:失败
	 */
	public boolean insertDepartment(DepartmentEntity deptEntity);
	/**
	 * @Title: updateDepartment
	 * @Description: 更新组织机构信息
	 * @Time: 2018年8月11日 上午11:04:56
	 * @author: wangbin
	 * @param dept_id 组织机构编号
	 * @param deptEntity 修改后的组织机构
	 * @return true:成功 false:失败
	 */
	public boolean updateDepartment(DepartmentEntity deptEntity);
	/**
	 * @Title: deleteDepartment
	 * @Description:删除一个组织机构
	 * @Time: 2018年8月11日 上午11:05:24
	 * @author: wangbin
	 * @param dept_id 待删除组织编号
	 * @return true:成功 false:失败
	 */
	public boolean deleteDepartment(int dept_id);
	/**
	 * @Title: queryDepartment
	 * @Description: 查询组织机构
	 * @Time: 2018年8月11日 上午11:05:50
	 * @author: wangbin
	 * @param dept_name 待查询组织名字
	 * @return List<DepartmentEntity>查询得到的组织列表
	 */
	public List<DepartmentEntity> queryDepartment(String dept_name);
	/**
	 * @Title: getAllDepartment
	 * @Description: 获取所有的部门信息
	 * @Time: 2018年8月23日 下午4:49:00
	 * @author: wangbin
	 * @return List<DepartmentEntity>获取到的部门信息
	 */
	public List<DepartmentEntity> getAllDepartment();
	/**
	 * @Title: getDeptById
	 * @Description: 通过部门编号查询部门
	 * @Time: 2018年8月23日 下午4:49:54
	 * @author: wangbin
	 * @param id 带查询部门编号
	 * @return DepartmentEntity查询得到的部门
	 */
	public DepartmentEntity getDeptById(String id);
	public List<DepartmentEntity> getDeptByName(String name);
}
