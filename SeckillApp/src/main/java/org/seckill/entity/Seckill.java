package org.seckill.entity;

import java.util.Date;

public class Seckill {
	
	private long itemId;
	private String name;
	private int number;
	private Date createTime;
	private Date startTime;
	private Date endTime;

	public Seckill() {
	}

	@Override
	public String toString() {
		return "Seckill [itemId=" + itemId + ", name=" + name + ", number="
				+ number + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", createTime=" + createTime + "]";
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
