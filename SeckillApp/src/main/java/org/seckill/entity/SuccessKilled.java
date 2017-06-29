package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
	private long itemId;
	private long userId;
	private short state;
	private Date createTime;
	private Seckill seckill;
	
	public SuccessKilled(long itemId, long userId, short state, Date createTime) {
		this.itemId = itemId;
		this.userId = userId;
		this.state = state;
		this.createTime = createTime;
	}
	
	public SuccessKilled() {
		
	}
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SuccessKilled [itemId=" + itemId + ", userId=" + userId
				+ ", state=" + state + ", createTime=" + createTime + "]";
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	
}
