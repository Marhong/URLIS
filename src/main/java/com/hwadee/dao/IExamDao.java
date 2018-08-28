/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.ExamEntity;

/**
 * 项目名称: URLIS
 * 类名称: ExamDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:45:15
 */
public interface IExamDao {

	/**
	 * @Title: insertExam
	 * @Description: 添加一场考试
	 * @Time: 2018年8月11日 上午10:50:12
	 * @author: wangbin
	 * @param examEntity 待添加的考试试题
	 * @return true:成功 false:失败
	 */
	public boolean insertExam(ExamEntity examEntity);
	/**
	 * @Title: deleteExam
	 * @Description: 删除一场考试
	 * @Time: 2018年8月11日 上午10:50:49
	 * @author: wangbin
	 * @param exam_id 待删除考试的编号
	 * @return true:成功 false:失败
	 */
	public boolean deleteExam(int exam_id);

	/**
	 * @Title: getExamsByName
	 * @Description: 根据考试名称查询所有考试
	 * @Time: 2018年8月11日 上午10:51:51
	 * @author: wangbin
	 * @param exam_name 考试名称
	 * @return List<ExamEntity>查询得到的考试列表
	 */
	public List<ExamEntity> getExamsByName(String exam_name);
	/**
	 * @Title: getExamById
	 * @Description: 通过考试编号查询考试
	 * @Time: 2018年8月27日 下午1:59:07
	 * @author: wangbin
	 * @param id 待查询考试编号
	 * @return
	 */
	public ExamEntity getExamById(int id);
	/**
	 * @Title: getAllExams
	 * @Description: 获取所有的考试
	 * @Time: 2018年8月27日 上午8:18:06
	 * @author: wangbin
	 * @return
	 */
	public List<ExamEntity> getAllExams();
	/**
	 * @Title: deleteSomeExams
	 * @Description: 批量删除考试
	 * @Time: 2018年8月27日 上午8:18:20
	 * @author: wangbin
	 * @param list 待删除考试编号集合
	 * @return
	 */
	public boolean deleteSomeExams(List<Integer> list);
	public List<ExamEntity> getExamsByYearMon(String yearmon);
}
