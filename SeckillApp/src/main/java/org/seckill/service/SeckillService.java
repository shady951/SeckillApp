package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;

public interface SeckillService {

	List<Seckill> getSeckillList();

	Seckill getById(long itemId);

	Exposer exportSeckillUrl(long itemId);

	SeckillExecution executionSeckill(long itemId, long userId, String md5);

}
