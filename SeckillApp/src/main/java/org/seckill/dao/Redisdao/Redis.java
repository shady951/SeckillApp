package org.seckill.dao.Redisdao;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final JedisPool jedisPool;
	
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	public Redis(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}
	
	public Seckill getSeckill(long seckillId) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:"+seckillId;
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes != null) {
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					return seckill;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public String putSeckill(Seckill seckill) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
					String key = "seckill:"+seckill.getItemId();
					byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
							LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
					//设置为超时缓存，时间两小时
					int timeout = 60 * 60 * 2;
					String result = jedis.setex(key.getBytes(), timeout, bytes);
					return result;
				} finally {
					jedis.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		return null;
	}

}
