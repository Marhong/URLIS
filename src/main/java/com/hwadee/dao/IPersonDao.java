/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwadee.model.PersonEntity;

/**
 * 项目名称: URLIS
 * 类名称: PersonDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:01:01
 */
public interface IPersonDao {

	/**
	 * @Title: insertPerson
	 * @Description: 插入一个员工
	 * @Time: 2018年8月11日 上午10:07:44
	 * @author: wangbin
	 * @param perEntity 带插入的员工实体
	 * @return true:插入成功 false:插入失败
	 */
	public boolean insertPerson(PersonEntity perEntity);
	/**
	 * @Title: deletePerson
	 * @Description:  删除一个员工
	 * @Time: 2018年8月11日 上午10:08:30
	 * @author: wangbin
	 * @param per_id 待删除员工的编号
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deletePerson(String per_id);
	/**
	 * @Title: deleSomePerson
	 * @Description:  批量删除人员信息
	 * @Time: 2018年8月23日 下午5:39:04
	 * @author: wangbin
	 * @param ids 待删除人员身份证号串
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deleSomePerson(List<String> list);
	/**
	 * @Title: updatePerson
	 * @Description: 更新一个员工信息
	 * @Time: 2018年8月11日 上午10:08:58
	 * @author: wangbin
	 * @param per_id 待更新员工编号
	 * @param perEntity 修改后的员工实体
	 * @return true:修改成功 false:修改失败
	 */
	public boolean updatePerson(PersonEntity perEntity);
	/**
	 * @Title: queryPerson
	 * @Description:  查询员工
	 * @Time: 2018年8月11日 上午10:09:40
	 * @author: wangbin
	 * @param per_name 待查询员工姓名
	 * @param per_id 待查询员工身份证号
	 * @return List<PersonEntity>查询得到的员工列表
	 */
	public List<PersonEntity> queryPerson(String per_name,String per_id);
	/**
	 * @Title: seePerson
	 * @Description: 查看一个员工具体信息
	 * @Time: 2018年8月11日 上午10:10:35
	 * @author: wangbin
	 * @param per_id 待查看员工身份证号
	 * @return PersonEntity 根据身份证号查询得到的员工
	 */
	public PersonEntity seePerson(String per_id);
	/**
	 * @Title: getAllPerson
	 * @Description: 获取所有人员信息
	 * @Time: 2018年8月23日 下午3:59:27
	 * @author: wangbin
	 * @return List<PersonEntity>获取到的人员信息
	 */
	public List<PersonEntity> getAllPerson();
	/**
	 * @Title: getPersonById
	 * @Description: 通过身份证号查询人员信息
	 * @Time: 2018年8月24日 上午10:21:33
	 * @author: wangbin
	 * @param per_id 待查询的身份证号
	 * @return PersonEntity查询得到的人员信息
	 */
	public PersonEntity getPersonById(String per_id);
}
