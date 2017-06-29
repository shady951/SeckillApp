package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStates;

public class SeckillExecution {
	private long itemId;
	private int state;
	private String stateInfo;
	private SuccessKilled successKilled;
	
	
	
	public SeckillExecution(long itemId, SeckillStates seckillStates) {
		super();
		this.itemId = itemId;
		this.state = seckillStates.getState();
		this.stateInfo = seckillStates.getStateInfo();
	}
	public SeckillExecution(long itemId, SeckillStates seckillStates,SuccessKilled successKilled) {
		super();
		this.itemId = itemId;
		this.state = seckillStates.getState();
		this.stateInfo = seckillStates.getStateInfo();
		this.successKilled = successKilled;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
}
