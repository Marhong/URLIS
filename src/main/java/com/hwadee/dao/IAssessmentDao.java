/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.AssessmentEntity;

/**
 * 项目名称: URLIS
 * 类名称: AssessmentDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午11:13:06
 */
public interface IAssessmentDao {

	/**
	 * @Title: insertAssessment
	 * @Description: 添加一条考核记录
	 * @Time: 2018年8月11日 上午11:17:17
	 * @author: wangbin
	 * @param assessmentEntity 待添加的考核记录
	 * @return true:成功 false:失败
	 */
	public boolean insertAssessment(AssessmentEntity assessmentEntity);
	/**
	 * @Title: deleteAssessment
	 * @Description: 删除一条考核记录
	 * @Time: 2018年8月11日 上午11:17:55
	 * @author: wangbin
	 * @param asse_id 待删除考核记录编号
	 * @return true:成功 false:失败
	 */
	public boolean deleteAssessment(int asse_id);
	public boolean deleteSomeAssessments(List<Integer> list);
	/**
	 * @Title: updateAssessment
	 * @Description: 更新一条考核记录
	 * @Time: 2018年8月11日 上午11:18:15
	 * @author: wangbin
	 * @param asse_id 待更新考核记录编号
	 * @param assessmentEntity 修改后的考核记录
	 * @return true:成功 false:失败
	 */
	public boolean updateAssessment(int asse_id,AssessmentEntity assessmentEntity);
	/**
	 * @Title: getAllAssessments
	 * @Description: 获取所有考核记录
	 * @Time: 2018年8月11日 上午11:18:41
	 * @author: wangbin
	 * @return List<AssessmentEntity>查询得到的考核记录列表
	 */
	public List<AssessmentEntity> getAllAssessments();
	/**
	 * @Title: getAssessmentsByName
	 * @Description: 通过姓名搜索所有考核记录
	 * @Time: 2018年8月27日 下午5:27:25
	 * @author: wangbin
	 * @param per_name 待搜索的姓名
	 * @return  List<AssessmentEntity>查询得到的考核记录列表
	 */
	public List<AssessmentEntity> getAssessmentsByName(String per_name);
	/**
	 * @Title: getAssessmentById
	 * @Description: 通过考核编号获取考核记录
	 * @Time: 2018年8月28日 上午8:45:13
	 * @author: wangbin
	 * @param asse_id 待查寻的考核编号
	 * @return 
	 */
	public AssessmentEntity getAssessmentById(int asse_id);
	public AssessmentEntity getAssessmentByPerIdAndYearMon(String per_id,String yearmon);
	public List<AssessmentEntity> getAssessmentByYearMon(String yearmon);
}
