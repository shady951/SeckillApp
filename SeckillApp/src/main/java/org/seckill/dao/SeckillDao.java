package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * �p������
	 * @param itemId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("itemId")long itemId,@Param("killTime") Date killTime);
	
	/**
	 * ��ԃ
	 * @param itemId
	 * @return
	 */
	Seckill queryById(long itemId);
	/**
	 * ��ѯ����
	 * @param offset ��ѯ���
	 * @param limit ��ѯ����
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);
}
