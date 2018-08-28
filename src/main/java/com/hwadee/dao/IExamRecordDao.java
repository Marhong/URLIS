/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwadee.model.ExamRecordEntity;

/**
 * 项目名称: URLIS
 * 类名称: ExamRecordDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:52:46
 */
public interface IExamRecordDao {

	/**
	 * @Title: insertExamRecord
	 * @Description: 添加一条考试记录
	 * @Time: 2018年8月11日 上午10:59:36
	 * @author: wangbin
	 * @param eRecordEntity 待添加的考试记录
	 * @return true:成功 false:失败
	 */
	public boolean insertExamRecord(ExamRecordEntity eRecordEntity);
	/**
	 * @Title: deleteExamRecord
	 * @Description: 删除 一条考试记录
	 * @Time: 2018年8月11日 上午11:00:16
	 * @author: wangbin
	 * @param per_id 身份证号
	 * @param exam_id 考试编号
	 * @return true:成功 false:失败
	 */
	public boolean deleteExamRecord(int exam_id,String per_id);



	/**
	 * @Title: getAllExams
	 * @Description: 获取所有的考试记录
	 * @Time: 2018年8月27日 上午8:48:35
	 * @author: wangbin
	 * @return
	 */
	public List<ExamRecordEntity> getAllExamRecords();
	/**
	 * @Title: getExamsByExamId
	 * @Description: 通过考试编号查询该场考试的所有成绩记录
	 * @Time: 2018年8月27日 下午2:09:45
	 * @author: wangbin
	 * @param exam_id 待查寻的考试编号
	 * @return
	 */
	public List<ExamRecordEntity> getExamsByExamId(int exam_id);
	/**
	 * @Title: getExamsByExamName
	 * @Description: 通过考试名称或姓名查询成绩记录
	 * @Time: 2018年8月27日 下午2:10:17
	 * @author: wangbin
	 * @param exam_name 待查寻的考试名称
	 * @param per_name 待查寻的姓名
	 * @return
	 */
	public List<ExamRecordEntity> getExamsByExamNameOrPerName(String exam_name,String per_name);
	/**
	 * @Title: getExamsByPerId
	 * @Description: 通过身份证获取某一个人的所有成绩记录
	 * @Time: 2018年8月27日 下午7:40:34
	 * @author: wangbin
	 * @param per_id 带查询身份证
	 * @return
	 */
	public List<ExamRecordEntity> getRecordssByPerId(String per_id);
}
