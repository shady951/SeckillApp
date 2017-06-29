package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * 減庫存更新
	 * @param itemId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("itemId")long itemId,@Param("killTime") Date killTime);
	
	/**
	 * 查詢
	 * @param itemId
	 * @return
	 */
	Seckill queryById(long itemId);
	/**
	 * 查询所有
	 * @param offset 查询起点
	 * @param limit 查询数量
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);
}
