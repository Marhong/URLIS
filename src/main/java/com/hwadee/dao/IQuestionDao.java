/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.OptionEntity;
import com.hwadee.model.QuestionEntity;

/**
 * 项目名称: URLIS
 * 类名称: QuestionDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午9:13:01
 */
public interface IQuestionDao {

	/**
	 * @Title: insertQuestion
	 * @Description: 往数据库中插入一个问题
	 * @Time: 2018年8月11日 上午9:13:47
	 * @author: wangbin
	 * @param quEntity 封装好的问题实体
	 * @return true:插入成功 false:插入失败
	 */
	public boolean insertQuestion(QuestionEntity quEntity);
	
	/**
	 * @Title: deleteQuestion
	 * @Description: 从数据中删除一个问题
	 * @Time: 2018年8月11日 上午9:15:01
	 * @author: wangbin
	 * @param qu_id 待删除问题的编号
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deleteQuestion(int qu_id);

	/**
	 * @Title: deleteSomeQuestions
	 * @Description: 批量删除多个问题
	 * @Time: 2018年8月22日 下午7:16:46
	 * @author: wangbin
	 * @param list 待删除问题的编号队列
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deleteSomeQuestions(List<Integer> list);
	

	/**
	 * @Title: updateQuestion
	 * @Description: 修改题目
	 * @Time: 2018年8月22日 下午3:15:14
	 * @author: wangbin
	 * @param quEntity 新的题目
	 * @return
	 */
	boolean updateQuestion(QuestionEntity quEntity);

	/**
	 * @Title: getQuestionByContent
	 * @Description: 根据题目内容查询某一个问题
	 * @Time: 2018年8月13日 上午11:18:46
	 * @author: wangbin
	 * @param qu_content 待查询的题目内容
	 * @param qu_type 问题类型
	 * @return List<QuestionEntity>查询得到的问题列表
	 */
	public List<QuestionEntity> getQuestionsByContentAndType(String qu_content,String qu_type);
	/**
	 * @Title: getQuestionById
	 * @Description: 通过问题编号查找问题
	 * @Time: 2018年8月22日 下午3:30:43
	 * @author: wangbin
	 * @param qu_id 待查找问题的编号
	 * @return QuestionEntity查找得到的问题
	 */
	public QuestionEntity getQuestionById(int qu_id);
	/**
	 * @Title: getAllQuestion
	 * @Description: 获取所有的问题
	 * @Time: 2018年8月22日 下午2:38:20
	 * @author: wangbin
	 * @return List<QuestionEntity>查询得到的所有问题列表
	 */
	public List<QuestionEntity> getAllQuestion();
}
