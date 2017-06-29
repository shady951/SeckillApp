package org.seckill.dto;

public class Exposer {

	private boolean exposed;
	private String md5;
	private long itemId;
	private long now;
	private long start;
	private long end;

	public Exposer(boolean exposed,long itemId, long now, long start, long end) {		
		super();
		this.itemId = itemId;
		this.exposed = exposed;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer(boolean exposed, long itemId) {
		super();
		this.exposed = exposed;
		this.itemId = itemId;
	}

	public Exposer(boolean exposed, String md5, long itemId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.itemId = itemId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + "/n, md5=" + md5 + "/n, itemId="
				+ itemId + "/n, now=" + now + "/n, start=" + start + "/n, end=" + end
				+ "]";
	}

}
