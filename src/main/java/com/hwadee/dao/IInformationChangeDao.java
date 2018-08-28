/**
 * 
 */
package com.hwadee.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwadee.model.InformationChangeEntity;

/**
 * 项目名称: URLIS
 * 类名称: InformationChangeDao
 * 创建人: wangbin
 * 创建时间: 2018年8月11日 上午10:37:41
 */
public interface IInformationChangeDao {


	public List<InformationChangeEntity> getAllChange();
	public InformationChangeEntity getChangeByIdAndDate(String per_id,String chan_time);
	public List<InformationChangeEntity> getChangeByNameOrId(String per_name,String per_id);
	public boolean deleteChange(String per_id,String chan_time);
	public boolean deleteSomeChange(@Param("ids")List<String> ids,@Param("times")List<String> times);
	public boolean insertChange(InformationChangeEntity change);
}
