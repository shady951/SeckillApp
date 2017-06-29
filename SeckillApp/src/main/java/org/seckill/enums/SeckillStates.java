package org.seckill.enums;

public enum SeckillStates {success(1,"秒杀成功！"),
	repeat(-1,"您已重复秒杀！"),
	end(-2,"商品秒杀已结束！"),
	failed(-3,"系统错误！");
	
	private int state;

	private String stateInfo;

	private SeckillStates(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static SeckillStates stateOf(int index) {
		for(SeckillStates seckillStates : values()) {
			if(seckillStates.getState() == index) return seckillStates;
		}
		return null;
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
	
	
}
