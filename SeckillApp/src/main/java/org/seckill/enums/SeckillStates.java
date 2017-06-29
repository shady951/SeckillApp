package org.seckill.enums;

public enum SeckillStates {success(1,"��ɱ�ɹ���"),
	repeat(-1,"�����ظ���ɱ��"),
	end(-2,"��Ʒ��ɱ�ѽ�����"),
	failed(-3,"ϵͳ����");
	
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
