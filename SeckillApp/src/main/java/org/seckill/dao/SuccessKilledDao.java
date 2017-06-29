package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;


public interface SuccessKilledDao {
	
	int insertSuccessKilled(@Param("itemId")long itemId,@Param("userId") long userId);
	
	SuccessKilled queryByIdWithSeckill(@Param("itemId")long itemId,@Param("userId") long userId);

}
