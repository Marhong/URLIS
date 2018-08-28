/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.PaperAssemblyEntity;

/**
 * 项目名称: URLIS
 * 类名称: PaperAssemblyDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:29:48
 */
public interface IPaperAssemblyDao {

	/**
	 * @Title: insertPaperAssembly
	 * @Description: 往组卷表中添加一条记录
	 * @Time: 2018年8月11日 上午10:33:26
	 * @author: wangbin
	 * @param pAssemblyEntity 待添加的实体
	 * @return true:添加成功 false:添加失败
	 */
	public boolean insertPaperAssembly(PaperAssemblyEntity pAssemblyEntity);
	/**
	 * @Title: deletePaperAssemblyById
	 * @Description: 删除某一套试卷的所有组卷记录
	 * @Time: 2018年8月11日 上午10:34:18
	 * @author: wangbin
	 * @param paper_id 试卷编号
	 * @return true:成功 false:失败
	 */
	public boolean deletePaperAssemblyById(String paper_id);
	public boolean deletePaperBySomeId(List<String> ids);
	/**
	 * @Title: updatePaperAssembly
	 * @Description: 更新组卷表中的一条记录
	 * @Time: 2018年8月11日 上午10:35:16
	 * @author: wangbin
	 * @param pAssemblyEntity 修改后的实体
	 * @return true:成功 false:失败
	 */
	public boolean updatePaperAssembly(PaperAssemblyEntity pAssemblyEntity);
	/**
	 * @Title: getPaperAssemblysById
	 * @Description: 获取某一试卷中的所有题目
	 * @Time: 2018年8月11日 上午10:35:47
	 * @author: wangbin
	 * @param paper_id 试卷编号
	 * @return List<PaperAssemblyEntity>查询得到的该试卷问题列表
	 */
	public List<PaperAssemblyEntity> getPaperAssemblysById(String paper_id);
	public List<PaperAssemblyEntity> getAllPaperAssembly();

}
