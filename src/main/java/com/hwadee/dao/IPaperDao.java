/**
 * 
 */
package com.hwadee.dao;

import java.util.List;

import com.hwadee.model.PaperEntity;

/**
 * 项目名称: URLIS
 * 类名称: PaperDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:14:09
 */
public interface IPaperDao {

	/**
	 * @Title: insertPaper
	 * @Description: 添加一份考卷
	 * @Time: 2018年8月11日 上午10:25:02
	 * @author: wangbin
	 * @param paperEntity 待添加的考卷实体
	 * @return true:添加成功 false:添加失败
	 */
	public boolean insertPaper(PaperEntity paperEntity);
	/**
	 * @Title: deletePaper
	 * @Description: 删除一份考卷
	 * @Time: 2018年8月11日 上午10:25:37
	 * @author: wangbin
	 * @param paper_id 待删除考卷的编号
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deletePaper(String paper_id);
	public boolean deleteSomePaper(List<String> ids);
	/**
	 * @Title: updatePaper
	 * @Description: 更新一份考卷信息
	 * @Time: 2018年8月11日 上午10:26:07
	 * @author: wangbin
	 * @param paperEntity 修改后的考卷实体
	 * @return true:修改成功 false:修改失败
	 */
	public boolean updatePaper(PaperEntity paperEntity);
	/**
	 * @Title: queryPaper
	 * @Description: 获取所有的考卷
	 * @Time: 2018年8月11日 上午10:27:12
	 * @author: wangbin
	 * @return List<PaperEntity>数据库中的所有考卷
	 */
	public List<PaperEntity> queryPaper();
	/**
	 * @Title: seePaper
	 * @Description: 查看某一份考卷具体内容
	 * @Time: 2018年8月11日 上午10:27:44
	 * @author: wangbin
	 * @param paper_id 待查看考卷的编号
	 * @return PaperEntity根据考卷编号查询得到的考卷实体
	 */
	public PaperEntity seePaper(int paper_id);
	public List<PaperEntity> getAllPaper();
	public List<PaperEntity> getPaperByName(String paper_name);
	public PaperEntity getPaperById(String paper_id);
}
