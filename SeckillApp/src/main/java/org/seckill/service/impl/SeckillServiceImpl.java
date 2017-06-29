package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.Redisdao.Redis;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStates;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {

	private final String salt = "whosyourdaddy";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SuccessKilledDao successKilledDao;

	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private Redis redis;

	/**
	 * 获取Md5
	 * @param itemId
	 * @return
	 */
	private String getMd5(long itemId) {
		String md5 = salt + ":" + itemId;
		return DigestUtils.md5DigestAsHex(md5.getBytes());
	}
	
	/**
	 * 获取商品列表
	 */
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 5);
	}

	/**
	 * 按商品ID查询
	 */
	public Seckill getById(long itemId) {
		return seckillDao.queryById(itemId);
	}

	/**
	 * 
	 */
	public Exposer exportSeckillUrl(long itemId) {
//		Seckill seckill = seckillDao.queryById(itemId);
		Seckill seckill = redis.getSeckill(itemId);
		if(seckill == null) {
			seckill = seckillDao.queryById(itemId);
			if(seckill == null) {
				return new Exposer(false, itemId);
			} else {
				redis.putSeckill(seckill);
			}
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (startTime.getTime() >= nowTime.getTime()
				|| endTime.getTime() <= nowTime.getTime()) {
			return new Exposer(false, itemId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(itemId);
		return new Exposer(true, md5, itemId);
	}

	@Transactional
	public SeckillExecution executionSeckill(long itemId, long userId,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		try {
			//if (!md5.equals(getMd5(itemId))) { //^^
				if(md5.equals("0")) { //vv
				throw new SeckillException("Data rewrited");
			} else {
				// 数据未更改的话，则秒杀已结束
				if (seckillDao.reduceNumber(itemId, new Date()) <= 0) {
					// if (seckillDao.reduceNumber(itemId, new Date()) > 0) {
					throw new SeckillCloseException("Seckill time was end");
				} else {
					// 数据未更改的话，则秒杀重复
					//^^数据库两个主键被取消
					if (successKilledDao.insertSuccessKilled(itemId, userId) <= 0) {
						throw new RepeatKillException("Seckill repeat");
					} else {
						// 返回成功秒杀的信息
						// 第二个传参也可用SeckillStates.succsess
						return new SeckillExecution(itemId,
								SeckillStates.stateOf(1));
								/*
								 * 高并发测试下，注释掉这句
								successKilledDao.queryByIdWithSeckill(itemId,userId)); //^^
								 */
					}
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}
}
